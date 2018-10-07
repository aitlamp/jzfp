package com.atlp.jzfp.service.zzjg.dw;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

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

    /**
     * 获取分页数据
     */
    public Page<JzfpBZzjgDwEntity> getPage(PageModel page) {
        return dwRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
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
     * 删除
     */
    public boolean doDelete(String cdid) {
        boolean ret = false;
        try {
            dwRepository.delete(dwRepository.findByDwid(cdid));
            //zzjgCdRepository.delete(zzjgCdRepository.findByCdid(cdid + 1));
            if (cdid != null) {
                //throw new RuntimeException("haha");
                throw new Exception("haha");
            }
            //ret = true;
        } catch (Exception e) {
            //} catch (RuntimeException e) {
            e.printStackTrace();
            //throw new RuntimeException();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//手动回滚，这样上层就无需去处理异常
        }
        return ret;
    }

}
