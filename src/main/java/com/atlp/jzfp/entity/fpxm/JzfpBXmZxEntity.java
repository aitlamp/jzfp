package com.atlp.jzfp.entity.fpxm;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_XM_ZX", schema = "JZFP", catalog = "")
public class JzfpBXmZxEntity {
    private String zxid;
    private String jdid;
    private String xmid;
    private Time wcsj;
    private long bczxjd;
    private Long ljzxjd;
    private String gznr;
    private String zhyy;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "ZXID")
    public String getZxid() {
        return zxid;
    }

    public void setZxid(String zxid) {
        this.zxid = zxid;
    }

    @Basic
    @Column(name = "JDID")
    public String getJdid() {
        return jdid;
    }

    public void setJdid(String jdid) {
        this.jdid = jdid;
    }

    @Basic
    @Column(name = "XMID")
    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    @Basic
    @Column(name = "WCSJ")
    public Time getWcsj() {
        return wcsj;
    }

    public void setWcsj(Time wcsj) {
        this.wcsj = wcsj;
    }

    @Basic
    @Column(name = "BCZXJD")
    public long getBczxjd() {
        return bczxjd;
    }

    public void setBczxjd(long bczxjd) {
        this.bczxjd = bczxjd;
    }

    @Basic
    @Column(name = "LJZXJD")
    public Long getLjzxjd() {
        return ljzxjd;
    }

    public void setLjzxjd(Long ljzxjd) {
        this.ljzxjd = ljzxjd;
    }

    @Basic
    @Column(name = "GZNR")
    public String getGznr() {
        return gznr;
    }

    public void setGznr(String gznr) {
        this.gznr = gznr;
    }

    @Basic
    @Column(name = "ZHYY")
    public String getZhyy() {
        return zhyy;
    }

    public void setZhyy(String zhyy) {
        this.zhyy = zhyy;
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

        JzfpBXmZxEntity that = (JzfpBXmZxEntity) o;

        if (bczxjd != that.bczxjd) return false;
        if (zxid != null ? !zxid.equals(that.zxid) : that.zxid != null) return false;
        if (jdid != null ? !jdid.equals(that.jdid) : that.jdid != null) return false;
        if (xmid != null ? !xmid.equals(that.xmid) : that.xmid != null) return false;
        if (wcsj != null ? !wcsj.equals(that.wcsj) : that.wcsj != null) return false;
        if (ljzxjd != null ? !ljzxjd.equals(that.ljzxjd) : that.ljzxjd != null) return false;
        if (gznr != null ? !gznr.equals(that.gznr) : that.gznr != null) return false;
        if (zhyy != null ? !zhyy.equals(that.zhyy) : that.zhyy != null) return false;
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
        int result = zxid != null ? zxid.hashCode() : 0;
        result = 31 * result + (jdid != null ? jdid.hashCode() : 0);
        result = 31 * result + (xmid != null ? xmid.hashCode() : 0);
        result = 31 * result + (wcsj != null ? wcsj.hashCode() : 0);
        result = 31 * result + (int) (bczxjd ^ (bczxjd >>> 32));
        result = 31 * result + (ljzxjd != null ? ljzxjd.hashCode() : 0);
        result = 31 * result + (gznr != null ? gznr.hashCode() : 0);
        result = 31 * result + (zhyy != null ? zhyy.hashCode() : 0);
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
