package osu.desktop.graphics.windows;

import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import osu.desktop.interaction.Settings;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static osu.desktop.Main.DEBUG;


public class GLFWWindow {
    private long id;

    public GLFWWindow(int width, int height, String name, boolean fullscreen) {
        glfwDefaultWindowHints();
        if (DEBUG) glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        id = glfwCreateWindow(width, height, name, fullscreen ? glfwGetPrimaryMonitor() : NULL, NULL);
        if (id == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
            //todo
        }
    }

    public GLFWWindow() {
        if (DEBUG) GLFWWindow.printPrimaryMonitorVideoModes();

        //if video mode wasn't selected
        if (Settings.VideoMode.get().isEmpty()) {
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            glfwDefaultWindowHints();
            glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
            glfwWindowHint(GLFW_REFRESH_RATE, vidMode.refreshRate());
            id = glfwCreateWindow(vidMode.width(), vidMode.height(), Settings.Title.get(), glfwGetPrimaryMonitor(), NULL);
            Settings.VideoMode.set(vidMode.width() + "x" + vidMode.height() + "@" + vidMode.refreshRate() + ",F");
            return;
        }

        //check user defined mode available
        GLFWVidMode.Buffer b = glfwGetVideoModes(getMonitor());
        String defaultMode = Settings.VideoMode.get();
        String[] mode = defaultMode.split("[x,@]");
        AtomicBoolean hasVideoMode = new AtomicBoolean(false);
        AtomicReference<GLFWVidMode> vidMode = new AtomicReference<>();
        b.forEach(glfwVidMode -> {
            System.out.println(glfwVidMode.width() + "x" + glfwVidMode.height());
            if ((mode[0] + "x" + mode[1]).equals(glfwVidMode.width() + "x" + glfwVidMode.height())) {
                vidMode.set(glfwVidMode);
                hasVideoMode.set(true);
            }
        });
        int width, height, refreshRate;
        if (hasVideoMode.get()) {
            width = vidMode.get().width();
            height = vidMode.get().height();
            refreshRate = vidMode.get().refreshRate();
        } else {
            width = b.width();
            height = b.height();
            refreshRate = b.refreshRate();
        }
        String title = Settings.Title.get();

        //window hints setup and window creation
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        switch (mode[3]) {
            case "B": //borderless
                glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);
                id = glfwCreateWindow(width, height, title, NULL, NULL);
                Settings.VideoMode.set(width + "x" + height + "@" + refreshRate + ",B");
                break;
            case "F"://fullscreen
                glfwWindowHint(GLFW_REFRESH_RATE, refreshRate);
                id = glfwCreateWindow(width, height, title, getMonitor(), NULL);
                Settings.VideoMode.set(width + "x" + height + "@" + refreshRate + ",F");
                break;
            case "W"://window
                id = glfwCreateWindow(width, height, title, NULL, NULL);
                Settings.VideoMode.set(width + "x" + height + "@" + refreshRate + ",W");
                break;
        }
        Settings.MonitorId.set(String.valueOf(glfwGetWindowMonitor(id)));
    }

    /**
     * @return user defined or default monitor id
     */
    private static long getMonitor() {
        PointerBuffer pb = glfwGetMonitors();
        long[] arr = new long[0];
        pb.get(arr);
        long savedMonitorId = Long.parseLong(Settings.MonitorId.get());
        for (long l :
                arr) {
            if (savedMonitorId == l) return l;
        }
        return glfwGetPrimaryMonitor();
    }

    public static void printPrimaryMonitorVideoModes() {
        System.out.println("Primary monitor video modes:");
        glfwGetVideoModes(glfwGetPrimaryMonitor()).forEach(glfwVidMode -> System.out.println(glfwVidMode.width() + "x" + glfwVidMode.height() + "@" + glfwVidMode.refreshRate()));
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
