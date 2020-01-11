package osu.desktop.graphics.windows.opengl;

import org.lwjgl.opengl.GL;
import osu.desktop.interaction.Settings;

public class OpenGL {
    private static MSAAFramebuffer renderMSAAFramebuffer;
    public static void initialise() {
        GL.createCapabilities();
        renderMSAAFramebuffer = new MSAAFramebuffer(Integer.parseInt(Settings.MSAALevel.get()));
        Shader.loadShaders();
        Texture.loadTextures();

    }
}
