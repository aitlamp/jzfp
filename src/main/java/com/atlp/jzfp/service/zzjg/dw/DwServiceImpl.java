package com.atlp.jzfp.service.zzjg.dw;

import com.atlp.jzfp.common.data.PageModel;
import com.atlp.jzfp.common.utils.AtlpUtil;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
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
public class DwServiceImpl implements IDwService {
    @Autowired
    private ZzjgDwRepository dwRepository;

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
        String[][] columns = {
                {"dwmc", "单位名称", "100",},
                {"dwjc", "单位简称", "80",},
                {"dwid", "单位ID", "50"}
        };
        pageModel = dwRepository.findPageBySql(sql, pageModel, columns);
//        //设置表头数据
//        List<Map> columnList = new ArrayList<>();
//        Map columnMap = new HashMap();
//        //单位名称
//        columnMap.put("prop", "dwmc");
//        columnMap.put("label", "单位名称");
//        columnList.add(columnMap);
//        //单位简称
//        columnMap.put("prop", "dwjc");
//        columnMap.put("label", "单位简称");
//        columnList.add(columnMap);
//        pageModel.setColumns(columnList);
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
