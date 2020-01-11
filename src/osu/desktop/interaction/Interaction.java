package osu.desktop.interaction;

import osu.desktop.interaction.audio.*;
import osu.desktop.interaction.input.*;
public class Interaction {
    public AudioSystem audio;
    public Input input;

    public void initialise() {
        audio = new AudioSystem();
        input = new Input();

        //todo
    }
}
