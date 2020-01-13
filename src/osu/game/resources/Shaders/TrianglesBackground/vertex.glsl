#version 330 core
layout (location = 0) in vec2 localPos;
layout (location = 1) in vec2 globalPos;
layout (location = 2) in float color;
layout (location = 3) in float scale;
layout (std140) uniform Screen
{
    mat4 projection;
    float width;
    float height;
};
out float colorOut;
void main(){
    colorOut = color;
    tmp = projection*vec4(localPos*scale+globalPos, 0.0, 1.0);
}