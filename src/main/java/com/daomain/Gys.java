package com.daomain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 供应商表的实体类
 */
@Entity
@Table(name = "p_gys")
public class Gys implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gysbh;
    private String gysmc;
    private String lxr;
    private String lxdh;
    private String dz;
    private String yl1;
    private String yl2;

    public Gys() {
    }

    public Gys(Integer gysbh, String gysmc, String lxr, String lxdh, String dz, String yl1, String yl2) {
        this.gysbh = gysbh;
        this.gysmc = gysmc;
        this.lxr = lxr;
        this.lxdh = lxdh;
        this.dz = dz;
        this.yl1 = yl1;
        this.yl2 = yl2;
    }

    public Integer getGysbh() {
        return gysbh;
    }

    public void setGysbh(Integer gysbh) {
        this.gysbh = gysbh;
    }

    public String getGysmc() {
        return gysmc;
    }

    public void setGysmc(String gysmc) {
        this.gysmc = gysmc;
    }

    public String getLxr() {
        return lxr;
    }

    public void setLxr(String lxr) {
        this.lxr = lxr;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getYl1() {
        return yl1;
    }

    public void setYl1(String yl1) {
        this.yl1 = yl1;
    }

    public String getYl2() {
        return yl2;
    }

    public void setYl2(String yl2) {
        this.yl2 = yl2;
    }
}
