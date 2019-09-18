package utils;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.apache.log4j.helpers.LogLog.error;

@Log4j
public class BrowserUtils {

    private BrowserUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void openUrlInDefaultBrowser(String url) {

        try {
            if (Desktop.isDesktopSupported()) Desktop.getDesktop().browse(new URI(url));
            else Runtime.getRuntime().exec("xdg-open " + url);
        } catch (IOException | URISyntaxException e) {
            error(format("Can't open %s", url), e);
        }
    }

    public static void openUrlInChrome(String url) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome-driver2/chromedriver.exe");
        System.setProperty("selenide.browser", "chrome");

        WebDriver driver = new ChromeDriver();
        driver.get(url);
    }

}
