package com.atlp.jzfp.entity.zzjg;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_ZZJG_CD")
public class JzfpBZzjgCdEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String cdid;
    private String pcdid;
    private String cdmc;
    private String cdlx;
    private String pcpage;
    private String padpage;
    private String wappage;
    private String tb;
    private int xssx;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;
}
