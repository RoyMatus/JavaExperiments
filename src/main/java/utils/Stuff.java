package utils;

import com.thedeanda.lorem.LoremIpsum;
import lombok.extern.log4j.Log4j;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;
import static org.apache.log4j.helpers.LogLog.error;

@Log4j
public class Stuff {

    public static String getLoremIpsum() {
        return LoremIpsum.getInstance().getWords(1000);
    }

    public static void openUrlInBrowser(String url) {
        try {
            if (Desktop.isDesktopSupported()) Desktop.getDesktop().browse(new URI(url));
            else Runtime.getRuntime().exec("xdg-open " + url);
        } catch (IOException | URISyntaxException e) {
            error(format("Can't open %s", url), e);
        }
    }
}
