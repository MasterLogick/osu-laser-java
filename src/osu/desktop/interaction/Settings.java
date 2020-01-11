package osu.desktop.interaction;

import osu.desktop.Main;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Settings {
    public static final Property TEST = new Property("TEST", "TEST");
    private static final Property[] allProps = new Property[]{
            TEST
    };
    private static Preferences preferences;

    private Settings() {

    }

    public static void importSettings() {
        preferences = Preferences.userNodeForPackage(Main.class);
        try {
            validate();
        } catch (BackingStoreException e) {
            e.printStackTrace();//todo make better capturing
        }
    }

    private static void validate() throws BackingStoreException {
        for (Property property :
                allProps) {
            if (!preferences.nodeExists(property.name)) preferences.put(property.name, property.defaultValue);
        }
//todo
    }

    public static class Property {
        private String name;
        private String defaultValue;

        private Property(String name, String defaultValue) {
            this.name = name;
            this.defaultValue = defaultValue;
        }

        public void set(String value) {
            preferences.put(name, value);
        }

        public String get() {
            return preferences.get(name, defaultValue);
        }
    }
}
