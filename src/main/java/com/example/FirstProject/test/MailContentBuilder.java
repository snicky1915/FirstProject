package com.example.FirstProject.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;


import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class MailContentBuilder {

    private final SpringTemplateEngine templateEngine;

    public MailContentBuilder(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildOrderXhtml() {
        Context context = new Context();
        context.setVariable("customerName", "Cuong");
        context.setVariable("total", 250000);

        List<Map<String, Object>> items = List.of(
                Map.of("name", "iPhone 13", "qty", 1, "price", 200000),
                Map.of("name", "Ốp lưng", "qty", 1, "price", 50000)
        );

        context.setVariable("items", items);

        String xhtml = templateEngine.process("order", context);
        log.info("Rendered order XHTML:\n{}", xhtml);
        return xhtml;
    }
}
