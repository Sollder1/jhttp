package de.sollder1.jhttp.config;

public final class ConfigManager {

    private static ConfigurationPojo config;

    public static void init() {
        config = new ConfigurationPojo();
    }

    public static ConfigurationPojo get() {
        return config;
    }


}
