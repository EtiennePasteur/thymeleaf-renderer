package com.etiennepasteur.thymeleaf_renderer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

@Component
public class JsonMessageSource extends AbstractMessageSource {

    private static final String TRANSLATIONS_FILE_PATH = "src/main/resources/static/translations.json";
    private Map<String, String> messages;
    private long lastModified;

    public JsonMessageSource() throws IOException {
        loadMessages();
    }

    private void loadMessages() throws IOException {
        messages = new ObjectMapper().readValue(Files.readAllBytes(Paths.get(TRANSLATIONS_FILE_PATH)), Map.class);
        lastModified = Files.getLastModifiedTime(Paths.get(TRANSLATIONS_FILE_PATH)).toMillis();
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        try {
            long currentModified = Files.getLastModifiedTime(Paths.get(TRANSLATIONS_FILE_PATH)).toMillis();
            if (currentModified > lastModified) {
                loadMessages();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = messages.get(code);
        return message != null ? new MessageFormat(message, locale) : null;
    }
}