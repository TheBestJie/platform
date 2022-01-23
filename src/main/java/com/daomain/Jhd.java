package com.daomain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "p_jhd")
public class Jhd implements Serializable {
    @Id
    private String jhdbh;
    private String jhsj;
    @Column(insertable = false,updatable = false)
    private Integer jhrbh;  //进货人编号
    @Column(insertable = false,updatable = false)
    private Integer gysbh; //进货商编号
    private Integer jhzj;
    @Column(insertable = false,updatable = false)
    private Integer kfbh;
    private String yl1;
    private String yl2;

    /*关联属性*/
    @ManyToOne
    @JoinColumn(name = "jhrbh")
    private Yh jhr;
    @ManyToOne
    @JoinColumn(name = "gysbh")
    private Gys gys;
    @ManyToOne
    @JoinColumn(name = "kfbh")
    private Kf kf;
    //在存储进货表的同时存储进货详情
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "jhdbh")
    private Set<Jhxq> jhxqList;

    public Jhd() {
    }

    public Jhd(String jhdbh, String jhsj, Integer jhrbh, Integer gysbh, Integer jhzj, Integer kfbh, String yl1, String yl2) {
        this.jhdbh = jhdbh;
        this.jhsj = jhsj;
        this.jhrbh = jhrbh;
        this.gysbh = gysbh;
        this.jhzj = jhzj;
        this.kfbh = kfbh;
        this.yl1 = yl1;
        this.yl2 = yl2;
    }

    public String getJhdbh() {
        return jhdbh;
    }

    public void setJhdbh(String jhdbh) {
        this.jhdbh = jhdbh;
    }

    public String getJhsj() {
        return jhsj;
    }

    public void setJhsj(String jhsj) {
        this.jhsj = jhsj;
    }

    public Integer getJhrbh() {
        return jhrbh;
    }

    public void setJhrbh(Integer jhrbh) {
        this.jhrbh = jhrbh;
    }

    public Integer getGysbh() {
        return gysbh;
    }

    public void setGysbh(Integer gysbh) {
        this.gysbh = gysbh;
    }

    public Integer getJhzj() {
        return jhzj;
    }

    public void setJhzj(Integer jhzj) {
        this.jhzj = jhzj;
    }

    public Integer getKfbh() {
        return kfbh;
    }

    public void setKfbh(Integer kfbh) {
        this.kfbh = kfbh;
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

    public Yh getJhr() {
        return jhr;
    }

    public void setJhr(Yh jhr) {
        this.jhr = jhr;
    }

    public Gys getGys() {
        return gys;
    }

    public void setGys(Gys gys) {
        this.gys = gys;
    }

    public Kf getKf() {
        return kf;
    }

    public void setKf(Kf kf) {
        this.kf = kf;
    }

    public Set<Jhxq> getJhxqList() {
        return jhxqList;
    }

    public void setJhxqList(Set<Jhxq> jhxqList) {
        this.jhxqList = jhxqList;
    }
}
