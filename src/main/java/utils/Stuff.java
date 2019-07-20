package utils;

import com.thedeanda.lorem.LoremIpsum;

public class Stuff {

    public static String getLoremIpsum(){
        return LoremIpsum.getInstance().getWords(1000);
    }
}
