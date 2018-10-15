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
@Table(name = "JZFP_B_ZJ_XB")
public class JzfpBZjXbEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String dzid;
    private String xbdwid;
    private String xbdwmc;
    private Timestamp xbsj;
    private long xbje;
    private String jsdwid;
    private String jsdwmc;
    private Timestamp jssj;
    private String nd;
    private String yd;
    private String pc;
    private String zjlx;
    private String fwbh;
    private String fwmc;
    private String slkm;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

}
