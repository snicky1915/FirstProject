package com.example.FirstProject.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/html")
public class HtmlController {

    private final MailContentBuilder htmlRenderService;

    public HtmlController(MailContentBuilder htmlRenderService) {
        this.htmlRenderService = htmlRenderService;
    }

    @GetMapping(produces = "text/html;charset=UTF-8")
    public String getHtml() {
        return htmlRenderService.buildOrderMail();
    }
}

