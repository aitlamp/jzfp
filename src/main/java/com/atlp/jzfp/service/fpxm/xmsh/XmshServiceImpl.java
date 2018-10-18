package com.atlp.jzfp.service.fpxm.xmsh;

import com.atlp.jzfp.repository.fpxm.FpxmXmzxRepository;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ExceptionEnum;
import org.atlp.data.PageModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: zhangchq
 * @CreateTime: 2018-10-18 15:08
 * @Decription:
 */
@Slf4j
@Service
@Transactional
public class XmshServiceImpl implements IXmshService {

    @Autowired
    private FpxmXmzxRepository xmzxRepository;

    @Override
    public PageModel getJdshPage(PageModel page) throws BusinessException {
        // 查询语句
        String sql = " select zx.zxid zxid, zx.wcsj jdsjjssj,  " +
                " xx.xmid xmid, xx.xmmc xmmc, xx.szx szx, xx.szxz szxz, xx.dlmc dlmc, xx.xlmc xlmc, xx.kssj xmkssj, " +
                " jd.jdid jdid, jd.jdmc jdmc, jd.jsnr jdgz, jd.jssj jdjhjssj " +
                " from jzfp_b_xm_zx zx " +
                " left join jzfp_b_xm_jd jd on jd.jdid = zx.jdid " +
                " left join jzfp_b_xm_xx xx on xx.xmid = zx.xmid " +
                " where zx.ljzxjd = 100 and zx.dqzt = '提交' order by zx.firsttime ";
        page = xmzxRepository.findPageBySql(sql, page);

        if (AtlpUtil.isEmpty(page.getRows())) {
            log.debug("阶段审核信息空空如也...阶段信息==={}", page.toString());
            throw new BusinessException(ExceptionEnum.ERROR.getCode(), "阶段审核信息空空如也.");
        }

        return page;
    }
}
