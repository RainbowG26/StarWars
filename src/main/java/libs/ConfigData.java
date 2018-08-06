package libs;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigData {
    public static String filePath = "src/main/resources/filePath.properties";

    public static String getValueFromFile(String key, String fileName) throws IOException {
        Properties p = new Properties();
        // Create stream for reading from file
        FileInputStream cfg = new FileInputStream(fileName);
        // Load Properties from input stream
        p.load(cfg);
        cfg.close();

        return (p.getProperty(key));
    }

    public static String getFilePathValue(String key) {

        try {
            return (getValueFromFile(key, filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }
}
