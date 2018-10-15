package com.atlp.jzfp.entity.fpzj;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_ZJ_FJ", schema = "JZFP", catalog = "")
public class JzfpBZjFjEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String fjid;
    private String djid;
    private String fileName;
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
