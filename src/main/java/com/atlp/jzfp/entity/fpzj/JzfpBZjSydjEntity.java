package com.atlp.jzfp.entity.fpzj;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_ZJ_SYDJ", schema = "JZFP", catalog = "")
public class JzfpBZjSydjEntity {
    private String djid;
    private String sydwid;
    private String sydwmc;
    private String zjyt;
    private long syje;
    private Time djsj;
    private String xmid;
    private String xmmc;
    private String xmjc;
    private String nd;
    private String yd;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "DJID")
    public String getDjid() {
        return djid;
    }

    public void setDjid(String djid) {
        this.djid = djid;
    }

    @Basic
    @Column(name = "SYDWID")
    public String getSydwid() {
        return sydwid;
    }

    public void setSydwid(String sydwid) {
        this.sydwid = sydwid;
    }

    @Basic
    @Column(name = "SYDWMC")
    public String getSydwmc() {
        return sydwmc;
    }

    public void setSydwmc(String sydwmc) {
        this.sydwmc = sydwmc;
    }

    @Basic
    @Column(name = "ZJYT")
    public String getZjyt() {
        return zjyt;
    }

    public void setZjyt(String zjyt) {
        this.zjyt = zjyt;
    }

    @Basic
    @Column(name = "SYJE")
    public long getSyje() {
        return syje;
    }

    public void setSyje(long syje) {
        this.syje = syje;
    }

    @Basic
    @Column(name = "DJSJ")
    public Time getDjsj() {
        return djsj;
    }

    public void setDjsj(Time djsj) {
        this.djsj = djsj;
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
    @Column(name = "XMMC")
    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    @Basic
    @Column(name = "XMJC")
    public String getXmjc() {
        return xmjc;
    }

    public void setXmjc(String xmjc) {
        this.xmjc = xmjc;
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

        JzfpBZjSydjEntity that = (JzfpBZjSydjEntity) o;

        if (syje != that.syje) return false;
        if (djid != null ? !djid.equals(that.djid) : that.djid != null) return false;
        if (sydwid != null ? !sydwid.equals(that.sydwid) : that.sydwid != null) return false;
        if (sydwmc != null ? !sydwmc.equals(that.sydwmc) : that.sydwmc != null) return false;
        if (zjyt != null ? !zjyt.equals(that.zjyt) : that.zjyt != null) return false;
        if (djsj != null ? !djsj.equals(that.djsj) : that.djsj != null) return false;
        if (xmid != null ? !xmid.equals(that.xmid) : that.xmid != null) return false;
        if (xmmc != null ? !xmmc.equals(that.xmmc) : that.xmmc != null) return false;
        if (xmjc != null ? !xmjc.equals(that.xmjc) : that.xmjc != null) return false;
        if (nd != null ? !nd.equals(that.nd) : that.nd != null) return false;
        if (yd != null ? !yd.equals(that.yd) : that.yd != null) return false;
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
        int result = djid != null ? djid.hashCode() : 0;
        result = 31 * result + (sydwid != null ? sydwid.hashCode() : 0);
        result = 31 * result + (sydwmc != null ? sydwmc.hashCode() : 0);
        result = 31 * result + (zjyt != null ? zjyt.hashCode() : 0);
        result = 31 * result + (int) (syje ^ (syje >>> 32));
        result = 31 * result + (djsj != null ? djsj.hashCode() : 0);
        result = 31 * result + (xmid != null ? xmid.hashCode() : 0);
        result = 31 * result + (xmmc != null ? xmmc.hashCode() : 0);
        result = 31 * result + (xmjc != null ? xmjc.hashCode() : 0);
        result = 31 * result + (nd != null ? nd.hashCode() : 0);
        result = 31 * result + (yd != null ? yd.hashCode() : 0);
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
