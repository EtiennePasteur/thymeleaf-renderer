package com.etiennepasteur.thymeleaf_renderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.Map;

@Controller
public class ThymeleafController {

    @Autowired
    private JsonService jsonService;

    @GetMapping("/{fileName}")
    public String serveFile(@PathVariable String fileName, Model model) {
        try {
            Map<String, Object> jsonData = jsonService.readJsonFile("src/main/resources/static/data.json");
            model.addAllAttributes(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}