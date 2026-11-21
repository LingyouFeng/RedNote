package com.rednote.note.biz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 查询话题
 *
 * @author Jason Feng
 * @since 2026-11-21 16:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindTopicRspVO {

    /**
     * 话题 ID
     */
    private Long id;

    /**
     * 话题名称
     */
    private String name;

}
