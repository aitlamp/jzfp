package com.atlp.jzfp.service.zzjg.dw;

import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.repository.zzjg.ZzjgCdRepository;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
import com.atlp.jzfp.service.zzjg.cd.CdServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 单位 Service
 *
 * @author ctc
 * @date 2018年10月7日 11:24:56
 */
@Service
@Transactional
public class DwServiceImpl implements IDwService {
    @Autowired
    private ZzjgDwRepository dwRepository;
    @Autowired
    private CdServiceImpl cdService;
    @Autowired
    private ZzjgCdRepository cdRepository;

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
        pageModel = dwRepository.findPageBySql(sql, pageModel);
        //设置返回值
        retMap.put("data", pageModel);
        return retMap;
    }

    /**
     * 保存
     */
    public boolean doSave(JzfpBZzjgDwEntity dwEntity, HttpServletRequest request) {
        boolean ret = false;
        try {
            JzfpBZzjgDwEntity saveEntity = new JzfpBZzjgDwEntity();
            if (AtlpUtil.isEmpty(dwEntity.getDwid())) {
                //新增
                BeanUtils.copyProperties(dwEntity, saveEntity, AtlpUtil.getNullPropertyNames(dwEntity));
                AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                saveEntity.setPdwid("root");
                saveEntity.setDqzt("有效");
            } else {
                //修改
                saveEntity = dwRepository.findByDwid(dwEntity.getDwid());
                BeanUtils.copyProperties(dwEntity, saveEntity, AtlpUtil.getNullPropertyNames(dwEntity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }
            dwRepository.save(saveEntity);
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
    public JzfpBZzjgDwEntity findByDwid(String cdid) {
        return dwRepository.findByDwid(cdid);
    }

    /**
     * 获取数据
     */
    public Object getData(Map pmap) {
        String sql = "select t.* from JZFP_B_ZZJG_DW t ";
        //List<Map> list = dwRepository.findListMapBySql(sql);
        Map list = dwRepository.findMapBySql(sql);
        return list;
    }

    /**
     * 删除
     */
    public boolean doDelete(String dwid) throws BusinessException {
        boolean ret = false;
        //try {
        //判断传参是否为空
        if (dwid != null) {
            throw new BusinessException(ExceptionEnum.ERROR_PARAM);
        }
        dwRepository.delete(dwRepository.findByDwid(dwid));
        //dwRepository.delete(dwRepository.findByDwid(dwid + 1));
        //cdService.doDelete(cdid);
        if (dwid != null) {
            //throw new BusinessException(ExceptionEnum.ERROR);
//                throw new BusinessException(ExceptionEnum.UNKONW_ERROR);
//                throw new BusinessException(1);
//                throw new BusinessException("参数错误");
//                throw new BusinessException(2, "参数错误");
        }
        ret = true;
        //} catch (BusinessException e) {
        //} catch (RuntimeException e) {
        //e.printStackTrace();
        //throw new BusinessException();
        //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚，这样上层就无需去处理异常
        //}
        return ret;
    }

}
