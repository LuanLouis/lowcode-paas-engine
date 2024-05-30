package com.example.demo.controller;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseResponse<T>{


    private T data;

    /**
     * 是否成功标识
     */
    private Boolean isSuccess;

    /**
     * 错误码
     */
    private String errorCode;


    /**
     * 错误消息
     */
    private String errorMessage;


    public static <T> BaseResponse<T>  success(T t){
        return new BaseResponse<T>().setData(t).setIsSuccess(true);
    }



}
