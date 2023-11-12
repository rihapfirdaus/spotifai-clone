package com.apps.spotifai.model.repository;

import com.apps.spotifai.model.DataBase.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcSongRepository implements SongRepository{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcSongRepository(JdbcTemplate jdbcTemplate){this.jdbcTemplate = jdbcTemplate;}
    @Override
    public int create(Song song) {
        return jdbcTemplate.update("INSERT INTO songs (title, artist, pathImg, pathSrc) VALUES(?,?,?,?)",
                new Object[]{song.getTitle(), song.getArtist(), song.getPathImg(), song.getPathSrc()});
    }

    @Override
    public int update(Song song) {
        return jdbcTemplate.update("UPDATE songs SET title=?, artist=?, pathImg=?, pathSrc=? WHERE id=?",
                new Object[]{song.getTitle(), song.getArtist(), song.getPathImg(), song.getPathSrc(), song.getId()});
    }

    @Override
    public Song findById(Long id) {
        try{
            Song song = jdbcTemplate.queryForObject("SELECT * FROM songs WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Song.class), id);
            return song;
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Song> findAll() {
        try{
            List<Song> song = jdbcTemplate.query("SELECT * FROM songs",
                    BeanPropertyRowMapper.newInstance(Song.class));
            return song;
        }catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Song> findByTitle(String title) {
        try{
            List<Song> song = jdbcTemplate.query("SELECT * FROM songs WHERE title LIKE CONCAT('%', ?, '%')",
                    BeanPropertyRowMapper.newInstance(Song.class), title);
            return song;
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Song> findByArtist(String artist) {
        try{
            List<Song> song = jdbcTemplate.query("SELECT * FROM songs WHERE artist LIKE CONCAT('%', ?, '%')",
                    BeanPropertyRowMapper.newInstance(Song.class), artist);
            return song;
        } catch (IncorrectResultSizeDataAccessException e){
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM songs WHERE id =?", id);
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE from songs");
    }
}
