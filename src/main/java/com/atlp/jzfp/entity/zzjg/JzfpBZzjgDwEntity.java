package com.atlp.jzfp.entity.zzjg;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_ZZJG_DW")
public class JzfpBZzjgDwEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String dwid;
    private String pdwid;
    private String dwmc;
    private String dwjc;
    private int dwpwsx;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;
}
