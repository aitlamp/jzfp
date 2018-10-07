package com.atlp.jzfp.entity.fpxm;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_XM_YS", schema = "JZFP", catalog = "")
public class JzfpBXmYsEntity {
    private String ysid;
    private String xmid;
    private Time wcsj;
    private long jsje;
    private long jyje;
    private Time yssj;
    private String fhrid;
    private String fhrxm;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "YSID")
    public String getYsid() {
        return ysid;
    }

    public void setYsid(String ysid) {
        this.ysid = ysid;
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
    @Column(name = "JSJE")
    public long getJsje() {
        return jsje;
    }

    public void setJsje(long jsje) {
        this.jsje = jsje;
    }

    @Basic
    @Column(name = "JYJE")
    public long getJyje() {
        return jyje;
    }

    public void setJyje(long jyje) {
        this.jyje = jyje;
    }

    @Basic
    @Column(name = "YSSJ")
    public Time getYssj() {
        return yssj;
    }

    public void setYssj(Time yssj) {
        this.yssj = yssj;
    }

    @Basic
    @Column(name = "FHRID")
    public String getFhrid() {
        return fhrid;
    }

    public void setFhrid(String fhrid) {
        this.fhrid = fhrid;
    }

    @Basic
    @Column(name = "FHRXM")
    public String getFhrxm() {
        return fhrxm;
    }

    public void setFhrxm(String fhrxm) {
        this.fhrxm = fhrxm;
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

        JzfpBXmYsEntity that = (JzfpBXmYsEntity) o;

        if (jsje != that.jsje) return false;
        if (jyje != that.jyje) return false;
        if (ysid != null ? !ysid.equals(that.ysid) : that.ysid != null) return false;
        if (xmid != null ? !xmid.equals(that.xmid) : that.xmid != null) return false;
        if (wcsj != null ? !wcsj.equals(that.wcsj) : that.wcsj != null) return false;
        if (yssj != null ? !yssj.equals(that.yssj) : that.yssj != null) return false;
        if (fhrid != null ? !fhrid.equals(that.fhrid) : that.fhrid != null) return false;
        if (fhrxm != null ? !fhrxm.equals(that.fhrxm) : that.fhrxm != null) return false;
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
        int result = ysid != null ? ysid.hashCode() : 0;
        result = 31 * result + (xmid != null ? xmid.hashCode() : 0);
        result = 31 * result + (wcsj != null ? wcsj.hashCode() : 0);
        result = 31 * result + (int) (jsje ^ (jsje >>> 32));
        result = 31 * result + (int) (jyje ^ (jyje >>> 32));
        result = 31 * result + (yssj != null ? yssj.hashCode() : 0);
        result = 31 * result + (fhrid != null ? fhrid.hashCode() : 0);
        result = 31 * result + (fhrxm != null ? fhrxm.hashCode() : 0);
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
