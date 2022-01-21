package com.action;

import com.daomain.Gys;
import com.daomain.Page;
import com.google.gson.Gson;
import com.service.GysService;
import org.apache.poi.ss.usermodel.*;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ParentPackage("lj")
@Controller
public class GysAction {

    private File excel;

    private Integer page;
    private Integer rows;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Autowired
    private GysService gysService;

    @Action("gysList")
    public void gysList() throws IOException {
        Page p = gysService.gysList(page,rows);
        Gson gson = new Gson();
        String json = gson.toJson(p);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    @Action("gysImport")
    public void gysImport() throws IOException {
        List<Gys> gysList = new ArrayList<>();
        //获取一个excel
        Workbook book = WorkbookFactory.create(excel);
        //获取第一个sheet
        Sheet sheet = book.getSheetAt(0);
        //表格中的第一行为名称不需要处理，所以循环读取第一行以后的每一行
        for (int i=1;i<=sheet.getLastRowNum();i++){
            //获取每行数据
            Row row = sheet.getRow(i);
            Cell c1 = row.getCell(0);
            Cell c2 = row.getCell(1);
            Cell c3 = row.getCell(2);
            Cell c4 = row.getCell(3);
            //将数据打包成gys实体，并存入gys实体集合中
            String gysmc = c1.getStringCellValue();
            String lxr = c2.getStringCellValue();
            String lxdh = (int)c3.getNumericCellValue() + "";
            String dz = c4.getStringCellValue();
            Gys gys = new Gys(null,gysmc,lxr,lxdh,dz,null,null);
            gysList.add(gys);
        }
        gysService.saves(gysList);
    }
}
