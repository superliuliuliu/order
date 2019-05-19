package com.lgy.order.model.VO;

import lombok.Data;

import java.io.Serializable;


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
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 444287145150626211L;

    private Integer code;

    private String message;

    private T data;
}
