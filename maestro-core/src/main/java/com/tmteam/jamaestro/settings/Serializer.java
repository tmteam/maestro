package com.tmteam.jamaestro.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oracle.javafx.jmx.json.JSONFactory;
import com.oracle.javafx.jmx.json.JSONReader;
import jdk.nashorn.internal.parser.JSONParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serializer {

    public Settings2 readSettings(String jsonPath) throws IOException {

        String text;

        Path path = Paths.get(jsonPath);
        text = new String(Files.readAllBytes(path));
        return  deserializeSettings(text);
    }
    public void write(String jsonPath, Settings settings) throws IOException {
        String text = serialize(settings);
        Files.write(Paths.get(jsonPath), text.getBytes());
    }
    public static  Settings2 deserializeSettings(String json){
        Reader reader = new StringReader(json);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, Settings2.class);
    }

    public static  ChannelSettings2 deserializeChannelSettings(String json){
        Reader reader = new StringReader(json);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, ChannelSettings2.class);
    }

    public static  String serialize(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }
}


