package com.atlp.jzfp.entity.fpxm;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_XM_FL", schema = "JZFP", catalog = "")
public class JzfpBXmFlEntity {
    private String flid;
    private String pflid;
    private String flmc;
    private byte xssx;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "FLID")
    public String getFlid() {
        return flid;
    }

    public void setFlid(String flid) {
        this.flid = flid;
    }

    @Basic
    @Column(name = "PFLID")
    public String getPflid() {
        return pflid;
    }

    public void setPflid(String pflid) {
        this.pflid = pflid;
    }

    @Basic
    @Column(name = "FLMC")
    public String getFlmc() {
        return flmc;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    @Basic
    @Column(name = "XSSX")
    public byte getXssx() {
        return xssx;
    }

    public void setXssx(byte xssx) {
        this.xssx = xssx;
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

        JzfpBXmFlEntity that = (JzfpBXmFlEntity) o;

        if (xssx != that.xssx) return false;
        if (flid != null ? !flid.equals(that.flid) : that.flid != null) return false;
        if (pflid != null ? !pflid.equals(that.pflid) : that.pflid != null) return false;
        if (flmc != null ? !flmc.equals(that.flmc) : that.flmc != null) return false;
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
        int result = flid != null ? flid.hashCode() : 0;
        result = 31 * result + (pflid != null ? pflid.hashCode() : 0);
        result = 31 * result + (flmc != null ? flmc.hashCode() : 0);
        result = 31 * result + (int) xssx;
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
