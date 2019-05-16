package com.zsx.dao;

import com.zsx.entity.Tarticle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TarticleDao {

    Tarticle selectByPrimaryKey(Integer id);

    int insert(Tarticle tarticle);

    int updateByPrimaryKeySelective(Tarticle tarticle);

    List<Tarticle> selectByParams(@Param("search") Object search);

}
