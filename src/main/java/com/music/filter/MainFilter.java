package com.music.filter;


import com.music.entity.user;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import org.apache.ibatis.annotations.Insert;

import java.io.IOException;

@WebFilter("/*")
public class MainFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String url=req.getRequestURL().toString();
        if(url.contains("/static/") && url.endsWith("login")){
            HttpSession session=req.getSession();
            user user=(user)session.getAttribute("user");
            if(user==null){
                res.sendRedirect("login");
                return;
            }
        }
        chain.doFilter(req,res);
    }
}
