package com.pasiv.money_sim_server;

import com.pasiv.money_sim_server.models.Scenario;
import com.pasiv.money_sim_server.models.Source;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class Controller {

    @GetMapping(value="/")
    public static String index() {
        return "Hello World";
    }

    @GetMapping(value="/scenarios/")
    public static Scenario[] scenarios() {
        System.out.println("GETting scenarios for user");
        // TBI
        return Service.scenariosFromJson("sources1ScenarioSettings.json", "sources1.json");
    }

    @PostMapping(value="/{scenarioId}/source/")
    public static ResponseEntity<Void> addSource(@PathVariable(value="scenarioId") String scenarioId,
                                           @RequestBody Source body) {
        System.out.println("Adding source" + body + "to scenario " + scenarioId);
        // TBI
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/{scenarioId}/source/{sourceId}/")
    public static ResponseEntity<Void> editSource(@PathVariable(value="scenarioId") String scenarioId,
                                  @PathVariable(value="sourceId") String sourceId,
                                  @RequestBody Source body) {
        System.out.println("Editing source " + body);
        // TBI
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value="/{scenarioId}/source/{sourceId}/")
    public static ResponseEntity<Void> deleteSource(@PathVariable(value="scenarioId") String scenarioId,
                                  @PathVariable(value="sourceId") String sourceId) {
        System.out.println("Deleting source " + sourceId);
        // TBI
        return ResponseEntity.ok().build();
    }

//    @RequestMapping(value="/calculate/{userId}")
//    public static String calculate(@PathVariable(value="userID") String userId) throws FileNotFoundException, ParseException {
//        JSONParser parser = new JSONParser(new FileReader("./src/main/resources/" + userId + ".json"));
//        JSONArray user = (JSONArray) parser.parse();
//
//        Calculator calc = new Calculator(user);
//
//        return calc.calculate();
//    }
}
