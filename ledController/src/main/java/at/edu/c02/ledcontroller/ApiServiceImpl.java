package at.edu.c02.ledcontroller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class should handle all HTTP communication with the server.
 * Each method here should correspond to an API call, accept the correct parameters and return the response.
 * Do not implement any other logic here - the ApiService will be mocked to unit test the logic without needing a server.
 */
public class ApiServiceImpl implements ApiService {
    /**
     * This method calls the `GET /getLights` endpoint and returns the response.
     * TODO: When adding additional API calls, refactor this method. Extract/Create at least one private method that
     * handles the API call + JSON conversion (so that you do not have duplicate code across multiple API calls)
     *
     * @return `getLights` response JSON object
     * @throws IOException Throws if the request could not be completed successfully
     *
     *
     *
     */


    private JSONObject performApiRequest(URL url,String method, String requestBody) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("X-Hasura-Group-ID", "");
        connection.setRequestProperty("Content-Type", "application/json");
        if ("POST".equals(method) || "PUT".equals(method)) {
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                os.write(requestBody.getBytes());
                os.flush();
            }
        }
        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new IOException("Error: API request failed with response code " + responseCode);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();

        int character;
        while ((character = reader.read()) != -1) {
            sb.append((char) character);
        }

        String jsonText = sb.toString();
        return new JSONObject(jsonText);
    }
    @Override
    public JSONObject getLights() throws IOException
    {
        URL url = new URL("https://balanced-civet-91.hasura.app/api/rest/getLights");
        return performApiRequest(url, "GET", null);}
        // Connect to the server
//        URL url = new URL("https://balanced-civet-91.hasura.app/api/rest/getLights");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        // and send a GET request
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("X-Hasura-Group-ID", "Todo");
//        // Read the response code
//        int responseCode = connection.getResponseCode();
//        if(responseCode != HttpURLConnection.HTTP_OK) {
//            // Something went wrong with the request
//            throw new IOException("Error: getLights request failed with response code " + responseCode);
//        }
//
//        // The request was successful, read the response
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        // Save the response in this StringBuilder
//        StringBuilder sb = new StringBuilder();
//
//        int character;
//        // Read the response, character by character. The response ends when we read -1.
//        while((character = reader.read()) != -1) {
//            sb.append((char) character);
//        }
//
//        String jsonText = sb.toString();
//        // Convert response into a json object
//        return new JSONObject(jsonText);

//    }

    @Override
   public JSONObject getLight(int id) throws IOException {
        URL url = new URL("https://balanced-civet-91.hasura.app/api/rest/lights/" + id);
        return performApiRequest(url,"GET", null);


}

    @Override
    public JSONObject setLed(int id, String color, boolean state) throws IOException {
        URL url = new URL("https://balanced-civet-91.hasura.app/api/rest/setLight"
);
        JSONObject requestBody = new JSONObject();
        requestBody.put("color", "#f00");
        requestBody.put("state", true);
        requestBody.put("id", 15);





        return performApiRequest(url, "PUT", requestBody.toString());
    }
}