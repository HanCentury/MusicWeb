package com.music.servlet.auth;

import com.music.entity.user;
import com.music.service.Musicserviceimp;
import com.music.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/musiceasy")
public class musiceasyservlet extends HttpServlet {
    Musicserviceimp service;
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
        ThymeleafUtil.process("musiceasy.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String musicname = req.getParameter("musicname");
        if(service.search(musicname,req.getSession())){
            resp.sendRedirect("musiceasy");
        }else{

        }
    }
}
