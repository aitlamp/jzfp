package com.atlp.jzfp.entity.fpzj;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_ZJ_DZ", schema = "JZFP", catalog = "")
public class JzfpBZjDzEntity {
    private String dzid;
    private String lyid;
    private String lymc;
    private Time sjxbsj;
    private Time dzsj;
    private long dzje;
    private String nd;
    private String yd;
    private String zjlx;
    private String fwbh;
    private String fwmc;
    private String pc;
    private String zjbfly;
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
    @Column(name = "LYID")
    public String getLyid() {
        return lyid;
    }

    public void setLyid(String lyid) {
        this.lyid = lyid;
    }

    @Basic
    @Column(name = "LYMC")
    public String getLymc() {
        return lymc;
    }

    public void setLymc(String lymc) {
        this.lymc = lymc;
    }

    @Basic
    @Column(name = "SJXBSJ")
    public Time getSjxbsj() {
        return sjxbsj;
    }

    public void setSjxbsj(Time sjxbsj) {
        this.sjxbsj = sjxbsj;
    }

    @Basic
    @Column(name = "DZSJ")
    public Time getDzsj() {
        return dzsj;
    }

    public void setDzsj(Time dzsj) {
        this.dzsj = dzsj;
    }

    @Basic
    @Column(name = "DZJE")
    public long getDzje() {
        return dzje;
    }

    public void setDzje(long dzje) {
        this.dzje = dzje;
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
    @Column(name = "PC")
    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    @Basic
    @Column(name = "ZJBFLY")
    public String getZjbfly() {
        return zjbfly;
    }

    public void setZjbfly(String zjbfly) {
        this.zjbfly = zjbfly;
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

        JzfpBZjDzEntity that = (JzfpBZjDzEntity) o;

        if (dzje != that.dzje) return false;
        if (dzid != null ? !dzid.equals(that.dzid) : that.dzid != null) return false;
        if (lyid != null ? !lyid.equals(that.lyid) : that.lyid != null) return false;
        if (lymc != null ? !lymc.equals(that.lymc) : that.lymc != null) return false;
        if (sjxbsj != null ? !sjxbsj.equals(that.sjxbsj) : that.sjxbsj != null) return false;
        if (dzsj != null ? !dzsj.equals(that.dzsj) : that.dzsj != null) return false;
        if (nd != null ? !nd.equals(that.nd) : that.nd != null) return false;
        if (yd != null ? !yd.equals(that.yd) : that.yd != null) return false;
        if (zjlx != null ? !zjlx.equals(that.zjlx) : that.zjlx != null) return false;
        if (fwbh != null ? !fwbh.equals(that.fwbh) : that.fwbh != null) return false;
        if (fwmc != null ? !fwmc.equals(that.fwmc) : that.fwmc != null) return false;
        if (pc != null ? !pc.equals(that.pc) : that.pc != null) return false;
        if (zjbfly != null ? !zjbfly.equals(that.zjbfly) : that.zjbfly != null) return false;
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
        result = 31 * result + (lyid != null ? lyid.hashCode() : 0);
        result = 31 * result + (lymc != null ? lymc.hashCode() : 0);
        result = 31 * result + (sjxbsj != null ? sjxbsj.hashCode() : 0);
        result = 31 * result + (dzsj != null ? dzsj.hashCode() : 0);
        result = 31 * result + (int) (dzje ^ (dzje >>> 32));
        result = 31 * result + (nd != null ? nd.hashCode() : 0);
        result = 31 * result + (yd != null ? yd.hashCode() : 0);
        result = 31 * result + (zjlx != null ? zjlx.hashCode() : 0);
        result = 31 * result + (fwbh != null ? fwbh.hashCode() : 0);
        result = 31 * result + (fwmc != null ? fwmc.hashCode() : 0);
        result = 31 * result + (pc != null ? pc.hashCode() : 0);
        result = 31 * result + (zjbfly != null ? zjbfly.hashCode() : 0);
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
