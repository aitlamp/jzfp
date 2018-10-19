package com.atlp.jzfp.entity.fpxm;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "JZFP_B_XM_XX")
public class JzfpBXmXxEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String xmid;
    private String xmmc;
    private String xmjc;
    private String dlid;
    private String dlmc;
    private String xlid;
    private String xlmc;
    private BigDecimal zje;
    private BigDecimal ysje;
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
    private String nd;
    private Timestamp kssj;
    private String kssjb;
    private Timestamp jssj;
    private String jssjb;
    private Boolean xmlb;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Transient
    private String jhkssj;
    @Transient
    private String jhjssj;
    @Transient
    private List<JzfpBXmJdEntity> xmJdEntityList;

}
