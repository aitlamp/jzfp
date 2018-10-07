package com.atlp.jzfp.entity.fpgz;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_FPDX_FPH_CY", schema = "JZFP", catalog = "")
public class JzfpBFpdxFphCyEntity {
    private String cyid;
    private String fphid;
    private String ssdwid;
    private String ssdwmc;
    private String xz;
    private String cb;
    private String sb;
    private String hzxm;
    private String xm;
    private String xb;
    private byte nl;
    private String sfzh;
    private String mz;
    private String dwid;
    private String zzmm;
    private String xl;
    private Time csrq;
    private String jg;
    private String sjh;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid2;
    private String yhxm2;
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
    @Column(name = "FPHID")
    public String getFphid() {
        return fphid;
    }

    public void setFphid(String fphid) {
        this.fphid = fphid;
    }

    @Basic
    @Column(name = "SSDWID")
    public String getSsdwid() {
        return ssdwid;
    }

    public void setSsdwid(String ssdwid) {
        this.ssdwid = ssdwid;
    }

    @Basic
    @Column(name = "SSDWMC")
    public String getSsdwmc() {
        return ssdwmc;
    }

    public void setSsdwmc(String ssdwmc) {
        this.ssdwmc = ssdwmc;
    }

    @Basic
    @Column(name = "XZ")
    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }

    @Basic
    @Column(name = "CB")
    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    @Basic
    @Column(name = "SB")
    public String getSb() {
        return sb;
    }

    public void setSb(String sb) {
        this.sb = sb;
    }

    @Basic
    @Column(name = "HZXM")
    public String getHzxm() {
        return hzxm;
    }

    public void setHzxm(String hzxm) {
        this.hzxm = hzxm;
    }

    @Basic
    @Column(name = "XM")
    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    @Basic
    @Column(name = "XB")
    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    @Basic
    @Column(name = "NL")
    public byte getNl() {
        return nl;
    }

    public void setNl(byte nl) {
        this.nl = nl;
    }

    @Basic
    @Column(name = "SFZH")
    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    @Basic
    @Column(name = "MZ")
    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
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
    @Column(name = "ZZMM")
    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    @Basic
    @Column(name = "XL")
    public String getXl() {
        return xl;
    }

    public void setXl(String xl) {
        this.xl = xl;
    }

    @Basic
    @Column(name = "CSRQ")
    public Time getCsrq() {
        return csrq;
    }

    public void setCsrq(Time csrq) {
        this.csrq = csrq;
    }

    @Basic
    @Column(name = "JG")
    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    @Basic
    @Column(name = "SJH")
    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
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
    @Column(name = "YHID2")
    public String getYhid2() {
        return yhid2;
    }

    public void setYhid2(String yhid2) {
        this.yhid2 = yhid2;
    }

    @Basic
    @Column(name = "YHXM2")
    public String getYhxm2() {
        return yhxm2;
    }

    public void setYhxm2(String yhxm2) {
        this.yhxm2 = yhxm2;
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

        JzfpBFpdxFphCyEntity that = (JzfpBFpdxFphCyEntity) o;

        if (nl != that.nl) return false;
        if (cyid != null ? !cyid.equals(that.cyid) : that.cyid != null) return false;
        if (fphid != null ? !fphid.equals(that.fphid) : that.fphid != null) return false;
        if (ssdwid != null ? !ssdwid.equals(that.ssdwid) : that.ssdwid != null) return false;
        if (ssdwmc != null ? !ssdwmc.equals(that.ssdwmc) : that.ssdwmc != null) return false;
        if (xz != null ? !xz.equals(that.xz) : that.xz != null) return false;
        if (cb != null ? !cb.equals(that.cb) : that.cb != null) return false;
        if (sb != null ? !sb.equals(that.sb) : that.sb != null) return false;
        if (hzxm != null ? !hzxm.equals(that.hzxm) : that.hzxm != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (xb != null ? !xb.equals(that.xb) : that.xb != null) return false;
        if (sfzh != null ? !sfzh.equals(that.sfzh) : that.sfzh != null) return false;
        if (mz != null ? !mz.equals(that.mz) : that.mz != null) return false;
        if (dwid != null ? !dwid.equals(that.dwid) : that.dwid != null) return false;
        if (zzmm != null ? !zzmm.equals(that.zzmm) : that.zzmm != null) return false;
        if (xl != null ? !xl.equals(that.xl) : that.xl != null) return false;
        if (csrq != null ? !csrq.equals(that.csrq) : that.csrq != null) return false;
        if (jg != null ? !jg.equals(that.jg) : that.jg != null) return false;
        if (sjh != null ? !sjh.equals(that.sjh) : that.sjh != null) return false;
        if (sm != null ? !sm.equals(that.sm) : that.sm != null) return false;
        if (dqzt != null ? !dqzt.equals(that.dqzt) : that.dqzt != null) return false;
        if (firsttime != null ? !firsttime.equals(that.firsttime) : that.firsttime != null) return false;
        if (lasttime != null ? !lasttime.equals(that.lasttime) : that.lasttime != null) return false;
        if (yhid2 != null ? !yhid2.equals(that.yhid2) : that.yhid2 != null) return false;
        if (yhxm2 != null ? !yhxm2.equals(that.yhxm2) : that.yhxm2 != null) return false;
        if (yhdwid != null ? !yhdwid.equals(that.yhdwid) : that.yhdwid != null) return false;
        if (yhdwmc != null ? !yhdwmc.equals(that.yhdwmc) : that.yhdwmc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cyid != null ? cyid.hashCode() : 0;
        result = 31 * result + (fphid != null ? fphid.hashCode() : 0);
        result = 31 * result + (ssdwid != null ? ssdwid.hashCode() : 0);
        result = 31 * result + (ssdwmc != null ? ssdwmc.hashCode() : 0);
        result = 31 * result + (xz != null ? xz.hashCode() : 0);
        result = 31 * result + (cb != null ? cb.hashCode() : 0);
        result = 31 * result + (sb != null ? sb.hashCode() : 0);
        result = 31 * result + (hzxm != null ? hzxm.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (xb != null ? xb.hashCode() : 0);
        result = 31 * result + (int) nl;
        result = 31 * result + (sfzh != null ? sfzh.hashCode() : 0);
        result = 31 * result + (mz != null ? mz.hashCode() : 0);
        result = 31 * result + (dwid != null ? dwid.hashCode() : 0);
        result = 31 * result + (zzmm != null ? zzmm.hashCode() : 0);
        result = 31 * result + (xl != null ? xl.hashCode() : 0);
        result = 31 * result + (csrq != null ? csrq.hashCode() : 0);
        result = 31 * result + (jg != null ? jg.hashCode() : 0);
        result = 31 * result + (sjh != null ? sjh.hashCode() : 0);
        result = 31 * result + (sm != null ? sm.hashCode() : 0);
        result = 31 * result + (dqzt != null ? dqzt.hashCode() : 0);
        result = 31 * result + (firsttime != null ? firsttime.hashCode() : 0);
        result = 31 * result + (lasttime != null ? lasttime.hashCode() : 0);
        result = 31 * result + (yhid2 != null ? yhid2.hashCode() : 0);
        result = 31 * result + (yhxm2 != null ? yhxm2.hashCode() : 0);
        result = 31 * result + (yhdwid != null ? yhdwid.hashCode() : 0);
        result = 31 * result + (yhdwmc != null ? yhdwmc.hashCode() : 0);
        return result;
    }
}
