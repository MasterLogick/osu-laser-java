package osu.desktop.graphics.windows;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.glfwInit;

public class Graphics {
    public static GLFWWindow mainScreen;
    public void initialise() {
        GLFWWindow.initialise();
        GL.createCapabilities();
        //todo
    }
    private static void initGLFW() {
        GLFWErrorCallback.createPrint(System.err).set();
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");
    }
}
