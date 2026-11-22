package com.rednote.note.biz.rpc;

import cn.hutool.core.collection.CollUtil;
import com.rednote.common.response.Result;
import com.rednote.count.api.dto.FindNoteCountsByIdRspDTO;
import com.rednote.count.api.dto.FindNoteCountsByIdsReqDTO;
import com.rednote.count.api.fegin.CountFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 计数服务
 *
 * @author Jason Feng
 * @since 2026-11-22 14:42
 */
@Component
public class CountRpcService {

    @Resource
    private CountFeignApi countFeignApi;

    /**
     * 批量查询笔记计数
     *
     * @param noteIds
     * @return
     */
    public List<FindNoteCountsByIdRspDTO> findByNoteIds(List<Long> noteIds) {
        FindNoteCountsByIdsReqDTO findNoteCountsByIdsReqDTO = new FindNoteCountsByIdsReqDTO();
        findNoteCountsByIdsReqDTO.setNoteIds(noteIds);

        Result<List<FindNoteCountsByIdRspDTO>> response = countFeignApi.findNotesCount(findNoteCountsByIdsReqDTO);

        if (!response.isSuccess() || Objects.isNull(response.getData()) || CollUtil.isEmpty(response.getData())) {
            return null;
        }
        return response.getData();
    }
}
