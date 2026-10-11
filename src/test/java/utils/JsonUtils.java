package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper objectMapper =
            new ObjectMapper();

    public static LoginData[] readLoginData(String filePath)
            throws IOException {

        return objectMapper.readValue(
                new File(filePath),
                LoginData[].class
        );
    }
}
