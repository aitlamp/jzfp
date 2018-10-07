package com.atlp.jzfp.entity.yzgz;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_YZGZ_GZD_CY", schema = "JZFP", catalog = "")
public class JzfpBYzgzGzdCyEntity {
    private String cyid;
    private String gzdid;
    private String cyxm;
    private String dwid;
    private String dwmc;
    private String cyzr;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "CYID")
    public String getCyid() {
        return cyid;
    }

    public void setCyid(String cyid) {
        this.cyid = cyid;
    }

    @Basic
    @Column(name = "GZDID")
    public String getGzdid() {
        return gzdid;
    }

    public void setGzdid(String gzdid) {
        this.gzdid = gzdid;
    }

    @Basic
    @Column(name = "CYXM")
    public String getCyxm() {
        return cyxm;
    }

    public void setCyxm(String cyxm) {
        this.cyxm = cyxm;
    }

    @Basic
    @Column(name = "DWID")
    public String getDwid() {
        return dwid;
    }

    public void setDwid(String dwid) {
        this.dwid = dwid;
    }

    @Basic
    @Column(name = "DWMC")
    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    @Basic
    @Column(name = "CYZR")
    public String getCyzr() {
        return cyzr;
    }

    public void setCyzr(String cyzr) {
        this.cyzr = cyzr;
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

        JzfpBYzgzGzdCyEntity that = (JzfpBYzgzGzdCyEntity) o;

        if (cyid != null ? !cyid.equals(that.cyid) : that.cyid != null) return false;
        if (gzdid != null ? !gzdid.equals(that.gzdid) : that.gzdid != null) return false;
        if (cyxm != null ? !cyxm.equals(that.cyxm) : that.cyxm != null) return false;
        if (dwid != null ? !dwid.equals(that.dwid) : that.dwid != null) return false;
        if (dwmc != null ? !dwmc.equals(that.dwmc) : that.dwmc != null) return false;
        if (cyzr != null ? !cyzr.equals(that.cyzr) : that.cyzr != null) return false;
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
        int result = cyid != null ? cyid.hashCode() : 0;
        result = 31 * result + (gzdid != null ? gzdid.hashCode() : 0);
        result = 31 * result + (cyxm != null ? cyxm.hashCode() : 0);
        result = 31 * result + (dwid != null ? dwid.hashCode() : 0);
        result = 31 * result + (dwmc != null ? dwmc.hashCode() : 0);
        result = 31 * result + (cyzr != null ? cyzr.hashCode() : 0);
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
