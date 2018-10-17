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
@Table(name = "JZFP_B_ZJ_DZ")
public class JzfpBZjDzEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String dzid;
    private String lyid;
    private String lymc;
    private Timestamp sjxbsj;
    private Timestamp dzsj;
    private long dzje;
    private String nd;
    private String yd;
    private String zjlx;
    private String fwbh;
    private String fwmc;
    private String pc;
    private String zjbfly;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

}
