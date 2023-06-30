package com.music.service;

import com.music.entity.music;
import jakarta.servlet.http.HttpSession;

import java.io.InputStream;
import java.util.List;

public interface Musicservice {
    List<music> getMusicList();

    List<music> searchMusicByName(String keyword);

    void uploadMusic(String musicname, InputStream musicStream);

    void insert(String musicname, String musicAddress);

    boolean search(String musicname, HttpSession session);

}
