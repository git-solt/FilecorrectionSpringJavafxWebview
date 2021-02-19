package com.filehandler.jfxsp8;

public class JSApi {

    public void print() {
        System.out.println("called from js");
    }

    public void sendBuffer(String buffer) {
        if(buffer != null) {
            for(byte b : buffer.getBytes()) System.out.println((char)b);

        } else System.out.println("no bytes here " + buffer);


    }
}