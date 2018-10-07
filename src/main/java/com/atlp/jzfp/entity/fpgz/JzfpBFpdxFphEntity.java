package com.atlp.jzfp.entity.fpgz;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_FPDX_FPH", schema = "JZFP", catalog = "")
public class JzfpBFpdxFphEntity {
    private String fphid;
    private boolean fphlx;
    private String nd;
    private String xzdwid;
    private String xzdwmc;
    private String cbdwid;
    private String cbdwmc;
    private String sbdwid;
    private String sbdwmc;
    private String xm;
    private Byte nl;
    private String sbbzdl;
    private String sbbzxl;
    private String pkhsx;
    private String jtqk;
    private String zyzpyy;
    private String qtzpyy;
    private String fpyy;
    private String fpfxd;
    private String jjbf;
    private Long kzpsr;
    private String cylx;
    private String lxfs;
    private String sfzh;
    private String yh;
    private String yhkh;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "FPHID")
    public String getFphid() {
        return fphid;
    }

    public void setFphid(String fphid) {
        this.fphid = fphid;
    }

    @Basic
    @Column(name = "FPHLX")
    public boolean isFphlx() {
        return fphlx;
    }

    public void setFphlx(boolean fphlx) {
        this.fphlx = fphlx;
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
    @Column(name = "XZDWID")
    public String getXzdwid() {
        return xzdwid;
    }

    public void setXzdwid(String xzdwid) {
        this.xzdwid = xzdwid;
    }

    @Basic
    @Column(name = "XZDWMC")
    public String getXzdwmc() {
        return xzdwmc;
    }

    public void setXzdwmc(String xzdwmc) {
        this.xzdwmc = xzdwmc;
    }

    @Basic
    @Column(name = "CBDWID")
    public String getCbdwid() {
        return cbdwid;
    }

    public void setCbdwid(String cbdwid) {
        this.cbdwid = cbdwid;
    }

    @Basic
    @Column(name = "CBDWMC")
    public String getCbdwmc() {
        return cbdwmc;
    }

    public void setCbdwmc(String cbdwmc) {
        this.cbdwmc = cbdwmc;
    }

    @Basic
    @Column(name = "SBDWID")
    public String getSbdwid() {
        return sbdwid;
    }

    public void setSbdwid(String sbdwid) {
        this.sbdwid = sbdwid;
    }

    @Basic
    @Column(name = "SBDWMC")
    public String getSbdwmc() {
        return sbdwmc;
    }

    public void setSbdwmc(String sbdwmc) {
        this.sbdwmc = sbdwmc;
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
    @Column(name = "NL")
    public Byte getNl() {
        return nl;
    }

    public void setNl(Byte nl) {
        this.nl = nl;
    }

    @Basic
    @Column(name = "SBBZDL")
    public String getSbbzdl() {
        return sbbzdl;
    }

    public void setSbbzdl(String sbbzdl) {
        this.sbbzdl = sbbzdl;
    }

    @Basic
    @Column(name = "SBBZXL")
    public String getSbbzxl() {
        return sbbzxl;
    }

    public void setSbbzxl(String sbbzxl) {
        this.sbbzxl = sbbzxl;
    }

    @Basic
    @Column(name = "PKHSX")
    public String getPkhsx() {
        return pkhsx;
    }

    public void setPkhsx(String pkhsx) {
        this.pkhsx = pkhsx;
    }

    @Basic
    @Column(name = "JTQK")
    public String getJtqk() {
        return jtqk;
    }

    public void setJtqk(String jtqk) {
        this.jtqk = jtqk;
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
    @Column(name = "FPFXD")
    public String getFpfxd() {
        return fpfxd;
    }

    public void setFpfxd(String fpfxd) {
        this.fpfxd = fpfxd;
    }

    @Basic
    @Column(name = "JJBF")
    public String getJjbf() {
        return jjbf;
    }

    public void setJjbf(String jjbf) {
        this.jjbf = jjbf;
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
    @Column(name = "CYLX")
    public String getCylx() {
        return cylx;
    }

    public void setCylx(String cylx) {
        this.cylx = cylx;
    }

    @Basic
    @Column(name = "LXFS")
    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
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
    @Column(name = "YH")
    public String getYh() {
        return yh;
    }

    public void setYh(String yh) {
        this.yh = yh;
    }

    @Basic
    @Column(name = "YHKH")
    public String getYhkh() {
        return yhkh;
    }

    public void setYhkh(String yhkh) {
        this.yhkh = yhkh;
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

        JzfpBFpdxFphEntity that = (JzfpBFpdxFphEntity) o;

        if (fphlx != that.fphlx) return false;
        if (fphid != null ? !fphid.equals(that.fphid) : that.fphid != null) return false;
        if (nd != null ? !nd.equals(that.nd) : that.nd != null) return false;
        if (xzdwid != null ? !xzdwid.equals(that.xzdwid) : that.xzdwid != null) return false;
        if (xzdwmc != null ? !xzdwmc.equals(that.xzdwmc) : that.xzdwmc != null) return false;
        if (cbdwid != null ? !cbdwid.equals(that.cbdwid) : that.cbdwid != null) return false;
        if (cbdwmc != null ? !cbdwmc.equals(that.cbdwmc) : that.cbdwmc != null) return false;
        if (sbdwid != null ? !sbdwid.equals(that.sbdwid) : that.sbdwid != null) return false;
        if (sbdwmc != null ? !sbdwmc.equals(that.sbdwmc) : that.sbdwmc != null) return false;
        if (xm != null ? !xm.equals(that.xm) : that.xm != null) return false;
        if (nl != null ? !nl.equals(that.nl) : that.nl != null) return false;
        if (sbbzdl != null ? !sbbzdl.equals(that.sbbzdl) : that.sbbzdl != null) return false;
        if (sbbzxl != null ? !sbbzxl.equals(that.sbbzxl) : that.sbbzxl != null) return false;
        if (pkhsx != null ? !pkhsx.equals(that.pkhsx) : that.pkhsx != null) return false;
        if (jtqk != null ? !jtqk.equals(that.jtqk) : that.jtqk != null) return false;
        if (zyzpyy != null ? !zyzpyy.equals(that.zyzpyy) : that.zyzpyy != null) return false;
        if (qtzpyy != null ? !qtzpyy.equals(that.qtzpyy) : that.qtzpyy != null) return false;
        if (fpyy != null ? !fpyy.equals(that.fpyy) : that.fpyy != null) return false;
        if (fpfxd != null ? !fpfxd.equals(that.fpfxd) : that.fpfxd != null) return false;
        if (jjbf != null ? !jjbf.equals(that.jjbf) : that.jjbf != null) return false;
        if (kzpsr != null ? !kzpsr.equals(that.kzpsr) : that.kzpsr != null) return false;
        if (cylx != null ? !cylx.equals(that.cylx) : that.cylx != null) return false;
        if (lxfs != null ? !lxfs.equals(that.lxfs) : that.lxfs != null) return false;
        if (sfzh != null ? !sfzh.equals(that.sfzh) : that.sfzh != null) return false;
        if (yh != null ? !yh.equals(that.yh) : that.yh != null) return false;
        if (yhkh != null ? !yhkh.equals(that.yhkh) : that.yhkh != null) return false;
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
        int result = fphid != null ? fphid.hashCode() : 0;
        result = 31 * result + (fphlx ? 1 : 0);
        result = 31 * result + (nd != null ? nd.hashCode() : 0);
        result = 31 * result + (xzdwid != null ? xzdwid.hashCode() : 0);
        result = 31 * result + (xzdwmc != null ? xzdwmc.hashCode() : 0);
        result = 31 * result + (cbdwid != null ? cbdwid.hashCode() : 0);
        result = 31 * result + (cbdwmc != null ? cbdwmc.hashCode() : 0);
        result = 31 * result + (sbdwid != null ? sbdwid.hashCode() : 0);
        result = 31 * result + (sbdwmc != null ? sbdwmc.hashCode() : 0);
        result = 31 * result + (xm != null ? xm.hashCode() : 0);
        result = 31 * result + (nl != null ? nl.hashCode() : 0);
        result = 31 * result + (sbbzdl != null ? sbbzdl.hashCode() : 0);
        result = 31 * result + (sbbzxl != null ? sbbzxl.hashCode() : 0);
        result = 31 * result + (pkhsx != null ? pkhsx.hashCode() : 0);
        result = 31 * result + (jtqk != null ? jtqk.hashCode() : 0);
        result = 31 * result + (zyzpyy != null ? zyzpyy.hashCode() : 0);
        result = 31 * result + (qtzpyy != null ? qtzpyy.hashCode() : 0);
        result = 31 * result + (fpyy != null ? fpyy.hashCode() : 0);
        result = 31 * result + (fpfxd != null ? fpfxd.hashCode() : 0);
        result = 31 * result + (jjbf != null ? jjbf.hashCode() : 0);
        result = 31 * result + (kzpsr != null ? kzpsr.hashCode() : 0);
        result = 31 * result + (cylx != null ? cylx.hashCode() : 0);
        result = 31 * result + (lxfs != null ? lxfs.hashCode() : 0);
        result = 31 * result + (sfzh != null ? sfzh.hashCode() : 0);
        result = 31 * result + (yh != null ? yh.hashCode() : 0);
        result = 31 * result + (yhkh != null ? yhkh.hashCode() : 0);
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
