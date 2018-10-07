package com.atlp.jzfp.service.zzjg.dw;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

/**
 * 单位 Service 接口
 *
 * @author ctc
 * @date 2018年10月7日 11:20:57
 */
public interface IDwService {
    //table分页展示
    Page<JzfpBZzjgDwEntity> getPage(PageModel page);

    //保存
    boolean doSave(JzfpBZzjgDwEntity cdEntity, HttpServletRequest request);

    //获取对象
    JzfpBZzjgDwEntity findByDwid(String cdid);

    //删除
    boolean doDelete(String cdid);

}
