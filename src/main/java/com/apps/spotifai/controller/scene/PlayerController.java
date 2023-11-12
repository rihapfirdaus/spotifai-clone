package com.apps.spotifai.controller.scene;

import com.apps.spotifai.model.DataBase.Song;
import com.apps.spotifai.model.data.GetSong;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.*;

public class PlayerController implements Initializable {
    private GetSong getSong = new GetSong();
    private Media media;
    private MediaPlayer mediaPlayer;

    private List<Song> songs;

    private int songNumber;
    private Timer timer;
    private TimerTask task;

    private boolean running = false;

    @FXML
    private Text ContentArtist;

    @FXML
    private ImageView ContentImg;

    @FXML
    private Text ContentTitle;

    @FXML
    private Button ControlButton;

    @FXML
    private Text CountdownMax;

    @FXML
    private Text CountdownMin;

    @FXML
    private Button NextButton;

    @FXML
    private Button PrevButton;

    @FXML
    private ProgressBar ProgresPlayer;

    @FXML
    private Slider SliderVolume;

    @FXML
    private Button VolumeButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songs = new ArrayList<Song>();
        songs = getSong.getAllData();

        media = new Media(getClass().getResource(songs.get(songNumber).getPathSrc()).toExternalForm());
        mediaPlayer = new MediaPlayer(media);
        updateDetails(songNumber);

        SliderVolume.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                double volume = SliderVolume.getValue() * 0.01;
                showIconVolume(volume);
                mediaPlayer.setVolume(volume);
            }

        });

        ProgresPlayer.setStyle("-fx-accent: green;");
    }
    public void showIconVolume(double volume){
        FontIcon fontIcon;
        if(volume == 0.0){
            fontIcon = new FontIcon("fas-volume-mute");
        }else if(volume <= 0.25){
            fontIcon = new FontIcon("fas-volume-off");
        }else if(volume <= 0.75){
            fontIcon = new FontIcon("fas-volume-down");
        }else{
            fontIcon = new FontIcon("fas-volume-up");
        }
        fontIcon.setIconSize(20);
        fontIcon.setIconColor(Color.WHITE);
        VolumeButton.setGraphic(fontIcon);
    }
    public void showCurrentButton(){
        if (running){
            FontIcon fontIcon = new FontIcon("fas-pause");
            ControlButton.setGraphic(fontIcon);
        } else {
            FontIcon fontIcon = new FontIcon("fas-play");
            ControlButton.setGraphic(fontIcon);
        }
    }
    public void controlMedia() {
        if (running){
            pauseMedia();
        }else{
            playMedia();
        }
    }
    public void playMedia(){
        running = true;
        showCurrentButton();
        updateDetails(songNumber);
        beginTimer();
        mediaPlayer.play();
    }
    public void pauseMedia(){
        running = false;
        showCurrentButton();
        cancelTimer();
        mediaPlayer.pause();
    }
    public void previousMedia() {

        if(songNumber > 0) {
            songNumber--;
            mediaPlayer.stop();

            if(running) {
                cancelTimer();
            }

            media = new Media(getClass().getResource(songs.get(songNumber).getPathSrc()).toExternalForm());
            mediaPlayer = new MediaPlayer(media);

            playMedia();
        }
        else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();

            if(running) {

                cancelTimer();
            }

            media = new Media(getClass().getResource(songs.get(songNumber).getPathSrc()).toExternalForm());
            mediaPlayer = new MediaPlayer(media);

            playMedia();
        }
    }
    public void nextMedia() {

        if(songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();

            if(running) {
                cancelTimer();
            }

            media = new Media(getClass().getResource(songs.get(songNumber).getPathSrc()).toExternalForm());
            mediaPlayer = new MediaPlayer(media);

            playMedia();
        }
        else {
            songNumber = 0;
            mediaPlayer.stop();

            media = new Media(getClass().getResource(songs.get(songNumber).getPathSrc()).toExternalForm());
            mediaPlayer = new MediaPlayer(media);

            playMedia();
        }
    }
    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {

            public void run() {
                Platform.runLater(() -> {
                    double current = mediaPlayer.getCurrentTime().toSeconds();
                    double end = media.getDuration().toSeconds();

                    CountdownMin.setText(getTimer(current));
                    CountdownMax.setText(getTimer(end));
                    ProgresPlayer.setProgress(current / end);

                    if (current / end == 1) {
                        nextMedia();
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelTimer() {
        timer.cancel();
    }

    public String getTimer(double time){
        int totalSeconds = (int) time;
        int totalMinutes = totalSeconds / 60;
        int remainingSeconds = totalSeconds % 60;
        String durationFormatted = String.format("%02d:%02d", totalMinutes, remainingSeconds);
        return durationFormatted;
    }

    public void updateDetails(int index){
        Song song = songs.get(index);
        Image image;
        try{
            if(song.getPathImg() == null || song.getPathImg().isEmpty()){
                image = new Image(getClass().getResourceAsStream("/com/apps/spotifai/img/music.jpg"));
            }else{
                image = new Image(getClass().getResourceAsStream(song.getPathImg()));
            }
        }catch (Exception e){
            image = new Image(getClass().getResourceAsStream("/com/apps/spotifai/img/music.jpg"));
        }
        ContentImg.setImage(image);
        ContentTitle.setText(song.getTitle());
        ContentArtist.setText(song.getArtist());
    }
}
