package com.google.api.client.extensions.servlet.auth.oauth2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request ,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String info = request.getSession().getAttribute("member") == null ? "" : request.getSession().getAttribute("member").toString();

        if(info.equals("success")){
            writer.print("Hello google api");
        }else{
            response.sendRedirect(request.getContextPath()+"/plus");
        }
    }
}