package com.filehandler.jfxsp8;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageReadyListener implements ApplicationListener<JavaFXApplication.StageReadyEvent> {
    @Override
    public void onApplicationEvent(JavaFXApplication.StageReadyEvent stageReadyEvent) {
        Stage stage = ((Stage) stageReadyEvent.getSource());

        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/views/webView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(parent, 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}
