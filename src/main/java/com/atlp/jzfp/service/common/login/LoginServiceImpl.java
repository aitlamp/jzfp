package com.atlp.jzfp.service.common.login;

import com.alibaba.fastjson.JSON;
import com.atlp.jzfp.service.zzjg.yh.IYhService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.atlp.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录 Service
 *
 * @author ctc
 * @date 2018年8月14日 23:11:18
 */
@Slf4j
@Service
@Transactional
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private IYhService yhService;

    //登录方法
    public Map<String, Object> doLogin(String userName, String userPwd, String clientIp) {
        // 判断用户名是否存在
        Map userMap = yhService.findMapByDlid(userName);
        if (AtlpUtil.isEmpty(userMap)) {
            throw new BusinessException(4001, "用户名不存在");
        }
        // 判断密码是否正确
        String yhmm = AtlpUtil.toString(userMap.get("yhmm"));
        if (!yhmm.equals(userPwd)) {
            throw new BusinessException(4002, "密码不正确");
        }
        //插入登录日志

        return userMap;
    }
}
