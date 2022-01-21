package com.daomain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 商品信息的实体类
 */
@Entity
@Table(name = "p_sp")
public class Sp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer spbh;
    private String spmc;
    private String zjm;
    private String spdw;
    private String spgg;
    private Integer spjg;
    private String zzs;
    private String yl1;
    private String yl2;

    public Sp() {
    }

    public Sp(Integer spbh, String spmc, String zjm, String spdw, String spgg, Integer spjg, String zzs, String yl1, String yl2) {
        this.spbh = spbh;
        this.spmc = spmc;
        this.zjm = zjm;
        this.spdw = spdw;
        this.spgg = spgg;
        this.spjg = spjg;
        this.zzs = zzs;
        this.yl1 = yl1;
        this.yl2 = yl2;
    }

    public Integer getSpbh() {
        return spbh;
    }

    public void setSpbh(Integer spbh) {
        this.spbh = spbh;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getZjm() {
        return zjm;
    }

    public void setZjm(String zjm) {
        this.zjm = zjm;
    }

    public String getSpdw() {
        return spdw;
    }

    public void setSpdw(String spdw) {
        this.spdw = spdw;
    }

    public String getSpgg() {
        return spgg;
    }

    public void setSpgg(String spgg) {
        this.spgg = spgg;
    }

    public Integer getSpjg() {
        return spjg;
    }

    public void setSpjg(Integer spjg) {
        this.spjg = spjg;
    }

    public String getZzs() {
        return zzs;
    }

    public void setZzs(String zzs) {
        this.zzs = zzs;
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
