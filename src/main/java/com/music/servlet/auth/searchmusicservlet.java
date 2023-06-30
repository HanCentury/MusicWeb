package com.music.servlet.auth;

import com.music.entity.music;
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
import java.util.List;

@WebServlet("/searchmusic")
public class searchmusicservlet extends HttpServlet {

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
        ThymeleafUtil.process("searchmusic.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        String keyword = req.getParameter("keyword");
        List<music> searchResults = service.searchMusicByName(keyword);
        context.setVariable("searchResults", searchResults);
        ThymeleafUtil.process("searchmusic.html", context, resp.getWriter());

    }
}
