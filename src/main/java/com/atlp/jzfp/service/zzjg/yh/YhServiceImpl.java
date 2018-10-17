package com.atlp.jzfp.service.zzjg.yh;

import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgYhEntity;
import com.atlp.jzfp.repository.zzjg.ZzjgCdRepository;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
import com.atlp.jzfp.repository.zzjg.ZzjgYhRepository;
import com.atlp.jzfp.service.zzjg.cd.CdServiceImpl;
import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 单位 Service
 *
 * @author ctc
 * @date 2018年10月7日 11:24:56
 */
@Service
@Transactional
public class YhServiceImpl implements IYhService {
    @Autowired
    private ZzjgYhRepository yhRepository;

    /**
     * 获取分页数据
     */
    public Map getPage(PageModel pageModel) {
        Map retMap = new HashMap();
        retMap.put("code", "0");
        retMap.put("msg", "成功");
        //查询数据
        String sql = "select t.dwmc, t.dwjc "
                + " from JZFP_B_ZZJG_DW t "
                + " order by t.dwpwsx ";
        pageModel = yhRepository.findPageBySql(sql, pageModel);
        //设置返回值
        retMap.put("data", pageModel);
        return retMap;
    }

    /**
     * 保存
     */
    public boolean doSave(JzfpBZzjgYhEntity yhEntity, HttpServletRequest request) {
        boolean ret = false;
        try {
            JzfpBZzjgYhEntity saveEntity = new JzfpBZzjgYhEntity();
            if (AtlpUtil.isEmpty(yhEntity.getDwid())) {
                //新增
                BeanUtils.copyProperties(yhEntity, saveEntity, AtlpUtil.getNullPropertyNames(yhEntity));
                AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                //saveEntity.setPdwid("root");
                saveEntity.setDqzt("有效");
            } else {
                //修改
                saveEntity = yhRepository.findByYhid(yhEntity.getDwid());
                BeanUtils.copyProperties(yhEntity, saveEntity, AtlpUtil.getNullPropertyNames(yhEntity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }
            yhRepository.save(saveEntity);
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ret;
    }

    /**
     * 获取数据
     */
    public JzfpBZzjgYhEntity findByYhid(String yhid) {
        return yhRepository.findByYhid(yhid);
    }

    /**
     * 删除
     */
    public boolean doDelete(String yhid) throws BusinessException {
        //判断传参是否为空
        if (AtlpUtil.isEmpty(yhid)) {
            throw new BusinessException(ExceptionEnum.ERROR_PARAM);
        }
        yhRepository.delete(yhRepository.findByYhid(yhid));
        return true;
    }

    /**
     * 获取数据
     */
    public Map findMapByDlid(String dlid) {
        return AtlpUtil.entityToMap(yhRepository.findByDlid(dlid));
    }

    /**
     * 获取数据
     */
    public Map findMapBySjh(String sjh) {
        return AtlpUtil.entityToMap(yhRepository.findBySjh(sjh));
    }

}
