package osu.desktop;

import osu.desktop.graphics.windows.Graphics;
import osu.desktop.interaction.Interaction;
import osu.desktop.interaction.Settings;
import osu.desktop.logic.OsuLogic;

public class Osu {
    public static boolean shouldClose = false;
    public Graphics graphics;
    public OsuLogic logic;
    public Interaction interaction;

    public void initialise() {
        Settings.importSettings();
        graphics = new Graphics();
        logic = new OsuLogic();
        interaction = new Interaction();
        graphics.initialise();
        logic.initialise();
        interaction.initialise();
    }

    public void run() {
        logic.run(graphics, interaction);
    }
}
