package osu.desktop.graphics.windows.opengl;

import org.lwjgl.opengl.GL;
import osu.desktop.interaction.Settings;

public class OpenGL {
    private static MSAASystem renderMSAASystem;

    public static void initialise() {
        GL.createCapabilities();
        renderMSAASystem = new MSAASystem(Integer.parseInt(Settings.MSAALevel.get()));
        Shader.loadShaders();
        Texture.loadTextures();

    }

    public static void prepareForDrawing() {
        renderMSAASystem.prepareForDraw();
        //todo
    }

    public static void prepareForUIDrawing() {
        //todo
    }

    public static void postProcessing() {
        renderMSAASystem.render(0);
        //todo
    }

    public static void render() {

    }
}
