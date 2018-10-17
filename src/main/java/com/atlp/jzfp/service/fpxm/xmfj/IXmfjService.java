package com.atlp.jzfp.service.fpxm.xmfj;

import com.atlp.jzfp.entity.fpxm.JzfpBXmFjEntity;
import org.atlp.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-09 16:38
 * @Decription: 项目附件业务层接口
 */
public interface IXmfjService {

    /**
     * 主键id查询项目附件
     * @param id
     * @return
     * @throws Exception
     */
    public JzfpBXmFjEntity getInfoById(String id) throws BusinessException;

    /**
     * 添加项目附件
     * @param entity
     * @return
     * @throws Exception
     */
    public void doSave(JzfpBXmFjEntity entity, HttpServletRequest request) throws BusinessException;

    /**
     * 删除项目附件
     * @param entity
     * @return
     * @throws Exception
     */
    public Boolean doDelete(JzfpBXmFjEntity entity) throws BusinessException;

    /**
     * 查询项目所需附件
     * @param xmid
     * @return
     * @throws BusinessException
     */
    public List<Map> getXmsxfjByXmid(String xmid) throws BusinessException;

    /**
     * 查询项目已上传附件
     * @param xmid
     * @return
     * @throws BusinessException
     */
    public List<Map> getXmyscfjByXmid(String xmid) throws BusinessException;
}
