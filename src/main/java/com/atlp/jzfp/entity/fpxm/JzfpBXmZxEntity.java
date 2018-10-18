package com.atlp.jzfp.entity.fpxm;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "JZFP_B_XM_ZX")
public class JzfpBXmZxEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String zxid;
    private String jdid;
    private String xmid;
    private Timestamp wcsj;
    private Double bczxjd;
    private Double ljzxjd;
    private String gznr;
    private String zhyy;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Transient
    private Double sczxjd;
    @Transient
    private Double xmljjd;
    @Transient
    private String xmmc;
    @Transient
    private List<JzfpBXmJdEntity> xmJdEntityList;
    @Transient
    private List<JzfpBXmFjEntity> xmFjEntityList;

}
