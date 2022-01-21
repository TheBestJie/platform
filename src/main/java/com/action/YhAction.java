package com.action;

import com.daomain.Yh;
import com.opensymphony.xwork2.ActionContext;
import com.service.YhService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

/**
 * 用户相关操作的类
 */
@ParentPackage("lj")
@Controller
public class YhAction {

    /**
     * 接收参数
     */
    private String yhm;
    private String yhmm;

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public void setYhmm(String yhmm) {
        this.yhmm = yhmm;
    }

    @Autowired
    private YhService yhService;

    /**
     * 登录验证
     * @throws IOException
     */
    @Action("/login")
    public void login() throws IOException {
        Yh yh = yhService.checkLogin(yhm,yhmm);
        if(yh == null){
            //登录失败
            ServletActionContext.getResponse().getWriter().write("9");
        }else {
            //登录成功，装入Session
            ActionContext.getContext().getSession().put("loginYh",yh);
            ServletActionContext.getResponse().getWriter().write("1");
        }
    }



}
