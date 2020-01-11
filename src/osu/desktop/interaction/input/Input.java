package osu.desktop.interaction.input;

import osu.desktop.interaction.Settings;

public class Input {
    private Thread windowCallback;
    private KeyBindingStore keyBindingStore;

    public Input() {
        windowCallback = new InputThread();
    }

    public void initialise() {
        loadKeyBindingStoreFromSettings();
    }

    private void loadKeyBindingStoreFromSettings() {
        keyBindingStore = new KeyBindingStore();
        String raw = Settings.KeyBindings.get();
        String[] actions = raw.split(";");
        for (String actionEntry : actions) {
            if (actionEntry.isEmpty()) continue;
            String[] info = actionEntry.split(":");
            Action action = Action.valueOf(info[0]);
            String[] combinations = info[1].split(",");
            for (String combination : combinations) {
                String[] stringKeys = combination.split("\\+");
                int[] keys = new int[stringKeys.length];
                for (int i = 0; i < keys.length; i++)
                    keys[i] = Integer.parseInt(stringKeys[i]);
                keyBindingStore.add(new KeyBinding(action, keys));
            }
        }
    }
}
