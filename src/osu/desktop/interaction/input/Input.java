package osu.desktop.interaction.input;

public class Input {
    private Thread windowCallback;
    private KeyBindingStore keyBindingStore;
    public Input(){
        windowCallback = new InputThread();
    }
    public void initialise() {
        loadKeyBindingsFromSettings();
    }
    private void loadKeyBindingsFromSettings(){

    }
}
