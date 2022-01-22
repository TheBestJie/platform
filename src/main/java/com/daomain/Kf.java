package com.daomain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "p_kf")
public class Kf implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kfbh;
    private String kfmc;
    private String kfwz;
    private String yl1;
    private String yl2;

    public Kf() {
    }

    public Kf(Integer kfbh, String kfmc, String kfwz, String yl1, String yl2) {
        this.kfbh = kfbh;
        this.kfmc = kfmc;
        this.kfwz = kfwz;
        this.yl1 = yl1;
        this.yl2 = yl2;
    }

    public Integer getKfbh() {
        return kfbh;
    }

    public void setKfbh(Integer kfbh) {
        this.kfbh = kfbh;
    }

    public String getKfmc() {
        return kfmc;
    }

    public void setKfmc(String kfmc) {
        this.kfmc = kfmc;
    }

    public String getKfwz() {
        return kfwz;
    }

    public void setKfwz(String kfwz) {
        this.kfwz = kfwz;
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
