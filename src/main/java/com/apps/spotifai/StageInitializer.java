package com.apps.spotifai;

import com.apps.spotifai.SpotifaiApplication.StageReadyEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent>{
    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader(SpotifaiApplication.class.getResource("/com/apps/spotifai/fxml/wrapper/LoginPage.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setResizable(false);
        stage.setTitle("Spotifai");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent arg0) {

                Platform.exit();
                System.exit(0);
            }
        });
    }
}
