package com.filehandler.jfxsp8.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.Arrays;

public class WebViewController {
    @FXML
    WebView webView;
    WebEngine engine;
    String charSequenceBuffer = "";
    String fileName = "";
    @FXML
    Button refreshBtn;
    public void initialize() {
//        System.setProperty("file.encoding", "Cp1251");
        engine = webView.getEngine();
        engine.load("http://localhost:8080/");
        ImageView imageView = new ImageView(new Image(getClass().getResource("/static/assets/icons/1200px-Refresh_icon.svg.png").toExternalForm()));

        refreshBtn.setGraphic(imageView);
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        JSObject window = ((JSObject) engine.executeScript("window"));
        window.setMember("javafxClient", "true");

        window.setMember("javafxClient", "true");
        engine.setOnError((e)-> {
            System.out.println("===ERROR===");
            System.out.println(e.getMessage());
        });
        engine.setOnAlert((e) -> {

            charSequenceBuffer = (String) window.getMember("charSequenceBuffer");
            String[] parsedSequence = charSequenceBuffer.split(",");
            fileName = (String) window.getMember("sequenceFileName");

            int[] data = Arrays.stream(parsedSequence).map(Integer::parseInt).mapToInt(Integer::valueOf).toArray();

            openSaveDialogAndWriteToDisk(data);

        });
    }

    private void openSaveDialogAndWriteToDisk(int[] data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(fileName.replace(".txt", "KORR"));
        fileChooser.setTitle("Save corrected file to disk");

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text", ".txt"));

        File file = fileChooser.showSaveDialog(webView.getScene().getWindow());

        writeToDisk(file, data);

    }

    private void writeToDisk(File file, int[] data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "Cp1252");

            for(int i : data) {
                outputStreamWriter.write((i));
            }
            outputStreamWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleRefresh() {
        engine.reload();
    }
}
