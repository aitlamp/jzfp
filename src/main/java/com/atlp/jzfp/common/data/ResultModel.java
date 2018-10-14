package com.atlp.jzfp.common.data;


import lombok.Data;

import java.io.Serializable;

/**
 * 返回值 Model
 *
 * @author 曹铁诚
 * @date 2018年10月13日 12:43:12
 */
@Data
public class ResultModel implements Serializable {
    private int code;   //返回码 非0即失败
    private String msg;  //消息提示
    private Object data; //返回的数据

    //构造函数
    public ResultModel(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //成功
    public static ResultModel success(String msg, Object data) {
        return new ResultModel(0, msg, data);
    }

    public static ResultModel success(Object data) {
        return success("成功", data);
    }

    public static ResultModel success() {
        return success(null);
    }

    //失败
    public static ResultModel failed(int code, String msg) {
        return new ResultModel(code, msg, "");
    }

    public static ResultModel failed(String msg) {
        return failed(1, msg);
    }

    public static ResultModel failed() {
        return failed("失败");
    }

}

