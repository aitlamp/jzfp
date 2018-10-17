package com.atlp.jzfp.service.fpzj.zjsy;

import com.atlp.jzfp.entity.fpzj.JzfpBZjSydjEntity;
import org.atlp.data.PageModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:25
 * @Decription: 资金使用业务层接口
 */
public interface IZjsydjService {
    public PageModel getPage(PageModel page);

    public Boolean doSaveOrUpdate(JzfpBZjSydjEntity entity, List<MultipartFile> files);

    public Boolean doDelete(String djid);

    public JzfpBZjSydjEntity getZjsydjById(String djid);

    public List<JzfpBZjSydjEntity> getZjsydjByXmid(String xmid);
}
