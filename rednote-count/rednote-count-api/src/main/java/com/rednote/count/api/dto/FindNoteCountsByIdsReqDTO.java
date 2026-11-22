package com.rednote.count.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 批量查询笔记维度相关计数
 *
 * @author Jason Feng
 * @since 2026-11-22 11:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindNoteCountsByIdsReqDTO {

    @NotNull(message = "笔记 ID 集合不能为空")
    @Size(min = 1, max = 20, message = "笔记 ID 集合大小必须大于等于 1, 小于等于 20")
    private List<Long> noteIds;
}
