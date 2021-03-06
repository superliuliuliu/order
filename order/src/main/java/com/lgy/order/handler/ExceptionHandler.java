package com.lgy.order.handler;

import com.lgy.order.VO.ResultVo;
import com.lgy.order.exception.SellException;
import com.lgy.order.util.ResultVoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @description 异常处理器，用于处理程序运行中出现的异常
 * @author liugaoyang
 * @date 2019/5/18 13:20
 * @version 1.0.0
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = SellException.class)
    @ResponseBody
    // ResponseStatus 注解的作用是修改返回给浏览器端的状态码
    //@ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResultVo handlerSellException(SellException e){
        return ResultVoUtil.error(e.getCode(), e.getMessage());
    }

}
