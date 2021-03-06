package com.atlp.jzfp.common.filter;

import com.atlp.jzfp.service.common.login.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ResultModel;
import org.atlp.exception.BusinessException;
import org.atlp.utils.AtlpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 是否允许访问--过滤器
 *
 * @author 曹铁诚
 * @date 2018年8月22日 11:14:00
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "accessFilter", urlPatterns = "/*")
public class AccessFilter implements Filter {
    @Autowired
    private Environment env;
    @Autowired
    ILoginService loginService;

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("AccessFilter过滤器初始化！");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        // 定义变量
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        // 获取请求地址
        String requestUrl = httpServletRequest.getRequestURI();

        // 排除静态资源过滤
        if (requestUrl.endsWith(".js") || requestUrl.endsWith(".css")
                || requestUrl.endsWith(".png") || requestUrl.endsWith(".ioc")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        // 忽略验证
        boolean check = false;
        List<String> accessIgnore = env.getProperty("custom.access-ignore", List.class);
        for (String ignore : accessIgnore) {
            if (requestUrl.contains(ignore)) {
                check = true;
                break;
            }
        }
        if (check || requestUrl.endsWith("/")) {
            // 忽略验证
            filterChain.doFilter(httpServletRequest, httpServletResponse); // 跳转页面
            return;
        }

        // 判断session是否存在hhid
        String contextPath = httpServletRequest.getContextPath();
        String hhid = AtlpUtil.toString(httpServletRequest.getSession().getAttribute("hhid"));
        if (AtlpUtil.isEmpty(hhid)) {
            // 验证不通过
            httpServletResponse.sendRedirect(contextPath);
            return;
        }

        // 验证hhid是否过期
        if (loginService.checkLogin(hhid, AtlpUtil.getClientIP(httpServletRequest))) {
            // 验证通过
            filterChain.doFilter(httpServletRequest, httpServletResponse); // 跳转页面
        } else {
            // 验证不通过
            httpServletResponse.sendRedirect(contextPath);
        }
    }

    @Override
    public void destroy() {
        log.info("AccessFilter过滤器销毁！");
    }

}
