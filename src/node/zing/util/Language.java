package node.zing.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Language {

    private static HashMap<String, String> lang;

    public static boolean load(String language) {
        try {
            lang = Utils.ini2map(IOUtils.resourceToString("/language/" + language + ".ini", Charset.defaultCharset()));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static String get(String key) {
        return lang.get(key);
    }

}
