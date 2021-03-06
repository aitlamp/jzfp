package com.atlp.jzfp.entity.fpxm;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "JZFP_B_XM_ZL")
public class JzfpBXmZlEntity implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String zlid;
    private String flid;
    private String zlmc;
    private String xssx;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Transient
    private JzfpBXmFjEntity xmFjEntity;
    @Transient
    private String flmc;
}
