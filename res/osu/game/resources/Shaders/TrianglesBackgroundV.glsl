#version 330 core
layout (location = 0) in vec3 localPos;
layout (location = 1) in float scale;
layout (location = 2) in float color;
layout (location = 3) in vec3 globalPos;
out float colorOut;
void main(){
    colorOut = color;
    gl_Position = vec4(localPos*scale+globalPos, 1.0);
}