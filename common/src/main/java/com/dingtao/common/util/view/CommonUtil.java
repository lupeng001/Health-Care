package com.dingtao.common.util.view;

import com.dingtao.common.util.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者;路鹏
 * 时间：$date$ $time$
 * 详细信息：
 */
public class CommonUtil {
    /**

     * 接口头参数

     * String 类型  否则报错

     */

    public static Map getHeaderParamsMap(String transactionType) {

        Map<String, Object> map = new HashMap<>();



        return map;

    }

    /**

     * 接口传json map  里面存放header + body

     * String 类型  否则报错

     */

    public static Map getServiceParamsMap(Map<String, Object> header,Map<String, Object> body) {

        Map<String, Object> map = new HashMap<>();

        map.put("header",header);//指定key 为header

        map.put("body",body);//指定key为body

        return map;

    }
}
