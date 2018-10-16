package com.atlp.jzfp.repository.common;

import com.atlp.jzfp.entity.common.ComBLoginLogEntity;
import com.atlp.jzfp.entity.zcxc.JzfpBZcxcEntity;
import org.atlp.base.BaseRepository;

import java.util.List;
import java.util.Map;

/**
 * 登录日志
 */
public interface LoginLogRepository extends BaseRepository<ComBLoginLogEntity, Long> {
    List<ComBLoginLogEntity> findAllByHhidAndIp(String hhid, String ip);
}
