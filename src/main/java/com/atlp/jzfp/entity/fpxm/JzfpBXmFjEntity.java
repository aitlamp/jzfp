package com.atlp.jzfp.entity.fpxm;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_XM_FJ")
public class JzfpBXmFjEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String fjid;
    private String xmid;
    private String jdid;
    private String zxid;
    private String zlid;
    private String fileName;
    private String pathUrl;
    private String mimeType;
    private long docSize;
    private String contentType;
    private byte[] blobContent;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;
}
