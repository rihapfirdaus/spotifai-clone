package com.apps.spotifai.view.manipulation;

import com.apps.spotifai.model.DataBase.Song;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardController {
    @FXML
    private VBox Card;

    @FXML
    private Button cardPlayBtn;

    @FXML
    private Text contentAddition;

    @FXML
    private ImageView contentImage;

    @FXML
    private Text contentTitle;

    public void initialize(){
        cardPlayBtn.setVisible(false);

        Card.setOnMouseEntered(event -> {
            cardPlayBtn.setVisible(true);
        });

        Card.setOnMouseExited(event -> {
            cardPlayBtn.setVisible(false);
        });
    }
    public void setCard(Song song){
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

        contentImage.setImage(image);
        contentTitle.setText(song.getTitle());
        contentAddition.setText(song.getArtist());
    }
}
