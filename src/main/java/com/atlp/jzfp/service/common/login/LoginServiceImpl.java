package com.atlp.jzfp.service.common.login;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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
    private Environment env;

    //登录方法
    public Map<String, Object> doLogin(String userName, String userPwd, String clientIp) {
        // 设置header
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=UTF-8");
        // 组织参数
        Map<String, Object> pmap = new HashMap<>();
        pmap.put("ip", clientIp);
        pmap.put("hhid", "");
        pmap.put("method", "userLogin");
        //pmap.put("appCode", customProps.getAppCode());
        pmap.put("userName", userName);
        pmap.put("userPwd", userPwd);
        // 发送请求
        Map<String, Object> responseMap = HttpClientUtil.postJson(null, pmap, headers);
        log.debug("调用统一授权接口登录方法返回值：" + responseMap);
        // 处理参数
        int statusCode = (Integer) responseMap.get("statusCode");
        if (statusCode == 200) {
            String responseContent = responseMap.get("responseContent").toString();
            // 将返回值转为Map对象
            return JSON.parseObject(responseContent);
        }
        return null;
    }
}
