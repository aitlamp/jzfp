package com.atlp.jzfp.entity.zzjg;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@Table(name = "JZFP_B_ZZJG_SQXX")
public class JzfpBZzjgSqxxEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String sqid;
    private String sqyhid;
    private String sqyhxm;
    private String cdid;
    private String cdmc;
    private Time yxq;
    private String sm;
    private String dqzt;
    private Time firsttime;
    private Time lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;
}
