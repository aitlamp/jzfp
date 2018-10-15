package com.atlp.jzfp.service.zzjg.dw;

import org.atlp.data.PageModel;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 单位 Service 接口
 *
 * @author ctc
 * @date 2018年10月7日 11:20:57
 */
public interface IDwService {
    //table分页展示
    Map getPage(PageModel pageModel);

    //保存
    boolean doSave(JzfpBZzjgDwEntity cdEntity, HttpServletRequest request);

    //获取对象
    JzfpBZzjgDwEntity findByDwid(String cdid);

    Object getData(Map pmap);

    //删除
    boolean doDelete(String cdid);

}
