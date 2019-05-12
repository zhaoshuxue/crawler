package com.zsx.entity;

import java.io.Serializable;

/**
 * Created by highness on 2018/5/31 0031.
 */
public class Tpage implements Serializable {

    private Integer id;
    private Integer page;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
