package at.edu.c02.ledcontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class LedControllerTest {
    /**
     * This test is just here to check if tests get executed. Feel free to delete it when adding your own tests.
     * Take a look at the stack calculator tests again if you are unsure where to start.
     */
    @Test
    public void dummyTest() {
        assertEquals(1, 1);
    }


    public void testGetGroupLeds() throws Exception {
        // Mock erstellen
        ApiService mockApiService = Mockito.mock(ApiService.class);

        // Mock-Verhalten festlegen
        JSONObject mockLightsResponse = new JSONObject();
        JSONArray mockLightsArray = new JSONArray();
        mockLightsArray.put(new JSONObject().put("id", 1).put("color", "red").put("isGrouped", true));
        mockLightsArray.put(new JSONObject().put("id", 2).put("color", "green").put("isGrouped", false));
        mockLightsResponse.put("lights", mockLightsArray);
        when(mockApiService.getLights()).thenReturn(mockLightsResponse);

        // LedController mit dem Mock-ApiService erstellen
        LedController ledController = new LedControllerImpl(mockApiService);

        // Methode aufrufen und Ergebnis überprüfen
        String[] result = ledController.getGroupLeds();
        String[] expected = {"Light ID: 1, Color: red, Grouped: true", "Light ID: 2, Color: green, Grouped: false"};
        assertArrayEquals(expected, result);


    }

}
