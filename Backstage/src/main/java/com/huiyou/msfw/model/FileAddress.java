package com.huiyou.msfw.model;

import java.io.Serializable;

/**
 * @功能
 * @创建时间 2019/11/12
 */
public class FileAddress implements Serializable {
    private static final long serialVersionUID=1L;
    private String adrImg;

    public String getAdrImg() {
        return adrImg;
    }

    public void setAdrImg(String adrImg) {
        this.adrImg = adrImg;
    }

    public String getAdrVid() {
        return adrVid;
    }

    public void setAdrVid(String adrVid) {
        this.adrVid = adrVid;
    }

    private String adrVid;
}
