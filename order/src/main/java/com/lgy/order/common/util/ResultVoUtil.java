package com.lgy.order.common.util;

import com.lgy.order.model.VO.ResultVo;


/**
 * ResultVoUtil
 * @description 返回结果工具类
 * @param
 * @return
 * @author liugaoyang
 * @date 2019/3/17 10:49
 * @version 1.0.0
 */
public class ResultVoUtil {

    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMessage("操作成功");
        resultVo.setData(object);
        return resultVo;
    }

    //成功但是无数据返回的情况
    public static ResultVo success(){
        return success(null);
    }

    //响应失败的情况
    public static ResultVo error(Integer code, String message){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMessage(message);
        return resultVo;
    }

}
