package com.atlp.jzfp.entity.fpxm;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_XM_XX", schema = "JZFP", catalog = "")
public class JzfpBXmXxEntity {
    private String xmid;
    private String xmmc;
    private String xmjc;
    private String dlid;
    private String dlmc;
    private String xlid;
    private String xlmc;
    private Long zje;
    private Long ysje;
    private String szx;
    private String szxz;
    private String zgbmid;
    private String zgbmmc;
    private String zrrid;
    private String zrrmc;
    private String xmzcy;
    private String xmmb;
    private String jsnr;
    private String xmdd;
    private Time kssj;
    private Time jssj;
    private Boolean xmlb;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
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
    @Column(name = "DLID")
    public String getDlid() {
        return dlid;
    }

    public void setDlid(String dlid) {
        this.dlid = dlid;
    }

    @Basic
    @Column(name = "DLMC")
    public String getDlmc() {
        return dlmc;
    }

    public void setDlmc(String dlmc) {
        this.dlmc = dlmc;
    }

    @Basic
    @Column(name = "XLID")
    public String getXlid() {
        return xlid;
    }

    public void setXlid(String xlid) {
        this.xlid = xlid;
    }

    @Basic
    @Column(name = "XLMC")
    public String getXlmc() {
        return xlmc;
    }

    public void setXlmc(String xlmc) {
        this.xlmc = xlmc;
    }

    @Basic
    @Column(name = "ZJE")
    public Long getZje() {
        return zje;
    }

    public void setZje(Long zje) {
        this.zje = zje;
    }

    @Basic
    @Column(name = "YSJE")
    public Long getYsje() {
        return ysje;
    }

    public void setYsje(Long ysje) {
        this.ysje = ysje;
    }

    @Basic
    @Column(name = "SZX")
    public String getSzx() {
        return szx;
    }

    public void setSzx(String szx) {
        this.szx = szx;
    }

    @Basic
    @Column(name = "SZXZ")
    public String getSzxz() {
        return szxz;
    }

    public void setSzxz(String szxz) {
        this.szxz = szxz;
    }

    @Basic
    @Column(name = "ZGBMID")
    public String getZgbmid() {
        return zgbmid;
    }

    public void setZgbmid(String zgbmid) {
        this.zgbmid = zgbmid;
    }

    @Basic
    @Column(name = "ZGBMMC")
    public String getZgbmmc() {
        return zgbmmc;
    }

    public void setZgbmmc(String zgbmmc) {
        this.zgbmmc = zgbmmc;
    }

    @Basic
    @Column(name = "ZRRID")
    public String getZrrid() {
        return zrrid;
    }

    public void setZrrid(String zrrid) {
        this.zrrid = zrrid;
    }

    @Basic
    @Column(name = "ZRRMC")
    public String getZrrmc() {
        return zrrmc;
    }

    public void setZrrmc(String zrrmc) {
        this.zrrmc = zrrmc;
    }

    @Basic
    @Column(name = "XMZCY")
    public String getXmzcy() {
        return xmzcy;
    }

    public void setXmzcy(String xmzcy) {
        this.xmzcy = xmzcy;
    }

    @Basic
    @Column(name = "XMMB")
    public String getXmmb() {
        return xmmb;
    }

    public void setXmmb(String xmmb) {
        this.xmmb = xmmb;
    }

    @Basic
    @Column(name = "JSNR")
    public String getJsnr() {
        return jsnr;
    }

    public void setJsnr(String jsnr) {
        this.jsnr = jsnr;
    }

    @Basic
    @Column(name = "XMDD")
    public String getXmdd() {
        return xmdd;
    }

    public void setXmdd(String xmdd) {
        this.xmdd = xmdd;
    }

    @Basic
    @Column(name = "KSSJ")
    public Time getKssj() {
        return kssj;
    }

    public void setKssj(Time kssj) {
        this.kssj = kssj;
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
    @Column(name = "XMLB")
    public Boolean getXmlb() {
        return xmlb;
    }

    public void setXmlb(Boolean xmlb) {
        this.xmlb = xmlb;
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

        JzfpBXmXxEntity that = (JzfpBXmXxEntity) o;

        if (xmid != null ? !xmid.equals(that.xmid) : that.xmid != null) return false;
        if (xmmc != null ? !xmmc.equals(that.xmmc) : that.xmmc != null) return false;
        if (xmjc != null ? !xmjc.equals(that.xmjc) : that.xmjc != null) return false;
        if (dlid != null ? !dlid.equals(that.dlid) : that.dlid != null) return false;
        if (dlmc != null ? !dlmc.equals(that.dlmc) : that.dlmc != null) return false;
        if (xlid != null ? !xlid.equals(that.xlid) : that.xlid != null) return false;
        if (xlmc != null ? !xlmc.equals(that.xlmc) : that.xlmc != null) return false;
        if (zje != null ? !zje.equals(that.zje) : that.zje != null) return false;
        if (ysje != null ? !ysje.equals(that.ysje) : that.ysje != null) return false;
        if (szx != null ? !szx.equals(that.szx) : that.szx != null) return false;
        if (szxz != null ? !szxz.equals(that.szxz) : that.szxz != null) return false;
        if (zgbmid != null ? !zgbmid.equals(that.zgbmid) : that.zgbmid != null) return false;
        if (zgbmmc != null ? !zgbmmc.equals(that.zgbmmc) : that.zgbmmc != null) return false;
        if (zrrid != null ? !zrrid.equals(that.zrrid) : that.zrrid != null) return false;
        if (zrrmc != null ? !zrrmc.equals(that.zrrmc) : that.zrrmc != null) return false;
        if (xmzcy != null ? !xmzcy.equals(that.xmzcy) : that.xmzcy != null) return false;
        if (xmmb != null ? !xmmb.equals(that.xmmb) : that.xmmb != null) return false;
        if (jsnr != null ? !jsnr.equals(that.jsnr) : that.jsnr != null) return false;
        if (xmdd != null ? !xmdd.equals(that.xmdd) : that.xmdd != null) return false;
        if (kssj != null ? !kssj.equals(that.kssj) : that.kssj != null) return false;
        if (jssj != null ? !jssj.equals(that.jssj) : that.jssj != null) return false;
        if (xmlb != null ? !xmlb.equals(that.xmlb) : that.xmlb != null) return false;
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
        int result = xmid != null ? xmid.hashCode() : 0;
        result = 31 * result + (xmmc != null ? xmmc.hashCode() : 0);
        result = 31 * result + (xmjc != null ? xmjc.hashCode() : 0);
        result = 31 * result + (dlid != null ? dlid.hashCode() : 0);
        result = 31 * result + (dlmc != null ? dlmc.hashCode() : 0);
        result = 31 * result + (xlid != null ? xlid.hashCode() : 0);
        result = 31 * result + (xlmc != null ? xlmc.hashCode() : 0);
        result = 31 * result + (zje != null ? zje.hashCode() : 0);
        result = 31 * result + (ysje != null ? ysje.hashCode() : 0);
        result = 31 * result + (szx != null ? szx.hashCode() : 0);
        result = 31 * result + (szxz != null ? szxz.hashCode() : 0);
        result = 31 * result + (zgbmid != null ? zgbmid.hashCode() : 0);
        result = 31 * result + (zgbmmc != null ? zgbmmc.hashCode() : 0);
        result = 31 * result + (zrrid != null ? zrrid.hashCode() : 0);
        result = 31 * result + (zrrmc != null ? zrrmc.hashCode() : 0);
        result = 31 * result + (xmzcy != null ? xmzcy.hashCode() : 0);
        result = 31 * result + (xmmb != null ? xmmb.hashCode() : 0);
        result = 31 * result + (jsnr != null ? jsnr.hashCode() : 0);
        result = 31 * result + (xmdd != null ? xmdd.hashCode() : 0);
        result = 31 * result + (kssj != null ? kssj.hashCode() : 0);
        result = 31 * result + (jssj != null ? jssj.hashCode() : 0);
        result = 31 * result + (xmlb != null ? xmlb.hashCode() : 0);
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
