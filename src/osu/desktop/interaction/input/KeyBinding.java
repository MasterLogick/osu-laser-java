package osu.desktop.interaction.input;

public class KeyBinding {
    int[] keyCombination;
    Action action;

    public KeyBinding(Action action, int... keyCombination) {
        this.action = action;
        this.keyCombination = keyCombination;
    }
}
