package com.atlp.jzfp.entity.common;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "COM_B_LOGIN_LOG")
public class ComBLoginLogEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String dllogid;
    private String dlid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;
    private String dljg;
    private String hhid;
    private Timestamp dlsj;
    private Timestamp yxsj;
    private String ip;
    private String mark;
    private String hostname;
}
