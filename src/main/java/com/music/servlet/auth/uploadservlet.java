package com.music.servlet.auth;

import com.music.entity.music;
import com.music.entity.user;
import com.music.service.Musicservice;
import com.music.service.Musicserviceimp;
import com.music.utils.ThymeleafUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet("/upload")
public class uploadservlet extends HttpServlet {

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


        ThymeleafUtil.process("upload.html",context,resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Context context = new Context();
        Part musicPart = req.getPart("musicFile");
        String musicname = musicPart.getSubmittedFileName();
        InputStream musicStream = musicPart.getInputStream();
        service.uploadMusic(musicname, musicStream);
         musicname = musicPart.getSubmittedFileName();
        int dotIndex = musicname.lastIndexOf('.');
        String filename = musicname.substring(0, dotIndex);
        music newMusic = new music();
        newMusic.setMusicname(filename);
        newMusic.setMusicAddress("music/" + musicname);
        service.insert(newMusic.getMusicname(), newMusic.getMusicAddress());
        ThymeleafUtil.process("upload.html", context, resp.getWriter());
    }
}
