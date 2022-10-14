package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Singleton implementation for properties reader.<br><br>
 * It encapsulates the constructor so that there could be only one instance of Properties object as well as PropertiesReader object.
 * Furthermore, the exposed methods are {@link  PropertiesReader#getInstance()  getInstance()} which returns the current instance or creates one if necessary,
 * and {@link  PropertiesReader#getInstance(String)} the version with a directory name as a parameter.
 * The {@link PropertiesReader#getValue(String) getValue(String value)} method will return the corresponding value from the properties file.<br>
 * Default location is <i>src/resources.properties</i><br>
 * @see <a href="https://refactoring.guru/design-patterns/singleton">singleton design pattern</a>
 *
 */
public class PropertiesReader {

    private static PropertiesReader instance = null;
    private static String dirName = "src/resources.properties"; //default
    private final Properties properties;

    private PropertiesReader(String _dirName) {
        properties = new Properties();
        dirName = _dirName;
        try {
            InputStream inputStream = new FileInputStream(dirName);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException(String.format("The file with relative path '%s' is not found", dirName));
        }
    }

    /**
     * Retrieves the instance of the properties reader if there is one, if not creates it and returns it.
     * @return singleton instance of the property reader
     */
    public static synchronized PropertiesReader getInstance() {
        if (instance == null) {
            instance = new PropertiesReader(dirName);
        }
        return instance;
    }

    /**
     * Retrieves the instance of the properties reader for a given name if there is one, if not creates it and returns it.
     * @param _dirName the directory name
     * @return singleton instance of the property reader
     */
    public static synchronized PropertiesReader getInstance(String _dirName) {
        if (instance == null) {
            instance = new PropertiesReader(_dirName);
        }
        return instance;
    }

    /**
     * Fetches the property value or defaults to 'the key does not exist'.
     * @param key the key in the properties dictionary
     * @return properties value
     */
    public String getValue(String key) {
        return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
    }
}