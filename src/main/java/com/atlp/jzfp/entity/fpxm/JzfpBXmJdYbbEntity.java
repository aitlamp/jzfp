package com.atlp.jzfp.entity.fpxm;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "JZFP_B_XM_JD_YBB", schema = "JZFP", catalog = "")
public class JzfpBXmJdYbbEntity {
    private String ybbid;
    private String jdid;
    private String nd;
    private String yd;
    private long jhzxl;
    private long ljjhzxl;
    private long sjzxl;
    private long ljsjzxl;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "YBBID")
    public String getYbbid() {
        return ybbid;
    }

    public void setYbbid(String ybbid) {
        this.ybbid = ybbid;
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
    @Column(name = "JHZXL")
    public long getJhzxl() {
        return jhzxl;
    }

    public void setJhzxl(long jhzxl) {
        this.jhzxl = jhzxl;
    }

    @Basic
    @Column(name = "LJJHZXL")
    public long getLjjhzxl() {
        return ljjhzxl;
    }

    public void setLjjhzxl(long ljjhzxl) {
        this.ljjhzxl = ljjhzxl;
    }

    @Basic
    @Column(name = "SJZXL")
    public long getSjzxl() {
        return sjzxl;
    }

    public void setSjzxl(long sjzxl) {
        this.sjzxl = sjzxl;
    }

    @Basic
    @Column(name = "LJSJZXL")
    public long getLjsjzxl() {
        return ljsjzxl;
    }

    public void setLjsjzxl(long ljsjzxl) {
        this.ljsjzxl = ljsjzxl;
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

        JzfpBXmJdYbbEntity that = (JzfpBXmJdYbbEntity) o;

        if (jhzxl != that.jhzxl) return false;
        if (ljjhzxl != that.ljjhzxl) return false;
        if (sjzxl != that.sjzxl) return false;
        if (ljsjzxl != that.ljsjzxl) return false;
        if (ybbid != null ? !ybbid.equals(that.ybbid) : that.ybbid != null) return false;
        if (jdid != null ? !jdid.equals(that.jdid) : that.jdid != null) return false;
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
        int result = ybbid != null ? ybbid.hashCode() : 0;
        result = 31 * result + (jdid != null ? jdid.hashCode() : 0);
        result = 31 * result + (nd != null ? nd.hashCode() : 0);
        result = 31 * result + (yd != null ? yd.hashCode() : 0);
        result = 31 * result + (int) (jhzxl ^ (jhzxl >>> 32));
        result = 31 * result + (int) (ljjhzxl ^ (ljjhzxl >>> 32));
        result = 31 * result + (int) (sjzxl ^ (sjzxl >>> 32));
        result = 31 * result + (int) (ljsjzxl ^ (ljsjzxl >>> 32));
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
