package com.atlp.jzfp.service.fpxm.xmjd;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdYbbEntity;
import org.atlp.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-19 14:49
 * @Decription:
 */
public interface IxmjdYbbService {

    /**
     * 添加或修改进度月报表
     * @param jdYbbEntityList
     * @param request
     * @return
     * @throws BusinessException
     */
    public Boolean doSave(List<JzfpBXmJdYbbEntity> jdYbbEntityList, HttpServletRequest request) throws BusinessException;

    /**
     * 删除阶段月度报表
     * @param jdYbbEntityList
     * @return
     * @throws BusinessException
     */
    public Boolean doDelete(List<JzfpBXmJdYbbEntity> jdYbbEntityList) throws BusinessException;

}
