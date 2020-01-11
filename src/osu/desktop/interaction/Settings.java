package osu.desktop.interaction;

import org.lwjgl.glfw.GLFW;
import osu.desktop.Main;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Settings {
    public static final Property TEST = new Property("TEST", "TEST");
    public static final Property VideoMode = new Property("VideoMode", "");
    public static final Property Title = new Property("Title", "osu!laser#java");
    public static final Property MonitorId = new Property("MonitorId", "0");
    public static final Property MSAALevel = new Property("MSAALevel", "4");
    public static final Property KeyBindings = new Property("KeyBindings", "Select:"+ GLFW.GLFW_KEY_ENTER);
    private static final Property[] allProps = new Property[]{
            TEST, VideoMode
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
