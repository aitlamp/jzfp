package com.atlp.jzfp.service.fpxm.xmjd;

import com.atlp.jzfp.entity.fpxm.JzfpBXmJdYbbEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmjdYbbRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-19 14:49
 * @Decription:
 */
@Slf4j
@Service
@Transactional
public class XmjdYbbServiceImpl implements IxmjdYbbService {

    @Autowired
    private FpxmXmjdYbbRepository xmjdYbbRepository;

    @Override
    public Boolean doSave(List<JzfpBXmJdYbbEntity> jdYbbEntityList, HttpServletRequest request) throws BusinessException {
        List<JzfpBXmJdYbbEntity> save = xmjdYbbRepository.saveAll(jdYbbEntityList);
        if (AtlpUtil.isEmpty(save) || jdYbbEntityList.size() != save.size()) {
            log.debug("增加阶段月报表失败...月报表信息==={}", jdYbbEntityList.toString());
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "增加阶段月报表失败.");
        }

        return true;
    }

    @Override
    public Boolean doDelete(List<JzfpBXmJdYbbEntity> jdYbbEntityList) throws BusinessException {
        xmjdYbbRepository.deleteAll(jdYbbEntityList);
        return true;
    }
}
