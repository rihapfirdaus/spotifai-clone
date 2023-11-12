package com.apps.spotifai.controller.model;

import com.apps.spotifai.model.DataBase.Song;
import com.apps.spotifai.model.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class SongController {
    @Autowired
    SongRepository songRepository;

    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getAllSongs(@RequestParam(required = false)String title){
        try {
            List<Song> songs = new ArrayList<Song>();

            if (title == null){
                songRepository.findAll().forEach(songs::add);
            } else {
                songRepository.findByTitle(title).forEach(songs::add);
            }

            if(songs.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(songs, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/songs/find/id/{id}")
    public ResponseEntity<Song> getSongById(@PathVariable("id") Long id){
        Song song = songRepository.findById(id);

        if (song != null){
            return new ResponseEntity<>(song, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/songs/find/artist/{artist}")
    public ResponseEntity<List<Song>> getSongByArtist(@PathVariable("artist") String artist){
        List<Song> song = songRepository.findByArtist(artist);

        if (song != null){
            return new ResponseEntity<>(song, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/songs/create")
    public ResponseEntity<String> createSong(@RequestBody Song song){
        try{
            songRepository.create(new Song(song.getTitle(), song.getArtist(), song.getPathImg(), song.getPathSrc()));
            return new ResponseEntity<>("Song was created successfully.", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/songs/update/{id}")
    public ResponseEntity<String> updateSong(@PathVariable("id") Long id, @RequestBody Song song){
        Song _song = songRepository.findById(id);

        if (_song != null){
            _song.setId(song.getId());
            _song.setTitle(song.getTitle());
            _song.setArtist(song.getArtist());
            _song.setPathImg(song.getPathImg());
            _song.setPathSrc(song.getPathSrc());

            songRepository.update(_song);
            return new ResponseEntity<>("Song was updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot find song with id " + id, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/songs/delete/id/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable("id") Long id){
        try {
            int result = songRepository.deleteById(id);
            if (result == 0) {
                return new ResponseEntity<>("Cannot find song with id " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Song was deleted successfully. ", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Cannot delete song. ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/songs/delete/all")
    public ResponseEntity<String> deleteAllSongs(){
        try {
            int numRows = songRepository.deleteAll();
            return new ResponseEntity<>("Deleted " + numRows + " Song(s) successfully.", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("Cannot delete songs.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
