package com.apps.spotifai;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StockUiApplication {
	public static void main(String[] args) {
		Application.launch(SpotifaiApplication.class, args);
	}
}
