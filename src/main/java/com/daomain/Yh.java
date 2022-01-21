package com.daomain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "p_yh")
public class Yh implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer yhbh;
    private String yhm;
    private String yhmm;
    private Integer nl;
    private String xb;

    public Yh() {
    }

    public Yh(Integer yhbh, String yhm, String yhmm, Integer nl, String xb) {
        this.yhbh = yhbh;
        this.yhm = yhm;
        this.yhmm = yhmm;
        this.nl = nl;
        this.xb = xb;
    }

    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getYhmm() {
        return yhmm;
    }

    public void setYhmm(String yhmm) {
        this.yhmm = yhmm;
    }

    public Integer getNl() {
        return nl;
    }

    public void setNl(Integer nl) {
        this.nl = nl;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }
}
