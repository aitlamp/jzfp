package com.atlp.jzfp.common.base;


import com.atlp.jzfp.common.data.ResultModel;
import com.atlp.jzfp.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
        //未知错误
        return ResultModel.failed(1, "系统异常：" + e);
    }

}
