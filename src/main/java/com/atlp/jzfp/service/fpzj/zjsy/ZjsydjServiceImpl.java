package com.atlp.jzfp.service.fpzj.zjsy;

import com.atlp.jzfp.entity.fpxm.JzfpBXmXxEntity;
import com.atlp.jzfp.entity.fpzj.JzfpBZjFjEntity;
import com.atlp.jzfp.entity.fpzj.JzfpBZjSydjEntity;
import com.atlp.jzfp.entity.zzjg.JzfpBZzjgDwEntity;
import com.atlp.jzfp.repository.fpxm.FpxmXmxxRepository;
import com.atlp.jzfp.repository.fpzj.FpzjZjsydjRepository;
import com.atlp.jzfp.repository.zzjg.ZzjgDwRepository;
import com.atlp.jzfp.service.fpzj.zjfj.IZjfjService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.atlp.wrapper.FastDFSClientWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: bijunming
 * @CreateTime: 2018/10/12 16:25
 * @Decription: 资金使用业务层实现类
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ZjsydjServiceImpl implements IZjsydjService {
    @Autowired
    private FpzjZjsydjRepository zjsydjRepository;
    @Autowired
    private ZzjgDwRepository zzjgDwRepository;
    @Autowired
    private FpxmXmxxRepository xmxxRepository;
    @Autowired
    private IZjfjService zjfjService;
    @Autowired
    private FastDFSClientWrapper dfsClientWrapper;

    /**
     * 资金使用登记信息分页展示
     */
    @Override
    public PageModel getPage(PageModel page) {
        String sql = "select t.nd,t.xmmc,t.zjyt,t.djsj,t.sydwmc,t.syje from JZFP_B_ZJ_SYDJ t";
        return zjsydjRepository.findPageBySql(sql, page);
    }

    /**
     * 添加或修改资金使用登记信息
     */
    @Override
    public Boolean doSaveOrUpdate(JzfpBZjSydjEntity entity, MultipartFile file) {
        JzfpBZjSydjEntity saveEntity = new JzfpBZjSydjEntity();
        //判断主键
        if (AtlpUtil.isEmpty(entity.getDjid())) {
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setYd("10");
            saveEntity.setFirsttime(new Timestamp(new Date().getTime()));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
            saveEntity.setDqzt("有效");
            saveEntity.setYhid("123a123");
            saveEntity.setYhxm("aaaaaa");
            saveEntity.setYhdwid("321b123");
            saveEntity.setYhdwmc("bbbbbb");
        } else {
            saveEntity = zjsydjRepository.findByDjid(entity.getDjid());
            if (AtlpUtil.isEmpty(saveEntity)) {
                throw new BusinessException(4201, "查询资金下拨登记信息失败");
            }
            BeanUtils.copyProperties(entity, saveEntity, AtlpUtil.getNullPropertyNames(entity));
            saveEntity.setLasttime(new Timestamp(new Date().getTime()));
        }
        //设置使用单位
        JzfpBZzjgDwEntity zzjgDwEntity = zzjgDwRepository.findByDwid(saveEntity.getSydwid());
        if (saveEntity.getSydwmc() == null || !saveEntity.getSydwmc().equals(zzjgDwEntity.getDwmc())) {
            saveEntity.setSydwmc(zzjgDwEntity.getDwmc());
        }
        //设置项目信息
        JzfpBXmXxEntity xmxxEntity = xmxxRepository.findByXmid(saveEntity.getXmid());
        if (null != xmxxEntity && !xmxxEntity.getXmmc().equals(saveEntity.getXmmc())) {
            saveEntity.setXmmc(xmxxEntity.getXmmc());
            saveEntity.setXmjc(xmxxEntity.getXmjc());
        }
        //添加资金使用登记信息
        JzfpBZjSydjEntity save = zjsydjRepository.save(saveEntity);
        if (null == save || null == save.getDjid()) {
            throw new BusinessException(4202, "新增或修改资金下拨登记信息失败");
        }
        //上传文件并添加资金附件
        JzfpBZjFjEntity zjFjEntity = new JzfpBZjFjEntity();
        String zjfjURL = null;
        try {
            zjfjURL = dfsClientWrapper.uploadFile(file);
            zjFjEntity.setDjid(save.getDjid());
            zjFjEntity.setZjfjurl(zjfjURL);
            zjFjEntity.setDocSize(file.getSize());
            zjFjEntity.setFileName("time");
            zjFjEntity.setContentType(FilenameUtils.getExtension(file.getOriginalFilename()));
            zjFjEntity.setMimeType(file.getName());
            zjFjEntity.setBlobContent(new byte[]{1, 2, 34, 5, 6, 7, 8, 9});
            zjfjService.doSave(zjFjEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 删除对应的资金使用登记
     */
    @Override
    @Transactional
    public Boolean doDelete(String djid) {
        zjfjService.doDeleteBydjid(djid);
        zjsydjRepository.delete(zjsydjRepository.findByDjid(djid));
        return true;
    }

    /**
     * 查询对应的资金下拨详细数据信息
     */
    @Override
    public JzfpBZjSydjEntity getZjsydjById(String djid) {
        JzfpBZjSydjEntity zjsydjEntity = zjsydjRepository.findByDjid(djid);
//        zjsydjEntity.setZjFjEntityList(zjfjService.getZjfjByDjid(djid));
        if (AtlpUtil.isEmpty(zjsydjEntity)) {
            throw new BusinessException(4201, "查询资金使用登记详细信息失败");
        }
        return zjsydjEntity;
    }
}