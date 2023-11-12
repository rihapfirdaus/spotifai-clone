package com.apps.spotifai.model.repository;

import com.apps.spotifai.model.DataBase.Song;

import java.util.ArrayList;
import java.util.List;

public interface SongRepository {
    int create(Song song);
    int update(Song song);
    Song findById(Long id);
    List<Song> findAll();
    List<Song> findByTitle(String title);
    List<Song> findByArtist(String artist);
    int deleteById(Long id);
    int deleteAll();
}
