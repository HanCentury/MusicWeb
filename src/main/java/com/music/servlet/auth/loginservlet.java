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

@WebServlet("/login")
public class loginservlet extends HttpServlet {

    Userservice service;
    @Override
    public void init() throws ServletException {
        service= new Userseviceimp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             Context context=new Context();
             if(req.getSession().getAttribute("login-false")!=null){
                 context.setVariable("false",true);
                 req.getSession().removeAttribute("login-false");
             }
             if(req.getSession().getAttribute("user")!=null){
                 resp.sendRedirect("index");
                 return;
             }
             ThymeleafUtil.process("login.html",context,resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String remenberme=req.getParameter("remenberme");
        if(service.auth(username,password,req.getSession())){
            resp.sendRedirect("index");
        }else{
            req.getSession().setAttribute("login-false",new Object());
            this.doGet(req,resp);
        }
    }
}
