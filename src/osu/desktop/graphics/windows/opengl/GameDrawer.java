package osu.desktop.graphics.windows.opengl;

import osu.desktop.graphics.windows.UI.UIManager;

public class GameDrawer {

    public static void draw() {
        //todo
        OpenGL.prepareForUIDrawing();
        UIManager.draw();
    }
}
