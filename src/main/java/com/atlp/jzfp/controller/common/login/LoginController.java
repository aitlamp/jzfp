package com.atlp.jzfp.controller.common.login;

import com.atlp.jzfp.service.common.login.ILoginService;
import org.atlp.data.ResultModel;
import org.atlp.data.UserInfo;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    //登录方法
    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public boolean doLogin(@RequestBody Map pmap, HttpServletRequest request) throws Exception {
        String userName = AtlpUtil.toString(pmap.get("userName"));
        String userPwd = AtlpUtil.toString(pmap.get("userPwd"));
        //判断参数
        if (AtlpUtil.isEmpty(userName)) {
            throw new BusinessException(4201, "用户名不能为空");
        }
        if (AtlpUtil.isEmpty(userPwd)) {
            throw new BusinessException(4202, "密码不能为空");
        }
        //登录
        Map<String, Object> userMap = loginService.doLogin(userName, userPwd, AtlpUtil.getClientIP(request));
        if (AtlpUtil.isEmpty(userMap)) {
            throw new BusinessException(4203, "未查询到用户信息");
        }
        String hhid = AtlpUtil.toString(userMap.get("hhid"));
        //给UserInfo赋值
        UserInfo userinfo = new UserInfo(userMap);
        // 清空前面的session变量
        request.getSession().removeAttribute("hhid");
        request.getSession().removeAttribute("userinfo");
        // 给session重新赋值
        request.getSession().setAttribute("hhid", hhid);
        request.getSession().setAttribute("userinfo", userinfo);
        // 返回
        return true;
    }
}
