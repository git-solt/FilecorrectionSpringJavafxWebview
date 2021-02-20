package com.filehandler.jfxsp8;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WebViewController {
    @FXML
    WebView webView;
    StringBuffer stringBuffer = new StringBuffer();
    String charSequenceBuffer = "";
    String fileName = "";
    public void initialize() {
        System.setProperty("file.encoding", "Cp1251");
        WebEngine engine = webView.getEngine();
        engine.load("http://localhost:8080/");
        engine.getLoadWorker().stateProperty().addListener((observable, old, current)-> {
            if(current == Worker.State.SUCCEEDED) {
//                JSObject window = ((JSObject) engine.executeScript("window"));
            }
        });

        JSObject window = ((JSObject) engine.executeScript("window"));
        window.setMember("javafxClient", "true");

        window.setMember("javafxClient", "true");
        engine.setOnError((e)-> {
            System.out.println("===ERROR===");
            System.out.println(e.getMessage());
        });
        engine.setOnAlert((e) -> {
            System.out.println(e.getData());

            charSequenceBuffer = (String) window.getMember("charSequenceBuffer");
            String[] parsedSequence = charSequenceBuffer.split(",");
            fileName = (String) window.getMember("sequenceFileName");

            int[] data = Arrays.stream(parsedSequence).map(Integer::parseInt).mapToInt(Integer::valueOf).toArray();

            openSaveDialogAndWriteToDisk(data);

//            System.out.println(stringBuffer.toString());



//            writeToDisk(file, stringBuffer.toString());
        });
    }

    private void openSaveDialogAndWriteToDisk(int[] data) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(fileName.replace(".txt", "KORR") + ".txt");
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
                stringBuffer.append((char)i);
            }
            System.out.println(stringBuffer.toString());
            outputStreamWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
