package com.music.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

public class MybatisUtil {
    private static SqlSessionFactory factory;
    static {
        try {
            factory= new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  public static SqlSession getsession(){
       return factory.openSession(true);
  }
}
