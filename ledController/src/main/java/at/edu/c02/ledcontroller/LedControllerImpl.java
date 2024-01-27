package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class handles the actual logic
 */
public class LedControllerImpl implements LedController {
    private final ApiService apiService;

    public LedControllerImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public String[] getGroupLeds() throws Exception {
        JSONObject lightsResponse = apiService.getLights();
        JSONArray lightsArray = lightsResponse.getJSONArray("lights");

        // Extract information for each light and add to result
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < lightsArray.length(); i++) {
            JSONObject light = lightsArray.getJSONObject(i);
            int id = light.getInt("id");
            String color = light.getString("color");
            boolean isGrouped = light.getBoolean("isGrouped");  // Annahme: Annahme, dass "isGrouped" ein boolean-Wert ist.

            // Add information to the result ArrayList
            result.add("Light ID: " + id + ", Color: " + color + ", Grouped: " + isGrouped);
        }

        // Convert the ArrayList to an array
        return result.toArray(new String[0]);
    }

    @Override
    public void demo() throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
    }
}
