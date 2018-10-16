package com.atlp.jzfp.entity.fpzj;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "JZFP_B_ZJ_SYDJ")
public class JzfpBZjSydjEntity {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String djid;
    private String sydwid;
    private String sydwmc;
    private String zjyt;
    private long syje;
    private Timestamp djsj;
    private String xmid;
    private String xmmc;
    private String xmjc;
    private String nd;
    private String yd;
    private String sm;
    private String dqzt;
    private Timestamp firsttime;
    private Timestamp lasttime;
    private String yhid;
    private String yhxm;
    private String yhdwid;
    private String yhdwmc;

    @Transient
    private List<JzfpBZjFjEntity> zjFjEntityList;

}
