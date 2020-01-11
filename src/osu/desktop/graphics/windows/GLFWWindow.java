package osu.desktop.graphics.windows;

import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static osu.desktop.Main.DEBUG;


public class GLFWWindow {
    private long id;

    public GLFWWindow(int width, int height, String name, boolean fullscreen) {
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        id = glfwCreateWindow(width, height, name, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
        if (id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
            //todo
        }
    }

    public static void printPrimaryMonitorVideoModes() {
        System.out.println("Primary monitor video modes:");
        glfwGetVideoModes(glfwGetPrimaryMonitor()).forEach(glfwVidMode -> System.out.println(glfwVidMode.width() + "x" + glfwVidMode.height() + "@" + glfwVidMode.refreshRate()));
    }

    public static void initialise() {
        //todo window creation and setup
        if (DEBUG) GLFWWindow.printPrimaryMonitorVideoModes();
        Graphics.mainScreen = new GLFWWindow(1680, 1050, "TEST osu!laser#java", !DEBUG);
        Graphics.mainScreen.setCurrent();
        Graphics.mainScreen.setVSync(1);
    }

    public void setCurrent() {
        glfwMakeContextCurrent(id);
    }

    public void setVSync(int val) {
        glfwSwapInterval(val);
    }

    public void showWindow() {
        glfwShowWindow(id);
    }

    public void swapBuffers() {
        glfwSwapBuffers(id);
    }

    public boolean isWindowShouldClose() {
        return glfwWindowShouldClose(id);
    }

    public void shouldClose() {
        glfwSetWindowShouldClose(id, true);
    }

    public void freeCallbacks() {
        glfwFreeCallbacks(id);
    }

    public void destroy() {
        freeCallbacks();
        glfwDestroyWindow(id);
    }

    public void setResizable(boolean resizable) {
        glfwSetWindowAttrib(id, GLFW_RESIZABLE, resizable ? GLFW_TRUE : GLFW_FALSE);
    }

    public void setKeyCallbacks(GLFWKeyCallbackI glfwKeyCallbackI) {
        glfwSetKeyCallback(id, glfwKeyCallbackI);
    }

    public void setMouseCallbacks(GLFWCursorPosCallbackI cdfn) {
        glfwSetCursorPosCallback(id, cdfn);
    }

    public void grabMouse() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_DISABLED);
    }

    public void releaseMouse() {
        glfwSetInputMode(id, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
    }

    public void setMousePos(double xpos, double ypos) {
        glfwSetCursorPos(id, xpos, ypos);
    }
}
