import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class ConfigReader {
    public static final Config CONFIG = ConfigFactory.load();

    public static String getParam(final String commandName) {
        return CONFIG.getString(commandName);
    }
}
