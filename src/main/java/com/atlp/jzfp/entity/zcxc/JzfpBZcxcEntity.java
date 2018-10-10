package com.atlp.jzfp.entity.zcxc;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_ZCXC")
public class JzfpBZcxcEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String xcid;
    private Integer lx;
    private String bt;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String nr;
    private Timestamp shrq;
    private Timestamp fbrq;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

}
