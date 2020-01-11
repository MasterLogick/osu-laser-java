package osu.desktop.interaction;

import osu.desktop.graphics.windows.Graphics;
import osu.desktop.interaction.audio.AudioSystem;
import osu.desktop.interaction.input.Input;

public class Interaction {
    public AudioSystem audio;
    public Input input;

    public void initialise() {
        audio = new AudioSystem();
        input = new Input();
        audio.initialise();
        input.initialise();
        //todo
    }

    public void run(Graphics graphics) {

    }
}
