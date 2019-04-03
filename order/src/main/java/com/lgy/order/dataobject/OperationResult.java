package com.lgy.order.dataobject;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OperationResult
 * @description 操作结果消息体 用来向Sweetalert.js传递参数
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/4/3 21:49
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class OperationResult {

    private Integer Code;
    private String message;
}
