package com.atlp.jzfp.entity.fpgz;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_FPDX_FPC", schema = "JZFP", catalog = "")
public class JzfpBFpdxFpcEntity {
    private String brcid;
    private boolean fpclx;
    private String xzdwid2;
    private String xzdwmc2;
    private String zyzpyy;
    private String qtzpyy;
    private String fpyy;
    private String jjbryy;
    private Long hs;
    private Long rs;
    private Long xzs;
    private Long kzpsr;
    private Long jtjjsr;
    private String jcssqk;
    private String cyfzqk;
    private String ggfwqk;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "BRCID")
    public String getBrcid() {
        return brcid;
    }

    public void setBrcid(String brcid) {
        this.brcid = brcid;
    }

    @Basic
    @Column(name = "FPCLX")
    public boolean isFpclx() {
        return fpclx;
    }

    public void setFpclx(boolean fpclx) {
        this.fpclx = fpclx;
    }

    @Basic
    @Column(name = "XZDWID2")
    public String getXzdwid2() {
        return xzdwid2;
    }

    public void setXzdwid2(String xzdwid2) {
        this.xzdwid2 = xzdwid2;
    }

    @Basic
    @Column(name = "XZDWMC2")
    public String getXzdwmc2() {
        return xzdwmc2;
    }

    public void setXzdwmc2(String xzdwmc2) {
        this.xzdwmc2 = xzdwmc2;
    }

    @Basic
    @Column(name = "ZYZPYY")
    public String getZyzpyy() {
        return zyzpyy;
    }

    public void setZyzpyy(String zyzpyy) {
        this.zyzpyy = zyzpyy;
    }

    @Basic
    @Column(name = "QTZPYY")
    public String getQtzpyy() {
        return qtzpyy;
    }

    public void setQtzpyy(String qtzpyy) {
        this.qtzpyy = qtzpyy;
    }

    @Basic
    @Column(name = "FPYY")
    public String getFpyy() {
        return fpyy;
    }

    public void setFpyy(String fpyy) {
        this.fpyy = fpyy;
    }

    @Basic
    @Column(name = "JJBRYY")
    public String getJjbryy() {
        return jjbryy;
    }

    public void setJjbryy(String jjbryy) {
        this.jjbryy = jjbryy;
    }

    @Basic
    @Column(name = "HS")
    public Long getHs() {
        return hs;
    }

    public void setHs(Long hs) {
        this.hs = hs;
    }

    @Basic
    @Column(name = "RS")
    public Long getRs() {
        return rs;
    }

    public void setRs(Long rs) {
        this.rs = rs;
    }

    @Basic
    @Column(name = "XZS")
    public Long getXzs() {
        return xzs;
    }

    public void setXzs(Long xzs) {
        this.xzs = xzs;
    }

    @Basic
    @Column(name = "KZPSR")
    public Long getKzpsr() {
        return kzpsr;
    }

    public void setKzpsr(Long kzpsr) {
        this.kzpsr = kzpsr;
    }

    @Basic
    @Column(name = "JTJJSR")
    public Long getJtjjsr() {
        return jtjjsr;
    }

    public void setJtjjsr(Long jtjjsr) {
        this.jtjjsr = jtjjsr;
    }

    @Basic
    @Column(name = "JCSSQK")
    public String getJcssqk() {
        return jcssqk;
    }

    public void setJcssqk(String jcssqk) {
        this.jcssqk = jcssqk;
    }

    @Basic
    @Column(name = "CYFZQK")
    public String getCyfzqk() {
        return cyfzqk;
    }

    public void setCyfzqk(String cyfzqk) {
        this.cyfzqk = cyfzqk;
    }

    @Basic
    @Column(name = "GGFWQK")
    public String getGgfwqk() {
        return ggfwqk;
    }

    public void setGgfwqk(String ggfwqk) {
        this.ggfwqk = ggfwqk;
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

        JzfpBFpdxFpcEntity that = (JzfpBFpdxFpcEntity) o;

        if (fpclx != that.fpclx) return false;
        if (brcid != null ? !brcid.equals(that.brcid) : that.brcid != null) return false;
        if (xzdwid2 != null ? !xzdwid2.equals(that.xzdwid2) : that.xzdwid2 != null) return false;
        if (xzdwmc2 != null ? !xzdwmc2.equals(that.xzdwmc2) : that.xzdwmc2 != null) return false;
        if (zyzpyy != null ? !zyzpyy.equals(that.zyzpyy) : that.zyzpyy != null) return false;
        if (qtzpyy != null ? !qtzpyy.equals(that.qtzpyy) : that.qtzpyy != null) return false;
        if (fpyy != null ? !fpyy.equals(that.fpyy) : that.fpyy != null) return false;
        if (jjbryy != null ? !jjbryy.equals(that.jjbryy) : that.jjbryy != null) return false;
        if (hs != null ? !hs.equals(that.hs) : that.hs != null) return false;
        if (rs != null ? !rs.equals(that.rs) : that.rs != null) return false;
        if (xzs != null ? !xzs.equals(that.xzs) : that.xzs != null) return false;
        if (kzpsr != null ? !kzpsr.equals(that.kzpsr) : that.kzpsr != null) return false;
        if (jtjjsr != null ? !jtjjsr.equals(that.jtjjsr) : that.jtjjsr != null) return false;
        if (jcssqk != null ? !jcssqk.equals(that.jcssqk) : that.jcssqk != null) return false;
        if (cyfzqk != null ? !cyfzqk.equals(that.cyfzqk) : that.cyfzqk != null) return false;
        if (ggfwqk != null ? !ggfwqk.equals(that.ggfwqk) : that.ggfwqk != null) return false;
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
        int result = brcid != null ? brcid.hashCode() : 0;
        result = 31 * result + (fpclx ? 1 : 0);
        result = 31 * result + (xzdwid2 != null ? xzdwid2.hashCode() : 0);
        result = 31 * result + (xzdwmc2 != null ? xzdwmc2.hashCode() : 0);
        result = 31 * result + (zyzpyy != null ? zyzpyy.hashCode() : 0);
        result = 31 * result + (qtzpyy != null ? qtzpyy.hashCode() : 0);
        result = 31 * result + (fpyy != null ? fpyy.hashCode() : 0);
        result = 31 * result + (jjbryy != null ? jjbryy.hashCode() : 0);
        result = 31 * result + (hs != null ? hs.hashCode() : 0);
        result = 31 * result + (rs != null ? rs.hashCode() : 0);
        result = 31 * result + (xzs != null ? xzs.hashCode() : 0);
        result = 31 * result + (kzpsr != null ? kzpsr.hashCode() : 0);
        result = 31 * result + (jtjjsr != null ? jtjjsr.hashCode() : 0);
        result = 31 * result + (jcssqk != null ? jcssqk.hashCode() : 0);
        result = 31 * result + (cyfzqk != null ? cyfzqk.hashCode() : 0);
        result = 31 * result + (ggfwqk != null ? ggfwqk.hashCode() : 0);
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
