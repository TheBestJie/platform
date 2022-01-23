package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 进货单单号生成工具类
 */
public class JhdbhUtil {

    //获取JHD
    public static final String DEFAULT_JHD = "JHD";

    //获取默认的时间
    public static String defaultDate(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(date);
    }

    //获取进货单头
    public static String getJhdbhByHead(){
        return DEFAULT_JHD + defaultDate();
    }

    //获取进货单号
    public static String getJhdbh(String oldJhdbh){
        String jhdbh = "";
        String jhdbhHead = getJhdbhByHead();
        if(oldJhdbh == null || oldJhdbh.equals("") || oldJhdbh.indexOf(defaultDate()) == -1){
            jhdbh  = jhdbhHead + "00001";
        }else {
            String no = oldJhdbh.substring(11);
            int _no = Integer.parseInt(no) ;
            _no++;
            no = _no + "";
            switch (no.length()){
                case 1:
                    no = "0000" + no;
                    break;
                case 2:
                    no = "000" + no;
                    break;
                case 3:
                    no = "00" + no;
                    break;
                case 4:
                    no = "0" + no;
                    break;
            }
            jhdbh  = jhdbhHead + no;
        }
        return jhdbh;
    }
}
