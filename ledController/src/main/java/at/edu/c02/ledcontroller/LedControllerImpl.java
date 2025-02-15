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
    public String[] getGroupLeds() throws IOException {
        JSONObject lightsResponse = apiService.getLights();
        JSONArray lightsArray = lightsResponse.getJSONArray("lights");

        // Extract information for each light and add to result
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < lightsArray.length(); i++) {
            JSONObject light = lightsArray.getJSONObject(i);
            int id = light.getInt("id");
            String color = light.getString("color");
            // String group = light.getString("groupByGroup");
            // Add information to the result ArrayList
            result.add("Light ID: " + id + ", Color: " + color );
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

    public void getLight(int id) throws IOException {
        JSONObject response = apiService.getLight(id);
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("Light id is: " + firstLight.getInt("id"));
        System.out.println("Light color is: " + firstLight.getString("color"));
    }

    public void turnOffAllLeds() throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));

        ArrayList<Integer> ledids=new ArrayList<>();
        ledids.add(2);
        ledids.add(10);
        ledids.add(11);
        ledids.add(12);
        ledids.add(13);
        ledids.add(14);
        ledids.add(15);
        ledids.add(16);
        for (Integer ledid : ledids) {
            ApiServiceImpl apiService1= new ApiServiceImpl();
            apiService1.setLed(ledid,"#F00",false);
        }


    }

    @Override
    public void turnOneLight() throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));
        ApiServiceImpl apiService1= new ApiServiceImpl();
        apiService1.setLed(2,"#F09",true);
    }

    @Override
    public void turnAlleLighton() throws IOException {
        // Call `getLights`, the response is a json object in the form `{ "lights": [ { ... }, { ... } ] }`
        JSONObject response = apiService.getLights();
        // get the "lights" array from the response
        JSONArray lights = response.getJSONArray("lights");
        // read the first json object of the lights array
        JSONObject firstLight = lights.getJSONObject(0);
        // read int and string properties of the light
        System.out.println("First light id is: " + firstLight.getInt("id"));
        System.out.println("First light color is: " + firstLight.getString("color"));

        ArrayList<Integer> ledids=new ArrayList<>();
        ledids.add(2);
        ledids.add(10);
        ledids.add(11);
        ledids.add(12);
        ledids.add(13);
        ledids.add(14);
        ledids.add(15);
        ledids.add(16);
        for (Integer ledid : ledids) {
            ApiServiceImpl apiService1= new ApiServiceImpl();
            apiService1.setLed(ledid,"#F09",true);
        }


    }
}
