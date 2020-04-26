package tt.psc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ExternalProperties {

    private ExternalProperties() {
    }

    private static final String NAME_OF_PROPERTIES_FILE = "app.properties";
    private static Properties properties = new Properties();
    private static InputStream inputStream = ExternalProperties.class.getClassLoader().getResourceAsStream(NAME_OF_PROPERTIES_FILE);

    public static String getProperty(String property) {
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(property);
    }
}
