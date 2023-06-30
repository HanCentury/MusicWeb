package com.music.service;

import com.music.dao.MusicMapper;
import com.music.entity.music;
import com.music.service.Musicservice;
import com.music.utils.MybatisUtil;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class Musicserviceimp implements Musicservice {
    @Override
    public List<music> getMusicList() {
        try(SqlSession sqlSession= MybatisUtil.getsession()){
            MusicMapper mapper=sqlSession.getMapper(MusicMapper.class);
            return mapper.getMusicList();
        }
    }

    @Override
    public boolean search(String musicname, HttpSession session) {
        try (SqlSession sqlSession = MybatisUtil.getsession()) {
            MusicMapper mapper = sqlSession.getMapper(MusicMapper.class);
            music music = mapper.getmusic(musicname);
            if(music==null){
                return  false;
            }else{
                return false;
            }
        }
    }

    @Override
    public List<music> searchMusicByName(String keyword) {
        try (SqlSession sqlSession = MybatisUtil.getsession()) {
            MusicMapper mapper = sqlSession.getMapper(MusicMapper.class);
            return mapper.searchMusicByName(keyword);
        }
    }

    @Override
    public void uploadMusic(String musicname, InputStream musicStream) {
        String uploadDirectory = "D:/work/IDEA/IDEA workspace/musciweb0410/src/main/webapp/music";
        try {
            String filePath = uploadDirectory + "/" + musicname;
            File outputFile = new File(filePath);
            Files.copy(musicStream, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
        }

    }

    @Override
    public void insert(String musicname, String musicAddress) {
        SqlSession sqlSession = MybatisUtil.getsession();
        MusicMapper musicMapper = sqlSession.getMapper(MusicMapper.class);
        musicMapper.addMusic(musicname, musicAddress);
        sqlSession.commit();
        sqlSession.close();
    }
}
