-- KEYS[1] = 版本号 key
-- KEYS[2] = 分类 hash key
-- ARGV    = field1, value1, field2, value2 ...（parentId -> 分片 JSON）
-- 返回：递增后的新版本号

redis.call('DEL', KEYS[2])
local argc = #ARGV
for i = 1, argc, 2 do
    redis.call('HSET', KEYS[2], ARGV[i], ARGV[i + 1])
end
return redis.call('INCR', KEYS[1])