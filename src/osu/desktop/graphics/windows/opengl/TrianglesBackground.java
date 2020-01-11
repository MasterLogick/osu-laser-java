package osu.desktop.graphics.windows.opengl;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenFramebuffers;
import static org.lwjgl.opengl.GL33C.glVertexAttribDivisor;

public class TrianglesBackground {
    private static final float[] vertices = new float[]{
            0, 0, 0,
            0, 1, 0,
            (float) Math.signum(Math.PI / 3), (float) Math.cos(Math.PI / 3), 0
    };
    Shader shader;
    private int vao;
    private int vbo;
    private int instVBO;
    private int amount;
    private float[] positions;
    private float[] velocity;

    public TrianglesBackground(int trianglesAmount) {
        amount = trianglesAmount;
        positions = new float[2 * amount];
        velocity = new float[amount];
    }

    public void draw() {

    }

    public void initialise() {
        //todo init shader
        vao = glGenFramebuffers();
        glBindVertexArray(vao);
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
        instVBO = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, instVBO);
        glBufferData(GL_ARRAY_BUFFER, 4 * Float.BYTES * amount, GL_DYNAMIC_DRAW);
        glVertexAttribPointer(1, 1, GL_FLOAT, false, 4 * Float.BYTES, 0);
        glVertexAttribPointer(2, 1, GL_FLOAT, false, 4 * Float.BYTES, Float.BYTES);
        glVertexAttribPointer(3, 2, GL_FLOAT, false, 4 * Float.BYTES, 2 * Float.BYTES);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);
        glEnableVertexAttribArray(3);
        glVertexAttribDivisor(1, 1);
        glVertexAttribDivisor(2, 1);
        glVertexAttribDivisor(3, 1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }
}
