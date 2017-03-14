package com.tmteam.jamaestro.settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oracle.javafx.jmx.json.JSONFactory;
import com.oracle.javafx.jmx.json.JSONReader;
import jdk.nashorn.internal.parser.JSONParser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Reader;
import java.io.StringReader;

/**
 * Created by Su on 14/03/17.
 */
public class Serializer {

    public Settings read(String jsonPath){

        throw new NotImplementedException();
    }
    public void write(String jsonPath, Settings settings){

    }
    public static  ChannelSettings2 deserializeChannelSettings(String json){
        Reader reader = new StringReader(json);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return gson.fromJson(json, ChannelSettings2.class);

    }
    public static  String serializeChannelSettings(ChannelSettings2 settings){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(settings);
    }
}


