package com.atlp.jzfp.entity.fpxm;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_XM_JD")
public class JzfpBXmJdEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String jdid;
    private String xmid;
    private String jdmc;
    private String jsnr;
    private Double gzlzb;
    private Timestamp kssj;
    private Timestamp jssj;
    private Integer jdsx;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Transient
    private Double jdljzxjd;
}
