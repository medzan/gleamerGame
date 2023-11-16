package com.ezangui.gleamer.configuration;

final public class ConfigurationLoader {
    private static volatile ConfigurationLoader instance;
    private GameConfiguration gameConfiguration;

    private ConfigurationLoader() {
        this.gameConfiguration = new DefaultGameConfiguration();
    }

    public static ConfigurationLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigurationLoader.class) {
                if (instance == null) {
                    instance = new ConfigurationLoader();
                }
            }
        }
        return instance;
    }

    public GameConfiguration loadDefault() {
        return gameConfiguration;
    }
}
