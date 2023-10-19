package persistence;

import model.*;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads ConsumptionList from JSON data stored in file
// Use JsonSerializationDemo as the template
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ConsumptionList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ConsumptionList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseConsumptionList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ConsumptionList from JSON object and returns it
    private ConsumptionList parseConsumptionList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ConsumptionList cl = new ConsumptionList(name);
        addList(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses list from JSON object and adds them to ConsumptionList
    private void addList(ConsumptionList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addConsumption(cl, nextThingy);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses list from JSON object and adds it to ConsumptionList
    private void addConsumption(ConsumptionList cl, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        String type = jsonObject.getString("type");
        double balance = jsonObject.getDouble("balance");
        Consumption thingy = new Consumption(date,type,balance);
        cl.addCons(date,type,balance);
    }

}
