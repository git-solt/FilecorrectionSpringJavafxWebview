package com.filehandler.jfxsp8;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentProvider {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
