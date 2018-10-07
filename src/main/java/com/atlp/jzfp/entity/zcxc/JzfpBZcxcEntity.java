package com.atlp.jzfp.entity.zcxc;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_ZCXC", schema = "JZFP", catalog = "")
public class JzfpBZcxcEntity {
    private String xcid;
    private Boolean lx;
    private String bt;
    private String nr;
    private Time shrq;
    private Time fbrq;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "XCID")
    public String getXcid() {
        return xcid;
    }

    public void setXcid(String xcid) {
        this.xcid = xcid;
    }

    @Basic
    @Column(name = "LX")
    public Boolean getLx() {
        return lx;
    }

    public void setLx(Boolean lx) {
        this.lx = lx;
    }

    @Basic
    @Column(name = "BT")
    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    @Basic
    @Column(name = "NR")
    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Basic
    @Column(name = "SHRQ")
    public Time getShrq() {
        return shrq;
    }

    public void setShrq(Time shrq) {
        this.shrq = shrq;
    }

    @Basic
    @Column(name = "FBRQ")
    public Time getFbrq() {
        return fbrq;
    }

    public void setFbrq(Time fbrq) {
        this.fbrq = fbrq;
    }

    @Basic
    @Column(name = "SM")
    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }

    @Basic
    @Column(name = "DQZT")
    public String getDqzt() {
        return dqzt;
    }

    public void setDqzt(String dqzt) {
        this.dqzt = dqzt;
    }

    @Basic
    @Column(name = "FIRSTTIME")
    public Time getFirsttime() {
        return firsttime;
    }

    public void setFirsttime(Time firsttime) {
        this.firsttime = firsttime;
    }

    @Basic
    @Column(name = "LASTTIME")
    public Time getLasttime() {
        return lasttime;
    }

    public void setLasttime(Time lasttime) {
        this.lasttime = lasttime;
    }

    @Basic
    @Column(name = "YHID")
    public String getYhid() {
        return yhid;
    }

    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    @Basic
    @Column(name = "YHXM")
    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm;
    }

    @Basic
    @Column(name = "YHDWID")
    public String getYhdwid() {
        return yhdwid;
    }

    public void setYhdwid(String yhdwid) {
        this.yhdwid = yhdwid;
    }

    @Basic
    @Column(name = "YHDWMC")
    public String getYhdwmc() {
        return yhdwmc;
    }

    public void setYhdwmc(String yhdwmc) {
        this.yhdwmc = yhdwmc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JzfpBZcxcEntity that = (JzfpBZcxcEntity) o;

        if (xcid != null ? !xcid.equals(that.xcid) : that.xcid != null) return false;
        if (lx != null ? !lx.equals(that.lx) : that.lx != null) return false;
        if (bt != null ? !bt.equals(that.bt) : that.bt != null) return false;
        if (nr != null ? !nr.equals(that.nr) : that.nr != null) return false;
        if (shrq != null ? !shrq.equals(that.shrq) : that.shrq != null) return false;
        if (fbrq != null ? !fbrq.equals(that.fbrq) : that.fbrq != null) return false;
        if (sm != null ? !sm.equals(that.sm) : that.sm != null) return false;
        if (dqzt != null ? !dqzt.equals(that.dqzt) : that.dqzt != null) return false;
        if (firsttime != null ? !firsttime.equals(that.firsttime) : that.firsttime != null) return false;
        if (lasttime != null ? !lasttime.equals(that.lasttime) : that.lasttime != null) return false;
        if (yhid != null ? !yhid.equals(that.yhid) : that.yhid != null) return false;
        if (yhxm != null ? !yhxm.equals(that.yhxm) : that.yhxm != null) return false;
        if (yhdwid != null ? !yhdwid.equals(that.yhdwid) : that.yhdwid != null) return false;
        if (yhdwmc != null ? !yhdwmc.equals(that.yhdwmc) : that.yhdwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = xcid != null ? xcid.hashCode() : 0;
        result = 31 * result + (lx != null ? lx.hashCode() : 0);
        result = 31 * result + (bt != null ? bt.hashCode() : 0);
        result = 31 * result + (nr != null ? nr.hashCode() : 0);
        result = 31 * result + (shrq != null ? shrq.hashCode() : 0);
        result = 31 * result + (fbrq != null ? fbrq.hashCode() : 0);
        result = 31 * result + (sm != null ? sm.hashCode() : 0);
        result = 31 * result + (dqzt != null ? dqzt.hashCode() : 0);
        result = 31 * result + (firsttime != null ? firsttime.hashCode() : 0);
        result = 31 * result + (lasttime != null ? lasttime.hashCode() : 0);
        result = 31 * result + (yhid != null ? yhid.hashCode() : 0);
        result = 31 * result + (yhxm != null ? yhxm.hashCode() : 0);
        result = 31 * result + (yhdwid != null ? yhdwid.hashCode() : 0);
        result = 31 * result + (yhdwmc != null ? yhdwmc.hashCode() : 0);
        return result;
    }
}
