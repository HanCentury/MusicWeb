package com.music.servlet.auth;

import com.music.service.Userservice;
import com.music.service.Userseviceimp;
import com.music.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;
import java.io.IOException;

@WebServlet("/register")
public class registerservlet extends HttpServlet {
    Userservice service;
    @Override
    public void init() throws ServletException {
        service= new Userseviceimp();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ThymeleafUtil.process("register.html", new Context(),resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        if(service.insert(username,password,req.getSession())==1);
        resp.sendRedirect("login");
    }
    //this was a word of test
}



