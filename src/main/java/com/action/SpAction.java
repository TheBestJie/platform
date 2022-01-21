package com.action;

import com.daomain.Gys;
import com.daomain.Page;
import com.daomain.Sp;
import com.google.gson.Gson;
import com.service.SpService;
import com.util.ZjmUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
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

    private File excel;



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

    public void setExcel(File excel) {
        this.excel = excel;
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


    /**
     * 批量保存操作
     * @throws IOException
     */
    @Action("spImport")
    public void spImport() throws IOException {
        List<Sp> gysList = new ArrayList<>();
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
            Cell c5 = row.getCell(4);
            //将数据打包成gys实体，并存入gys实体集合中
            String spmc = c1.getStringCellValue();
            String spdw = c2.getStringCellValue();
            String spgg = c3.getStringCellValue();
            Integer spjg = (int)c4.getNumericCellValue();
            String zzs = c5.getStringCellValue();
            Sp sp =new Sp(null,spmc,null,spdw,spgg,spjg,zzs,null,null);
            gysList.add(sp);
        }
        spService.saves(gysList);
    }

    /**
     * 批量导出操作
     * @return
     * @throws Exception
     */
    @Action(
            value = "spExport",
            results = {@Result(name = "success",type = "stream",params = {"contentDisposition","attachment;filename=sp.xlsx"})}
    )
    public String spExport() throws Exception {
        //获取数据库数据
        List<Sp> spList = spService.findAll();
        //将数据写入Excel
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet();
        //写入表头数据
        {
            Row row = sheet.createRow(0);
            Cell c1 = row.createCell(0);
            Cell c2 = row.createCell(1);
            Cell c3 = row.createCell(2);
            Cell c4 = row.createCell(3);
            Cell c5 = row.createCell(4);
            Cell c6 = row.createCell(5);
            Cell c7 = row.createCell(6);
            c1.setCellValue("商品编号");
            c2.setCellValue("商品名称");
            c3.setCellValue("助记码");
            c4.setCellValue("商品单位");
            c5.setCellValue("商品规格");
            c6.setCellValue("商品价格");
            c7.setCellValue("制造商");
        }
        //写入数据
        int index = 1;
        for (Sp sp : spList) {
            Row row = sheet.createRow(index++);
            Cell c1 = row.createCell(0);
            Cell c2 = row.createCell(1);
            Cell c3 = row.createCell(2);
            Cell c4 = row.createCell(3);
            Cell c5 = row.createCell(4);
            Cell c6 = row.createCell(5);
            Cell c7 = row.createCell(6);
            c1.setCellValue(sp.getSpbh());
            c2.setCellValue(sp.getSpmc());
            c3.setCellValue(sp.getZjm());
            c4.setCellValue(sp.getSpdw());
            c5.setCellValue(sp.getSpgg());
            c6.setCellValue(sp.getSpjg());
            c7.setCellValue(sp.getZzs());
        }
        //下载
        //写入本地
        OutputStream os = new FileOutputStream("F:\\MyCode\\sp.xlsx");
        book.write(os);
        os.close();
        //返回浏览器
        fileUrl = "F:\\MyCode\\sp.xlsx";
        return "success";
    }

    private String fileUrl;
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(fileUrl);
    }
}
