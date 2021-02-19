package com.filehandler.jfxsp8;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class WebViewController {
    @FXML
    WebView webView;
    StringBuffer stringBuffer = new StringBuffer("hello");

    public void initialize() {
        WebEngine engine = webView.getEngine();
        engine.load("http://localhost:8080/");
        JSObject window = ((JSObject) engine.executeScript("window"));
        window.setMember("java", stringBuffer);

        engine.setOnAlert((e) -> {
            System.out.println("===ALERTED===");
            String buffer = (String) window.getMember("importantData");
//            Supplier
            String[] a = buffer.split("");
//            List<Integer> integers = Arrays.stream(a).map(Integer::parseInt).collect(Collectors.toList());
//            for(int i : integers) {
//                stringBuffer.append((char)i);
//            }
            System.out.println(stringBuffer.toString());
            System.out.println("Type " + window.getMember("importantDataType"));
        });
    }


}
