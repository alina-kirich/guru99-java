package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils {
    public static void takeScreenshot(WebDriver driver, String testName) throws IOException {

        File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        File folder = new File("screenshots");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

        String timestamp = now.format(formatter);

        File destination =
                new File(folder, testName + "_" + timestamp + ".png");

        Files.copy(screenshot.toPath(), destination.toPath());
    }
}
