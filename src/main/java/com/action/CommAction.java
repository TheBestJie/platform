package com.action;

import com.google.gson.Gson;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommAction {

    /**
     * 直接响应json
     * @param obj
     * @throws IOException
     */
    protected void writeJson(Object obj) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void writeString(String str) throws IOException {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(str);
    }

    protected void forward(String url) throws ServletException, IOException {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void sendRedirect(String url) throws IOException {
        ServletActionContext.getResponse().sendRedirect(url);
    }
}
