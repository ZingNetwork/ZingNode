package node.zing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties;

    private static final File PROFILE = new File("server.properties");

    private static final String NOTES = "Zing network node config file.";

    static {
        try {
            if (!PROFILE.exists()) {
                Properties properties = new Properties();
                properties.setProperty("port", "19190");
                properties.setProperty("language", "");
                properties.setProperty("verification", "off");
                properties.setProperty("zing-address", "");
                properties.setProperty("private-key", "");
                Config.properties = properties;
                save();
            } else {
                properties = new Properties();
                properties.load(new FileInputStream(PROFILE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    static void set(String key, String value) {
        properties.setProperty(key, value);
    }

    static void save() {
        try {
            properties.store(new FileOutputStream(PROFILE), NOTES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
