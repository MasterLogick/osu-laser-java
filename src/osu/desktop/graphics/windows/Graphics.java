package osu.desktop.graphics.windows;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwInit;

public class Graphics {
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
//        GLFWWindow.initialise();
        GL.createCapabilities();
        //todo
    }
}
