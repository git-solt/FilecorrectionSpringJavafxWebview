package com.filehandler.jfxsp8;


import org.springframework.stereotype.Component;

@Component
public class JavaBridge {
    public void print() {
        System.out.println("called from js");
    }

    public void handleDataBuffer(String buffer){
        for(byte b : buffer.getBytes()) {
            System.out.print((char)b);
        }
    }
}
