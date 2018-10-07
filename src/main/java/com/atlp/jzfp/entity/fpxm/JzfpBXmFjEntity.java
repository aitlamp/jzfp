package com.atlp.jzfp.entity.fpxm;

import javax.persistence.*;
import java.sql.Time;
import java.util.Arrays;

@Entity
@Table(name = "JZFP_B_XM_FJ", schema = "JZFP", catalog = "")
public class JzfpBXmFjEntity {
    private String fjid;
    private String xmid;
    private String zlid;
    private String fileName;
    private String mimeType;
    private long docSize;
    private String contentType;
    private byte[] blobContent;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Id
    @Column(name = "FJID")
    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
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
    @Column(name = "ZLID")
    public String getZlid() {
        return zlid;
    }

    public void setZlid(String zlid) {
        this.zlid = zlid;
    }

    @Basic
    @Column(name = "FILE_NAME")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Basic
    @Column(name = "MIME_TYPE")
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Basic
    @Column(name = "DOC_SIZE")
    public long getDocSize() {
        return docSize;
    }

    public void setDocSize(long docSize) {
        this.docSize = docSize;
    }

    @Basic
    @Column(name = "CONTENT_TYPE")
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Basic
    @Column(name = "BLOB_CONTENT")
    public byte[] getBlobContent() {
        return blobContent;
    }

    public void setBlobContent(byte[] blobContent) {
        this.blobContent = blobContent;
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

        JzfpBXmFjEntity that = (JzfpBXmFjEntity) o;

        if (docSize != that.docSize) return false;
        if (fjid != null ? !fjid.equals(that.fjid) : that.fjid != null) return false;
        if (xmid != null ? !xmid.equals(that.xmid) : that.xmid != null) return false;
        if (zlid != null ? !zlid.equals(that.zlid) : that.zlid != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (mimeType != null ? !mimeType.equals(that.mimeType) : that.mimeType != null) return false;
        if (contentType != null ? !contentType.equals(that.contentType) : that.contentType != null) return false;
        if (!Arrays.equals(blobContent, that.blobContent)) return false;
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
        int result = fjid != null ? fjid.hashCode() : 0;
        result = 31 * result + (xmid != null ? xmid.hashCode() : 0);
        result = 31 * result + (zlid != null ? zlid.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (mimeType != null ? mimeType.hashCode() : 0);
        result = 31 * result + (int) (docSize ^ (docSize >>> 32));
        result = 31 * result + (contentType != null ? contentType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(blobContent);
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
