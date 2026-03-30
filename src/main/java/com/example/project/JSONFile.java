package com.example.project;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONFile {

    // read a json file and return an array
    public static JSONArray readArray(String fileName) {
        //
        // read the birthday.json file and iterate over it
        //

        // JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        JSONArray data = null;

        try (FileReader reader = new FileReader(fileName)) {
            Object obj = jsonParser.parse(reader);
            data = (JSONArray) obj;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
