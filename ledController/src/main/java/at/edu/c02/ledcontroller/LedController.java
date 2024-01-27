package at.edu.c02.ledcontroller;

import java.io.IOException;

public interface LedController {
    String[] getGroupLeds() throws Exception;

    void demo() throws IOException;
}

