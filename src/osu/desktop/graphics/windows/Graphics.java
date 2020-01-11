package osu.desktop.graphics.windows;

import org.lwjgl.glfw.GLFWErrorCallback;
import osu.desktop.Osu;
import osu.desktop.graphics.windows.UI.UIManager;
import osu.desktop.graphics.windows.opengl.GameDrawer;
import osu.desktop.graphics.windows.opengl.OpenGL;
import osu.desktop.interaction.Interaction;

import static org.lwjgl.glfw.GLFW.glfwInit;

public class Graphics extends Thread {
    public static GLFWWindow mainScreen;

    private static void initGLFW() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
    }

    public void initialise() {
        initGLFW();
        mainScreen = new GLFWWindow();
        mainScreen.setCurrent();
        mainScreen.setVSync(1);
        //todo create mainScreen callbacks
        OpenGL.initialise();
        UIManager.initialise();
    }

    public void run(Interaction interaction) {
        this.start();
    }

    @Override
    public void run() {
        while (!Osu.shouldClose) {
            OpenGL.prepareForDrawing();
            GameDrawer.draw();
            OpenGL.render();
        }
    }
}
