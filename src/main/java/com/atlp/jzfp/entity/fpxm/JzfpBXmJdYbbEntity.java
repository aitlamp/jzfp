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
@Table(name = "JZFP_B_XM_JD_YBB")
public class JzfpBXmJdYbbEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String ybbid;
    // 项目信息
    private String xmid;
    private String xmmc;
    private String xmjc;
    private Timestamp xmjhkssj;
    private Timestamp xmjhjssj;
    // 阶段信息
    private String jdid;
    private String jdmc;
    private Double jdgzlzb;
    private Timestamp jdjhkssj;
    private Timestamp jdjhjssj;
    // 日期--阶段
    private String nd;
    private String yd;
    private String yb;
    private String ksyb;
    private String jsyb;
    private String kssj;
    private String jssj;
    private Integer totalyb;
    // 比率
    private Double jdjhzxl;
    private Double jdljjhzxl;
    private Double jdsjzxl;
    private Double jdljsjzxl;
    private Double xmjhzxl;
    private Double xmljjhzxl;
    private Double xmsjzxl;
    private Double xmljsjzxl;

}
