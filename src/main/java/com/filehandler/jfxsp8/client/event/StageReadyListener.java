package com.filehandler.jfxsp8.client.event;

import com.filehandler.jfxsp8.JavaFXApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;

@Component
public class StageReadyListener implements ApplicationListener<JavaFXApplication.StageReadyEvent> {
    @Override
    public void onApplicationEvent(JavaFXApplication.StageReadyEvent stageReadyEvent) {
        Stage stage = ((Stage) stageReadyEvent.getSource());

        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/views/enhancedWebView.fxml"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
        double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
        Scene scene = new Scene(parent, screenWidth / 2, screenHeight / 1.3);
        stage.setScene(scene);
        stage.show();
    }
}
