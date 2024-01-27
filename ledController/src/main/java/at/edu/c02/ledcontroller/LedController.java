package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    String[] getGroupLeds() throws Exception;

    void demo() throws IOException;
    void getLight(int id) throws IOException;

    void turnOffAllLeds() throws IOException;

    void turnOneLight() throws IOException;

    void turnAlleLighton() throws IOException;
}

