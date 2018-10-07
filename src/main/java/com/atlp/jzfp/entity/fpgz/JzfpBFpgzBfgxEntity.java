package com.atlp.jzfp.entity.fpgz;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_FPGZ_BFGX", schema = "JZFP", catalog = "")
public class JzfpBFpgzBfgxEntity {
    private String gxid;
    private Boolean dxlx;
    private String dxid;
    private String dxmc;
    private String bdxid;
    private String bdxmc;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "GXID")
    public String getGxid() {
        return gxid;
    }

    public void setGxid(String gxid) {
        this.gxid = gxid;
    }

    @Basic
    @Column(name = "DXLX")
    public Boolean getDxlx() {
        return dxlx;
    }

    public void setDxlx(Boolean dxlx) {
        this.dxlx = dxlx;
    }

    @Basic
    @Column(name = "DXID")
    public String getDxid() {
        return dxid;
    }

    public void setDxid(String dxid) {
        this.dxid = dxid;
    }

    @Basic
    @Column(name = "DXMC")
    public String getDxmc() {
        return dxmc;
    }

    public void setDxmc(String dxmc) {
        this.dxmc = dxmc;
    }

    @Basic
    @Column(name = "BDXID")
    public String getBdxid() {
        return bdxid;
    }

    public void setBdxid(String bdxid) {
        this.bdxid = bdxid;
    }

    @Basic
    @Column(name = "BDXMC")
    public String getBdxmc() {
        return bdxmc;
    }

    public void setBdxmc(String bdxmc) {
        this.bdxmc = bdxmc;
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

        JzfpBFpgzBfgxEntity that = (JzfpBFpgzBfgxEntity) o;

        if (gxid != null ? !gxid.equals(that.gxid) : that.gxid != null) return false;
        if (dxlx != null ? !dxlx.equals(that.dxlx) : that.dxlx != null) return false;
        if (dxid != null ? !dxid.equals(that.dxid) : that.dxid != null) return false;
        if (dxmc != null ? !dxmc.equals(that.dxmc) : that.dxmc != null) return false;
        if (bdxid != null ? !bdxid.equals(that.bdxid) : that.bdxid != null) return false;
        if (bdxmc != null ? !bdxmc.equals(that.bdxmc) : that.bdxmc != null) return false;
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
        int result = gxid != null ? gxid.hashCode() : 0;
        result = 31 * result + (dxlx != null ? dxlx.hashCode() : 0);
        result = 31 * result + (dxid != null ? dxid.hashCode() : 0);
        result = 31 * result + (dxmc != null ? dxmc.hashCode() : 0);
        result = 31 * result + (bdxid != null ? bdxid.hashCode() : 0);
        result = 31 * result + (bdxmc != null ? bdxmc.hashCode() : 0);
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
