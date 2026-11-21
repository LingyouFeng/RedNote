package com.rednote.note.biz.domain.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 笔记喜欢/收藏/评论计数实体类
 *
 * @author Jason Feng
 * @since 2026-11-21 16:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteCountDO {

    private Long id;

    private Long noteId;

    private Long likeTotal;

    private Long collectTotal;

    private Long commentTotal;

}