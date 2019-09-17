package utils;

import com.thedeanda.lorem.LoremIpsum;
import lombok.extern.log4j.Log4j;

@Log4j
public class Stuff {

    private Stuff() {
        throw new IllegalStateException("Utility class");
    }

    public static String getLoremIpsum() {
        return LoremIpsum.getInstance().getWords(1000);
    }

}
