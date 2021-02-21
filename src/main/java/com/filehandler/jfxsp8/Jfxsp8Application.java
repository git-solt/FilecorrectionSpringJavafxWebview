package com.filehandler.jfxsp8;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jfxsp8Application {

    public static void main(String[] args) {
//        SpringApplication.run(Jfxsp8Application.class, args);
        Application.launch(JavaFXApplication.class, args);

    }

}
