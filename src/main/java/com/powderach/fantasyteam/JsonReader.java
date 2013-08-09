package com.powderach.fantasyteam;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

    public JSONObject readJsonFromUrl(String url) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(), Charset.forName("UTF-8")))) {
            String jsonText = readAll(reader);
            Object parsed = new JSONParser().parse(jsonText);
            return (JSONObject) parsed;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    private String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int cp;
        while ((cp = reader.read()) != -1) {
            stringBuilder.append((char) cp);
        }
        return stringBuilder.toString();
    }

}
