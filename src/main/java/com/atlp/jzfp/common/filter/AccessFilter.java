package com.atlp.jzfp.common.filter;

import lombok.extern.slf4j.Slf4j;
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
import java.util.Map;

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
        String hhid = httpServletRequest.getSession().getAttribute("hhid").toString();
        if (AtlpUtil.isEmpty(hhid)) {
            // 验证不通过
            String contextPath = httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(contextPath);
        }

        // 验证hhid是否过期

        //判断是否登录
        Map<String, Object> checkLoginMap = null;//this.checkLogin(httpServletRequest);
        String code = checkLoginMap.get("code").toString();
        if (code.equals("00")) {
            // 验证通过
            filterChain.doFilter(httpServletRequest, httpServletResponse); // 跳转页面
        } else {
            // 验证不通过
            String contextPath = httpServletRequest.getContextPath();
            httpServletResponse.sendRedirect(contextPath);
        }

        //filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("AccessFilter过滤器销毁！");
    }

}
