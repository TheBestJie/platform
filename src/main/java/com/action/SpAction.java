package com.action;

import com.daomain.Page;
import com.daomain.Sp;
import com.google.gson.Gson;
import com.service.SpService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品信息的action类
 */

@ParentPackage("lj")
@Controller
public class SpAction {

    @Autowired
    private SpService spService;

    /**
     * 接收参数
     */
    private Sp sp;
    private Integer spbh;

    private Integer page;
    private Integer rows;



    public Sp getSp() {
        return sp;
    }

    public void setSp(Sp sp) {
        this.sp = sp;
    }

    public void setSpbh(Integer spbh) {
        this.spbh = spbh;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * 查询商品信息表中的全部数据
     * 通过json的形式将数据返回去
     * @throws IOException
     */
    @Action("spList")
    public void spList() throws IOException {
       Page p = spService.spList(page,rows);
        Gson gson = new Gson();
        String json = gson.toJson(p);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 数据的添加操作
     */
    @Action("spSave")
    public void spSave(){
        spService.spSave(sp);
    }

    /**
     * 数据的删除操作
     */
    @Action("spDelete")
    public void spDelete(){
        spService.spDelete(spbh);
    }

    /**
     * 数据的查询操作
     * 通过spbh字段进行查询
     * @throws IOException
     */
    @Action("spEdit")
    public void spEdit() throws IOException {
        Sp sp = spService.spEdit(spbh);
        Gson gson = new Gson();
        String json = gson.toJson(sp);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }


    /**
     * 数据的修改操作
     */
    @Action("spUpdate")
    public void spUpdate(){
        spService.spUpdate(sp);
    }
}
