package com.rednote.note.biz.domain.mapper;

import com.rednote.note.biz.domain.dataobject.ChannelDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 频道 Mapper 类
 *
 * @since 2026-03-04 21:26
 * @author Jason Feng
 */
@Mapper
public interface ChannelDOMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ChannelDO record);

    int insertSelective(ChannelDO record);

    ChannelDO selectByPrimaryKey(Long id);

    List<ChannelDO> selectAll();

    int updateByPrimaryKeySelective(ChannelDO record);

    int updateByPrimaryKey(ChannelDO record);
}