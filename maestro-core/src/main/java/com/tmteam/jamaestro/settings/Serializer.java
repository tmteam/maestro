package com.tmteam.jamaestro.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serializer {

    public static MaestroSettings readSettings(String jsonPath) throws IOException {

        String text;

        Path path = Paths.get(jsonPath);
        text = new String(Files.readAllBytes(path));
        return  deserializeSettings(text);
    }
    public static void write(String jsonPath, Object obj) throws IOException {
        String text = serialize(obj);
        Files.write(Paths.get(jsonPath), text.getBytes());
    }
    public static MaestroSettings deserializeSettings(String json){
        Reader reader = new StringReader(json);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, MaestroSettings.class);
    }

    public static ChannelSettings deserializeChannelSettings(String json){
        Reader reader = new StringReader(json);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.fromJson(json, ChannelSettings.class);
    }

    public static  String serialize(Object obj){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }
}


