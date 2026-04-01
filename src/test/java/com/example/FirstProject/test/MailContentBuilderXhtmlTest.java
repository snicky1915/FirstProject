package com.example.FirstProject.test;

import org.junit.jupiter.api.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MailContentBuilderXhtmlTest {

    @Test
    void shouldRenderValidXhtmlExample() {
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.XML);
        resolver.setCharacterEncoding("UTF-8");

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        Context context = new Context();
        context.setVariable("customerName", "Cuong");
        context.setVariable("total", 250000);
        context.setVariable("items", List.of(
                Map.of("name", "iPhone 13", "qty", 1, "price", 200000),
                Map.of("name", "Case", "qty", 1, "price", 50000)
        ));

        String xhtml = templateEngine.process("order", context);
        System.out.println("Rendered XHTML:\n" + xhtml);

        assertTrue(xhtml.startsWith("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"));
        assertTrue(xhtml.contains("<html xmlns=\"http://www.w3.org/1999/xhtml\">"));
        assertTrue(xhtml.contains("<br />"));
    }
}
