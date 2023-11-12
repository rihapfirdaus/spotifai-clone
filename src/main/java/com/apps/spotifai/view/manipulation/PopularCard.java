package com.apps.spotifai.view.manipulation;

import com.apps.spotifai.model.DataBase.Song;
import com.apps.spotifai.model.data.GetSong;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PopularCard implements Initializable {
    private GetSong getSong = new GetSong();

    @FXML
    private HBox cardBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Song> songs = new ArrayList<Song>();
        songs = getSong.getAllData();

        try {
            for (int i = 3; i <= songs.size()-1; i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/apps/spotifai/fxml/component/General/ContentCard.fxml"));

                VBox vBox = fxmlLoader.load();

                CardController cardController = fxmlLoader.getController();
                cardController.setCard(songs.get(i));

                cardBox.getChildren().add(vBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
