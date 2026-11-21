package com.rednote.note.biz.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 查询频道
 *
 * @author Jason Feng
 * @since 2026-11-21 16:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindChannelRspVO {

    /**
     * 频道 ID
     */
    private Long id;

    /**
     * 频道名称
     */
    private String name;

}
