package osu.desktop.interaction.input;

import osu.desktop.interaction.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KeyBindingStore {
    private HashMap<Action, ArrayList<int[]>> store;

    public KeyBindingStore() {
        store = new HashMap<>();
    }

    public void add(KeyBinding binding) {
        int[] combination = binding.keyCombination;
        Arrays.sort(combination);
        ArrayList<int[]> keyBindings = getKeyBindings(binding);
        if (keyBindings != null)
            keyBindings.add(combination);
        else {
            keyBindings = new ArrayList<>();
            keyBindings.add(combination);
            store.put(binding.getAction(), keyBindings);
        }
    }

    public void update(KeyBinding replacing, int[] newCombination) {
        ArrayList<int[]> keyBindings = getKeyBindings(replacing);
        int[] replacement = Arrays.copyOf(replacing.keyCombination, replacing.keyCombination.length);
        Arrays.sort(replacement);
        keyBindings.replaceAll(ints -> Arrays.equals(ints, replacement) ? newCombination : ints);
    }

    public void remove(KeyBinding remove) {
        ArrayList<int[]> keyBindings = getKeyBindings(remove);
        if (keyBindings != null) {
            int[] removing = Arrays.copyOf(remove.keyCombination, remove.keyCombination.length);
            Arrays.sort(removing);
            keyBindings.removeIf(ints -> Arrays.equals(ints, removing));
        }
    }

    private ArrayList<int[]> getKeyBindings(Action action) {
        return store.get(action);
    }

    private ArrayList<int[]> getKeyBindings(KeyBinding binding) {
        return getKeyBindings(binding.action);
    }

    public void pushToSettings() {
        StringBuilder sb = new StringBuilder();
        store.forEach((action, ints) -> {
            sb.append(action.ordinal()).append(":");
            ints.forEach(ints1 -> {
                for (int i :
                        ints1) {
                    sb.append(i).append("+");
                }
                sb.append(",");
            });
            sb.append(";");
        });
        Settings.KeyBindings.set(sb.toString());
    }
}
