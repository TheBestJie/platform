package com.action;

import com.daomain.Jhd;
import com.daomain.Page;
import com.daomain.Sp;
import com.google.gson.Gson;
import com.service.JhdService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@ParentPackage("lj")
public class JhdAction extends CommAction{

    @Autowired
    private JhdService jhdService;

    //分页参数
    private Integer page;
    private Integer rows;

    private String spmc;
    private Jhd jhd;
    private String spbhs;
    private String spsls;
    private String spjgs;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public Jhd getJhd() {
        return jhd;
    }

    public void setJhd(Jhd jhd) {
        this.jhd = jhd;
    }

    public void setSpbhs(String spbhs) {
        this.spbhs = spbhs;
    }

    public void setSpsls(String spsls) {
        this.spsls = spsls;
    }

    public void setSpjgs(String spjgs) {
        this.spjgs = spjgs;
    }

    //获取当前的时间
    @Action("currentTime")
    public void currentTime() throws IOException {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String str_date = df.format(date);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.getWriter().write(str_date);
    }

    //通过传递的商品名称进行查询操作
    @Action("spListByMc")
    public void spListByMc() throws IOException {
        List<Sp> spList = jhdService.findSpByMc(spmc);
        super.writeJson(spList);
    }

    //保存进货单，进货详情，库存
    @Action("jhdSave")
    public void jhdSave() throws IOException, ServletException {
        jhdService.jhdSave(jhd,spbhs,spsls,spjgs);
        //super.writeString("保存成功");
        super.sendRedirect("go_jhdAdd.htm");
    }

    //进货单列表
    @Action("jhdList")
    public void jhdList() throws IOException {
        Page p = jhdService.findByPage(page, rows);
        super.writeJson(p);
    }
}
