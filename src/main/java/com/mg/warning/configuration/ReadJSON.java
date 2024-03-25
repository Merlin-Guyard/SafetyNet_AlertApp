package com.mg.warning.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.warning.model.Json;
import org.springframework.stereotype.Component;
import org.tinylog.Logger;

import java.io.IOException;
import java.io.InputStream;


@Component
public class ReadJSON {


    public Json readEntities() throws IOException {

        String fileName = "data.json";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream(fileName);

        ObjectMapper mapper = new ObjectMapper();

        Logger.info("JSON imported successfully");

        return mapper.readValue(is, Json.class);
    }

}