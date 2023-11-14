package com.google.api.client.extensions.servlet.auth.oauth2;

import com.woof.member.entity.Member;
import com.woof.member.service.MemberServiceImpl;

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
        Member member = (Member) request.getSession(false).getAttribute("member");

        if(member != null){
            Member memberByNo = new MemberServiceImpl().findMemberByNo(member.getMemNo());

            if (memberByNo != null){
                response.sendRedirect(request.getContextPath()+"/index.jsp");
            }else{
                response.sendRedirect(request.getContextPath()+"/plus");
            }

        }else{
            response.sendRedirect(request.getContextPath()+"/plus");
        }
    }
}