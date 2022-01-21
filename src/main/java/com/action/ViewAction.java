package com.action;

/**
 * 视图处理类
 * 通过配置将所有以 go_*.htm的请求转发到这里
 */
public class ViewAction {

    public String view(){
        return "success";
    }
}
