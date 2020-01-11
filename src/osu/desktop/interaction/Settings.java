package osu.desktop.interaction;

import osu.desktop.Main;

import java.util.prefs.Preferences;

public class Settings {
    public static final Property TEST = new Property("TEST", "TEST");

    private static Preferences preferences;

    private Settings() {

    }

    public static void importSettings() {
        preferences = Preferences.userNodeForPackage(Main.class);
        validate();
        push();
    }

    private static void push() {
//todo
    }

    private static void validate() {
//todo
    }

    public static class Property {
        String name;
        String defaultValue;

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
