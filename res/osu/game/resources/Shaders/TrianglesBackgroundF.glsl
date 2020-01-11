#version 330 core
in float colorOut;
out vec4 color;
void main()
{
    color = vec4(colorOut, colorOut, colorOut, 1.0f);
}