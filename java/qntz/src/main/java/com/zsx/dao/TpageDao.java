package com.zsx.dao;

import com.zsx.entity.Tpage;

import java.util.List;

public interface TpageDao {

    Tpage selectByPrimaryKey(Integer id);

    void insert(Tpage tpage);

    void updateByPrimaryKeySelective(Tpage tpage);

    List<Tpage> selectAll();

}
