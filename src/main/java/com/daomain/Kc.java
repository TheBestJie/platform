package com.daomain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "p_kc")
public class Kc implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer kcid;
    @Column(insertable = false,updatable = false)
    private Integer kfbh;
    @Column(insertable = false,updatable = false)
    private Integer spbh;
    private Integer spsl;

    @ManyToOne
    @JoinColumn(name = "kfbh")
    private Kf kf;
    @ManyToOne
    @JoinColumn(name = "spbh")
    private Sp sp;

    public Kc() {
    }

    public Kc(Integer kcid, Integer kfbh, Integer spbh, Integer spsl) {
        this.kcid = kcid;
        this.kfbh = kfbh;
        this.spbh = spbh;
        this.spsl = spsl;
    }

    public Integer getkcid() {
        return kcid;
    }

    public void setkcid(Integer kcid) {
        this.kcid = kcid;
    }

    public Integer getKfbh() {
        return kfbh;
    }

    public void setKfbh(Integer kfbh) {
        this.kfbh = kfbh;
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

    public Kf getKf() {
        return kf;
    }

    public void setKf(Kf kf) {
        this.kf = kf;
    }

    public Sp getSp() {
        return sp;
    }

    public void setSp(Sp sp) {
        this.sp = sp;
    }
}
