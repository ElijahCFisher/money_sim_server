package com.pasiv.money_sim_server;

import com.pasiv.money_sim_server.models.Scenario;
import com.pasiv.money_sim_server.models.Source;
import com.pasiv.money_sim_server.models.SourceRow;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Service {

    public static Scenario[] scenariosFromJson(String settingsFile, String sourcesFile) {
        JSONParser parser = new JSONParser();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Scenario scenario1 = new Scenario(), scenario2 = new Scenario();
        scenario1.setIndex(1);
        scenario2.setIndex(2);

        JSONObject scenarioSettings;
        JSONArray sources;
        try {
            scenarioSettings = (JSONObject) parser.parse(new InputStreamReader(classLoader.getResourceAsStream(settingsFile)));
            sources = (JSONArray) parser.parse(new InputStreamReader(classLoader.getResourceAsStream(sourcesFile)));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        Source scenarioSettings1 = sourceFromJson(scenarioSettings, 0);
        Source scenarioSettings2 = sourceFromJson(scenarioSettings, 1);

        HashMap<String, Source> sources1 = new HashMap<>();
        HashMap<String, Source> sources2 = new HashMap<>();
        for (Object o : sources) {
            Source source1 = sourceFromJson((JSONObject) o, 0);
            Source source2 = sourceFromJson((JSONObject) o, 1);
            sources1.put(source1.getId(), source1);
            sources2.put(source2.getId(), source2);
        }
        scenario1.setScenarioSettings(scenarioSettings1);
        scenario1.setScenarioSources(sources1);
        scenario2.setScenarioSettings(scenarioSettings2);
        scenario2.setScenarioSources(sources2);

        return new Scenario[]{scenario1, scenario2};
    }

    public static Source sourceFromJson(JSONObject json) {
        return sourceFromJson(json, -1);
    }
    public static Source sourceFromJson(JSONObject json, int scenario) {
        Source ret = new Source();
        ret.setName((String) json.get("name"));
        ret.setId((String) json.get("id"));

        ArrayList<SourceRow> rows = new ArrayList<SourceRow>();

        // looping through each row depending on which scenario it is
        for(Object row : (JSONArray)((scenario != -1) ? ((JSONArray) json.get("scenarios")).get(scenario) : json.get("rows"))) {
            SourceRow rowComp = new SourceRow();
            if (row.getClass().equals(JSONArray.class)) {
                HashMap<String, String> attributes = new HashMap<>();
                for (Object attribute : (JSONArray) row)
                    attributes.put((String)((JSONArray) attribute).get(0), (String)((JSONArray) attribute).get(1));
                rowComp.setAttributes(attributes);
            }
            else
                rowComp.setSource(sourceFromJson((JSONObject) row));
            rows.add(rowComp);
        }
        ret.setRows(rows.toArray(new SourceRow[0]));
        return ret;
    }
}
