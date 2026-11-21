package com.rednote.user.biz.rpc;

import com.rednote.leaf.api.feign.LeafFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 分布式 ID 生成服务
 *
 * @author Jason Feng
 * @since 2026-03-01 21:28
 */
@Component
public class LeafRpcService {

    @Resource
    private LeafFeignApi leafFeignApi;

    /**
     * Leaf 号段模式：小红书 ID 业务标识
     */
    private static final String BIZ_TAG_REDNOTE_ID = "leaf-segment-rednote-id";

    /**
     * Leaf 号段模式：用户 ID 业务标识
     */
    private static final String BIZ_TAG_USER_ID = "leaf-segment-user-id";

    /**
     * 调用分布式 ID 生成服务生成小红书 ID
     *
     * @return      小红书用户 ID
     */
    public String getRedNoteId() {
        return leafFeignApi.getSegmentId(BIZ_TAG_REDNOTE_ID);
    }

    /**
     * 调用分布式 ID 生成服务用户 ID
     *
     * @return
     */
    public String getUserId() {
        return leafFeignApi.getSegmentId(BIZ_TAG_USER_ID);
    }
}
