package com.music.dao;

import com.music.entity.music;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MusicMapper {

    @Results({
            @Result(column = "musicname",property = "musicname"),
            @Result(column = "musicAddress",property = "musicAddress"),
    })
    @Select("select * from music")
    List<music> getMusicList();

    @Select("select musicname from music where musicname=#{musicname}")
    music getmusic(@Param("musicname") String musicname);

    @Results({
            @Result(column = "musicname", property = "musicname"),
            @Result(column = "musicAddress", property = "musicAddress"),
    })
    @Select("SELECT * FROM music WHERE musicname LIKE CONCAT('%', #{keyword}, '%')")
    List<music> searchMusicByName(@Param("keyword") String keyword);



    @Insert("INSERT INTO music (musicname, musicAddress) VALUES (#{musicname}, #{musicAddress})")
    void addMusic(@Param("musicname") String musicname, @Param("musicAddress") String musicAddress);

}
