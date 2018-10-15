package com.atlp.jzfp.service.zzjg.yh;

import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgYhEntity;
import org.atlp.data.PageModel;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 单位 Service 接口
 *
 * @author ctc
 * @date 2018年10月7日 11:20:57
 */
public interface IYhService {
    //table分页展示
    Map getPage(PageModel pageModel);

    //保存
    boolean doSave(JzfpBZzjgYhEntity yhEntity, HttpServletRequest request);

    //获取对象
    JzfpBZzjgYhEntity findByYhid(String yhid);

    //删除
    boolean doDelete(String yhid);

    Map findMapByDlid(String dlid);

}
