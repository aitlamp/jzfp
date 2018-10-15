package com.atlp.jzfp.controller.common.login;

import com.atlp.jzfp.service.common.login.ILoginService;
import com.atlp.jzfp.service.zzjg.yh.IYhService;
import org.atlp.data.UserInfo;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 登录 Controller
 *
 * @author ctc
 * @date 2018年8月14日 22:47:49
 */
@Controller
public class LoginController {
    @Autowired
    private ILoginService loginService;
    @Autowired
    private IYhService yhService;

    //公共包含页面
    @RequestMapping({"/include"})
    public String include() {
        return "/include/include";
    }

    //跳转登录页
    @RequestMapping({"/", "/login"})
    public String login() {
        return "/login/login";
    }

    //登录方法
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public Object doLogin(String userName, String userPwd, HttpServletRequest request) {
        Map<String, Object> retMap = loginService.doLogin(userName, userPwd, AtlpUtil.getClientIP(request));
        String code = retMap.get("code").toString();
        if (code.equals("00")) {
            // 获取接收到的用户信息
            Map<String, Object> userMap = (Map<String, Object>) retMap.get("userInfo");
            String hhid = userMap.get("hhid").toString();
            String dlid = userMap.get("dlid").toString();
            // 根据dlid获取本系统用户信息
            Map yhMap = yhService.findMapByDlid(dlid);
            //JzfpBZzjgYhEntity yhEntity = zzjgYhRepository.findByDlid(dlid);
            if (yhMap != null) {
                UserInfo userinfo = new UserInfo(yhMap);
                // 清空前面的session变量
                request.getSession().removeAttribute("hhid");
                request.getSession().removeAttribute("userinfo");
                // 给session重新赋值
                request.getSession().setAttribute("hhid", hhid);
                request.getSession().setAttribute("userinfo", userinfo);
                // 移除用户信息后返回
                retMap.remove("userInfo");
            } else {
                retMap.put("code", "31");
                retMap.put("msg", "本系统没有此用户！");
            }
        }
        return retMap;
    }
}
