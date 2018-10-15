package org.atlp.data;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义错误枚举
 *
 * @author 曹铁诚
 * @since 2018年10月14日 22:24:48
 */
public enum ExceptionEnum {
    ERROR(4000, "失败"),
    ERROR_UNKONW(4100, "未知错误"),
    ERROR_PARAM(4200, "参数错误"),
    ;

    private int code;   //返回码 非0即失败
    private String msg;  //消息提示

    ExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

