package com.atlp.jzfp.entity.fpzj;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_ZJ_XB", schema = "JZFP", catalog = "")
public class JzfpBZjXbEntity {
    private String dzid;
    private String xbdwid;
    private String xbdwmc;
    private Time xbsj;
    private long xbje;
    private String jsdwid;
    private String jsdwmc;
    private Time jssj;
    private String nd;
    private String yd;
    private String pc;
    private String zjlx;
    private String fwbh;
    private String fwmc;
    private String slkm;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "DZID")
    public String getDzid() {
        return dzid;
    }

    public void setDzid(String dzid) {
        this.dzid = dzid;
    }

    @Basic
    @Column(name = "XBDWID")
    public String getXbdwid() {
        return xbdwid;
    }

    public void setXbdwid(String xbdwid) {
        this.xbdwid = xbdwid;
    }

    @Basic
    @Column(name = "XBDWMC")
    public String getXbdwmc() {
        return xbdwmc;
    }

    public void setXbdwmc(String xbdwmc) {
        this.xbdwmc = xbdwmc;
    }

    @Basic
    @Column(name = "XBSJ")
    public Time getXbsj() {
        return xbsj;
    }

    public void setXbsj(Time xbsj) {
        this.xbsj = xbsj;
    }

    @Basic
    @Column(name = "XBJE")
    public long getXbje() {
        return xbje;
    }

    public void setXbje(long xbje) {
        this.xbje = xbje;
    }

    @Basic
    @Column(name = "JSDWID")
    public String getJsdwid() {
        return jsdwid;
    }

    public void setJsdwid(String jsdwid) {
        this.jsdwid = jsdwid;
    }

    @Basic
    @Column(name = "JSDWMC")
    public String getJsdwmc() {
        return jsdwmc;
    }

    public void setJsdwmc(String jsdwmc) {
        this.jsdwmc = jsdwmc;
    }

    @Basic
    @Column(name = "JSSJ")
    public Time getJssj() {
        return jssj;
    }

    public void setJssj(Time jssj) {
        this.jssj = jssj;
    }

    @Basic
    @Column(name = "ND")
    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    @Basic
    @Column(name = "YD")
    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    @Basic
    @Column(name = "PC")
    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    @Basic
    @Column(name = "ZJLX")
    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    @Basic
    @Column(name = "FWBH")
    public String getFwbh() {
        return fwbh;
    }

    public void setFwbh(String fwbh) {
        this.fwbh = fwbh;
    }

    @Basic
    @Column(name = "FWMC")
    public String getFwmc() {
        return fwmc;
    }

    public void setFwmc(String fwmc) {
        this.fwmc = fwmc;
    }

    @Basic
    @Column(name = "SLKM")
    public String getSlkm() {
        return slkm;
    }

    public void setSlkm(String slkm) {
        this.slkm = slkm;
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

        JzfpBZjXbEntity that = (JzfpBZjXbEntity) o;

        if (xbje != that.xbje) return false;
        if (dzid != null ? !dzid.equals(that.dzid) : that.dzid != null) return false;
        if (xbdwid != null ? !xbdwid.equals(that.xbdwid) : that.xbdwid != null) return false;
        if (xbdwmc != null ? !xbdwmc.equals(that.xbdwmc) : that.xbdwmc != null) return false;
        if (xbsj != null ? !xbsj.equals(that.xbsj) : that.xbsj != null) return false;
        if (jsdwid != null ? !jsdwid.equals(that.jsdwid) : that.jsdwid != null) return false;
        if (jsdwmc != null ? !jsdwmc.equals(that.jsdwmc) : that.jsdwmc != null) return false;
        if (jssj != null ? !jssj.equals(that.jssj) : that.jssj != null) return false;
        if (nd != null ? !nd.equals(that.nd) : that.nd != null) return false;
        if (yd != null ? !yd.equals(that.yd) : that.yd != null) return false;
        if (pc != null ? !pc.equals(that.pc) : that.pc != null) return false;
        if (zjlx != null ? !zjlx.equals(that.zjlx) : that.zjlx != null) return false;
        if (fwbh != null ? !fwbh.equals(that.fwbh) : that.fwbh != null) return false;
        if (fwmc != null ? !fwmc.equals(that.fwmc) : that.fwmc != null) return false;
        if (slkm != null ? !slkm.equals(that.slkm) : that.slkm != null) return false;
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
        int result = dzid != null ? dzid.hashCode() : 0;
        result = 31 * result + (xbdwid != null ? xbdwid.hashCode() : 0);
        result = 31 * result + (xbdwmc != null ? xbdwmc.hashCode() : 0);
        result = 31 * result + (xbsj != null ? xbsj.hashCode() : 0);
        result = 31 * result + (int) (xbje ^ (xbje >>> 32));
        result = 31 * result + (jsdwid != null ? jsdwid.hashCode() : 0);
        result = 31 * result + (jsdwmc != null ? jsdwmc.hashCode() : 0);
        result = 31 * result + (jssj != null ? jssj.hashCode() : 0);
        result = 31 * result + (nd != null ? nd.hashCode() : 0);
        result = 31 * result + (yd != null ? yd.hashCode() : 0);
        result = 31 * result + (pc != null ? pc.hashCode() : 0);
        result = 31 * result + (zjlx != null ? zjlx.hashCode() : 0);
        result = 31 * result + (fwbh != null ? fwbh.hashCode() : 0);
        result = 31 * result + (fwmc != null ? fwmc.hashCode() : 0);
        result = 31 * result + (slkm != null ? slkm.hashCode() : 0);
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
