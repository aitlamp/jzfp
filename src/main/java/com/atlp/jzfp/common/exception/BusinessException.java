package com.atlp.jzfp.common.exception;

import com.atlp.jzfp.common.data.ExceptionEnum;
import lombok.Data;

/**
 * 自定义--业务异常
 *
 * @author 曹铁诚
 * @since 2018年10月14日 22:24:48
 */
@Data
public class BusinessException extends RuntimeException {
    private int code;  //错误码

    public BusinessException() {
    }

    public BusinessException(int code) {
        super("失败");
        this.code = code;
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = 1;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }
}

