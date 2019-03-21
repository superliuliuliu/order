package com.lgy.order.VO;

import lombok.Data;


/**
 * ResultVo
 * @description Http请求返回的最外层对象
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/16 23:06
 * @version 1.0.0
 */
@Data
public class ResultVo<T> {

    private Integer code;

    private String message;

    private T data;
}
