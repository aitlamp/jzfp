package com.atlp.jzfp.service.common.login;

import java.util.Map;

/**
 * 登录 Service 接口
 *
 * @author ctc
 * @date 2018年8月14日 23:11:54
 */
public interface ILoginService {
    Map<String, Object> doLogin(String userName, String userPwd, String clientIp) throws Exception;

    boolean checkLogin(String hhid, String clientIp);
}
