package com.zsx.dao;

import com.zsx.entity.Tarticle;
import com.zsx.entity.Timage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TimageDao {

    Timage selectByPrimaryKey(Integer id);

    int insert(Timage timage);

    int updateByPrimaryKeySelective(Timage timage);

    List<Timage> selectByParams(@Param("search") Object search);

    int insertBatch(List<Timage> list);

}
