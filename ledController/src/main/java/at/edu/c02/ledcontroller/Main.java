package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.*;
import java.util.ArrayList;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());
        ApiService apiController = new ApiServiceImpl();

        String input = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(!input.equalsIgnoreCase("exit"))
        {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();
            if(input.equalsIgnoreCase("demo"))
            {
                ledController.demo();
            }
            else if (input.equalsIgnoreCase("status")) {
                System.out.println("Please specify LED id");
                input = reader.readLine();
                ledController.getLight(Integer.parseInt(input));
            }
            else if (input.equalsIgnoreCase("groupstatus")) {
                String[] result = new String[0];
                try {
                    result = ledController.getGroupLeds();
                    for (int i = 0; i < result.length; i++) {
                        System.out.println(result[i]);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }
}
