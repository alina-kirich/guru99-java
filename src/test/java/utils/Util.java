package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = Util.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static final String BASE_URL = properties.getProperty("base.url");
    public static final String USER_NAME = properties.getProperty("username");
    public static final String PASSWORD = properties.getProperty("password");
    public static final String EXPECTED_TITLE = properties.getProperty("expected.title");
}