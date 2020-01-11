package osu.desktop.logic;

import osu.desktop.graphics.windows.Graphics;
import osu.desktop.interaction.Interaction;

public class OsuLogic {
    public void initialise() {
        //todo
    }

    public void run(Graphics graphics, Interaction interaction) {
        graphics.run(interaction);
        interaction.run(graphics);

    }
}
