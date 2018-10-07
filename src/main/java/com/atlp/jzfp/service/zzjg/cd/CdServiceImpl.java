package com.atlp.jzfp.service.zzjg.cd;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgCdEntity;
import com.atlp.jzfp.repository.zzjg.ZzjgCdRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.*;

/**
 * 菜单 Service
 *
 * @author ctc
 * @date 2018年8月9日 15:53:49
 */
@Service
@Transactional
public class CdServiceImpl implements ICdService {
    @Autowired
    private ZzjgCdRepository zzjgCdRepository;

    /**
     * 获取分页数据
     */
    public Page<JzfpBZzjgCdEntity> getPage(PageModel page) {
        return zzjgCdRepository.findAll(PageRequest.of(page.getPage(), page.getLimit()));
    }

    /**
     * 保存
     */
    public boolean doSave(JzfpBZzjgCdEntity cdEntity, HttpServletRequest request) {
        boolean ret = false;
        try {
            JzfpBZzjgCdEntity saveEntity = new JzfpBZzjgCdEntity();
            if (AtlpUtil.isEmpty(cdEntity.getCdid())) {
                //新增
                BeanUtils.copyProperties(cdEntity, saveEntity, AtlpUtil.getNullPropertyNames(cdEntity));
                AtlpUtil.setUserInfo(saveEntity, request);
                saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
                saveEntity.setPcdid("root");
                saveEntity.setDqzt("有效");
            } else {
                //修改
                saveEntity = zzjgCdRepository.findByCdid(cdEntity.getCdid());
                BeanUtils.copyProperties(cdEntity, saveEntity, AtlpUtil.getNullPropertyNames(cdEntity));
                saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            }
            zzjgCdRepository.save(saveEntity);
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
    public JzfpBZzjgCdEntity findByCdid(String cdid) {
        return zzjgCdRepository.findByCdid(cdid);
    }

    /**
     * 删除
     */
    public boolean doDelete(String cdid) {
        boolean ret = false;
        try {
            zzjgCdRepository.delete(zzjgCdRepository.findByCdid(cdid));
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

    /**
     * 获取数据
     */
    public List findAll() {
        return zzjgCdRepository.findAll();
    }

    /**
     * 获取菜单数据
     */
    public List getMenus(String pcdid) {
        List<Map> menuList = new ArrayList<>();
        //根据上级菜单ID获取子菜单
        List<JzfpBZzjgCdEntity> pCdList = zzjgCdRepository.findByPcdid(pcdid, new Sort(Sort.Direction.DESC, "xssx"));
        for (JzfpBZzjgCdEntity pCd : pCdList) {
            Map menuMap = new HashMap();
            menuMap.put("cdid", pCd.getCdid());
            menuMap.put("pcdid", pCd.getPcdid());
            menuMap.put("cdmc", pCd.getCdmc());
            if (pCd.getCdlx().equals("1")) {
                //单元
                List<Map> childList = this.getMenus(pCd.getCdid());
                menuMap.put("childs", childList);
            } else {
                //功能
                menuMap.put("page", pCd.getPcpage());
            }
            menuList.add(menuMap);
        }
        return menuList;
    }
}
