package com.atlp.jzfp.service.zzjg.cd;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgCdEntity;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 菜单 Service 接口
 *
 * @author ctc
 * @date 2018年8月9日 15:53:49
 */
public interface ICdService {
    Page<JzfpBZzjgCdEntity> getPage(PageModel page);

    boolean doSave(JzfpBZzjgCdEntity cdEntity, HttpServletRequest request);

    JzfpBZzjgCdEntity findByCdid(String cdid);

    boolean doDelete(String cdid);

    List findAll();

    List getMenus(String pcdid);
}
