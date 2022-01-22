package com.action;

import com.daomain.Kf;
import com.daomain.Page;
import com.google.gson.Gson;
import com.service.KfService;
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

@ParentPackage("lj")
@Controller
public class KfAction {

    @Autowired
    private KfService kfService;

    private Integer page;
    private Integer rows;
    private File excel;
    private Kf kf;
    private Integer kfbh;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public void setExcel(File excel) {
        this.excel = excel;
    }

    public Kf getKf() {
        return kf;
    }

    public void setKf(Kf kf) {
        this.kf = kf;
    }

    public void setKfbh(Integer kfbh) {
        this.kfbh = kfbh;
    }

    /**
     * 返回分页信息
     * @throws IOException
     */
    @Action("kfList")
    public void kfList() throws IOException {
        Page p = kfService.findByPage(page,rows);
        Gson gson = new Gson();
        String json = gson.toJson(p);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 批量导入
     * @throws IOException
     */
    @Action("kfImport")
    public void kfImport() throws IOException {
        List<Kf> kfList = new ArrayList<>();
        Workbook book = WorkbookFactory.create(excel);
        Sheet sheet = book.getSheetAt(0);
        for(int i=1;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            Cell c1 = row.getCell(0);
            Cell c2 = row.getCell(1);
            Kf kf = new Kf(null,c1.getStringCellValue(),c2.getStringCellValue(),null,null);
            kfList.add(kf);
        }
        kfService.saves(kfList);
    }

    /**
     * 批量导出
     */
    @Action(
            value = "kfExport"
            ,results = {@Result(name = "success",type = "stream",params = {"contentDisposition","attachment;filename=kf.xlsx"})}
    )
    public String kfExport() throws IOException {
        List<Kf> kfList = kfService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        {
            Row row = sheet.createRow(0);
            Cell c1 = row.createCell(0);
            Cell c2 = row.createCell(1);
            Cell c3 = row.createCell(2);
            c1.setCellValue("库房编号");
            c2.setCellValue("库房名称");
            c3.setCellValue("库房位置");
        }
        int index = 1;
        for (Kf kf : kfList) {
            Row row = sheet.createRow(index++);
            Cell c1 = row.createCell(0);
            Cell c2 = row.createCell(1);
            Cell c3 = row.createCell(2);
            c1.setCellValue(kf.getKfbh());
            c2.setCellValue(kf.getKfmc());
            c3.setCellValue(kf.getKfmc());
        }

        OutputStream os = new FileOutputStream("F:\\MyCode\\kf.xlsx");
        workbook.write(os);
        os.close();
        fileUrl = "F:\\MyCode\\kf.xlsx";
        return "success";
    }

    private String fileUrl;

    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(fileUrl);
    }

    @Action("kfSave")
    public void kfSave(){
        kfService.save(kf);
    }

    /**
     * 通过kfbh，单行数据查询
     * @throws IOException
     */
    @Action("kfEdit")
    public void kfEdit() throws IOException {
        Kf kf = kfService.findByKfbh(kfbh);
        Gson gson = new Gson();
        String json = gson.toJson(kf);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    /**
     * 修改一条数据
     */
    @Action("kfUpdate")
    public void kfUpdate(){
        kfService.kfEdit(kf);
    }

    /**
     * 删除一条记录
     */
    @Action("kfDelete")
    public void kfDelete(){
        kfService.kfDelete(kfbh);
    }
}
