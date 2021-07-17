package node.zing.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Utils {

    /**
     * Properties 转 Map
     * @param ini Properties实例
     * @return Map实例
     */
    public static HashMap<String, String> ini2map(Properties ini) {
        return new HashMap<String, String>((Map) ini);
    }

    public static HashMap<String, String> ini2map(File file) {
        try {
            return ini2map(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> ini2map(InputStream stream) {
        try {
            Properties properties = new Properties();
            properties.load(stream);
            return ini2map(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HashMap<String, String> ini2map(String content) {
        HashMap<String, String> d = new HashMap();
        String[] var4 = content.split("\n");
        int var5 = var4.length;
        for(int var6 = 0; var6 < var5; ++var6) {
            String line = var4[var6];
            line = line.trim();
            if (!line.equals("") && line.charAt(0) != '#') {
                String[] t = line.split("=");
                if (t.length >= 2) {
                    String key = t[0];
                    String value = "";
                    for(int i = 1; i < t.length - 1; ++i) {
                        value = value + t[i] + "=";
                    }
                    value = value + t[t.length - 1];
                    if (!value.equals("")) {
                        d.put(key, value);
                    }
                }
            }
        }
        return d;
    }

}
