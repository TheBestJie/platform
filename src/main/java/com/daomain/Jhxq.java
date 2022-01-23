package com.daomain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "p_jhxq")
public class Jhxq implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jhid;
    private String jhdbh;
    @Column(insertable = false,updatable = false)
    private Integer spbh;
    private Integer spsl;
    private Integer spjg;

    @ManyToOne
    @JoinColumn(name = "spbh")
    private Sp sp;

    public Jhxq() {
    }

    public Jhxq(Integer jhid, String jhdbh, Integer spbh, Integer spsl, Integer spjg) {
        this.jhid = jhid;
        this.jhdbh = jhdbh;
        this.spbh = spbh;
        this.spsl = spsl;
        this.spjg = spjg;
    }


    //public Jhxq(String jhdbh, Integer spbh, Integer spsl, Integer spjg) {
    //    this.jhdbh = jhdbh;
    //    this.spbh = spbh;
    //    this.spsl = spsl;
    //    this.spjg = spjg;
    //}

    public Integer getJhid() {
        return jhid;
    }

    public void setJhid(Integer jhid) {
        this.jhid = jhid;
    }

    public String getJhdbh() {
        return jhdbh;
    }

    public void setJhdbh(String jhdbh) {
        this.jhdbh = jhdbh;
    }

    public Integer getSpbh() {
        return spbh;
    }

    public void setSpbh(Integer spbh) {
        this.spbh = spbh;
    }

    public Integer getSpsl() {
        return spsl;
    }

    public void setSpsl(Integer spsl) {
        this.spsl = spsl;
    }

    public Integer getSpjg() {
        return spjg;
    }

    public void setSpjg(Integer spjg) {
        this.spjg = spjg;
    }

    public Sp getSp() {
        return sp;
    }

    public void setSp(Sp sp) {
        this.sp = sp;
    }
}
