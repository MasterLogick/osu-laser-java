package osu.desktop;

import osu.desktop.graphics.windows.Graphics;
import osu.desktop.interaction.Interaction;
import osu.desktop.interaction.Settings;
import osu.desktop.logick.GameLogic;

public class Game {
    public Graphics graphics;
    public GameLogic logic;
    public Interaction interaction;

    public void initialise() {
        Settings.importSettings();
        graphics = new Graphics();
        logic = new GameLogic();
        interaction = new Interaction();
        graphics.initialise();
        logic.initialise();
        interaction.initialise();
    }

    public void run() {

    }
}
