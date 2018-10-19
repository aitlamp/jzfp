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
    private String nd;
    private Timestamp kssj;
    private String kssjb;
    private Timestamp jssj;
    private String jssjb;
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
    private String jhkssj;  // 计划开始时间2018-03
    @Transient
    private String jhjssj;
    @Transient
    private Double jdljzxjd;    // 阶段累计执行进度
}
