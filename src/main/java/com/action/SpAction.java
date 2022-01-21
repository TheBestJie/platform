package com.action;

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

@ParentPackage("lj")
@Controller
public class SpAction {

    @Autowired
    private SpService spService;
    private Sp sp;
    private Integer spbh;

    public Sp getSp() {
        return sp;
    }

    public void setSp(Sp sp) {
        this.sp = sp;
    }

    public void setSpbh(Integer spbh) {
        this.spbh = spbh;
    }

    @Action("spList")
    public void spList() throws IOException {
        List<Sp> spList = spService.spList();
        Gson gson = new Gson();
        String json = gson.toJson(spList);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Action("spSave")
    public void spSave(){
        spService.spSave(sp);
    }

    @Action("spDelete")
    public void spDelete(){
        spService.spDelete(spbh);
    }

    @Action("spEdit")
    public void spEdit() throws IOException {
        Sp sp = spService.spEdit(spbh);
        Gson gson = new Gson();
        String json = gson.toJson(sp);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Action("spUpdate")
    public void spUpdate(){
        spService.spUpdate(sp);
    }
}
