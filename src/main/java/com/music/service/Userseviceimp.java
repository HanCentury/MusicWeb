package com.music.service;

import com.music.dao.UserMapper;
import com.music.entity.user;
import com.music.service.Userservice;
import com.music.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

public class Userseviceimp implements Userservice {
    @Override
    public boolean auth(String username, String password, HttpSession session) {
        try(SqlSession sqlSession=MybatisUtil.getsession()){
            UserMapper mapper=sqlSession.getMapper(UserMapper.class);
            user user=mapper.getUser(username,password);
            if(user==null){
                return  false;
            }else{
                session.setAttribute("user",user);
                return true;
            }
        }
    }
    @Override
    public int insert(String username, String password, HttpSession session) {
        SqlSession sqlSession=MybatisUtil.getsession();
        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user user = mapper.Insertuser(username, password);
            sqlSession.commit();
            return 1;
        } catch (Exception e) {
            sqlSession.rollback();
            return 0;
        } finally {
            sqlSession.close();
        }
    }

}
