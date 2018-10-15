package org.atlp.handler;


import lombok.extern.slf4j.Slf4j;
import org.atlp.data.ResultModel;
import org.atlp.exception.BusinessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Controller层--异常拦截处理器
 *
 * @author 曹铁诚
 * @date 2018年10月13日 12:41:53
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    public ResultModel defultExcepitonHandler(Exception e) {
        e.printStackTrace();
        if (e instanceof BusinessException) {
            log.error(this.getClass() + "业务异常：" + e.getMessage());
            BusinessException businessException = (BusinessException) e;
            return ResultModel.failed(businessException.getCode(), businessException.getMessage());
        }
        //获取异常堆栈信息
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        e.fillInStackTrace().printStackTrace(printWriter);
        String eStr = result.toString();
        //解析
        String lastMsg = e.getMessage();
        String[] eArr = eStr.split("\n");
        for (String es : eArr) {
            if (es.startsWith("Caused by:")) {
                lastMsg = es.replace("Caused by:", "").replaceAll("\r", "");
            }
        }
        //未知错误
        return ResultModel.failed(5000, "系统异常：" + lastMsg);
    }

}
