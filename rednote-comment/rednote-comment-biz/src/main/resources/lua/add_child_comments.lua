-- 入参说明：
-- KEYS[1]: 子评论 ZSet 的键
-- ARGV: 每个评论的数据，格式为 member1, score1, member2, score2 ...
--        member 为评论 ID，score 为评论创建时间戳（与缓存重建时保持一致）

local zsetKey = KEYS[1]
local membersScores = ARGV

-- 缓存窗口大小，与缓存重建时的 6*10 保持一致
-- ZSet 中缓存的是最旧的 60 条子评论，只服务前 10 页
local maxSize = 60

-- 确认 ZSet 是否存在
-- 若不存在，说明缓存还没建立，直接返回，由读取接口从数据库重建，
-- 避免只把增量写进来，导致缓存中缺失历史评论
if redis.call('EXISTS', zsetKey) == 0 then
    return -1
end

for i = 1, #membersScores, 2 do
    local member = membersScores[i]
    local score = membersScores[i + 1]

    -- 已存在的成员：只更新 score，不占用缓存窗口
    if redis.call('ZSCORE', zsetKey, member) then
        redis.call('ZADD', zsetKey, score, member)
    elseif redis.call('ZCARD', zsetKey) < maxSize then
        redis.call('ZADD', zsetKey, score, member)
    else
        -- 缓存窗口已满：此时新评论在列表中的位置已经超出窗口（读取时会回源数据库），
        -- 若继续追加，它会被放到窗口末尾，和数据库分页的 offset 对不上，导致分页错位
        return -2
    end
end

return 0
