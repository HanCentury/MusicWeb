package com.music.servlet.auth;


import com.music.entity.user;
import com.music.service.Musicservice;
import com.music.service.Musicserviceimp;
import com.music.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/index")
public class Indexservlet extends HttpServlet {
    Musicservice service;
    @Override
    public void init() throws ServletException {
        service= new Musicserviceimp();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context=new Context();
        user user= (user) req.getSession().getAttribute("user");
        context.setVariable("username",user.getUsername());
        context.setVariable("music_List",service.getMusicList());
        ThymeleafUtil.process("index.html",context,resp.getWriter());
        System.out.println("final test");
    }

}
