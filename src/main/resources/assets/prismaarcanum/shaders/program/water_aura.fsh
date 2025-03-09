#version 150

in vec2 texCoord0;
uniform sampler2D DiffuseSampler;
uniform float time;
out vec4 fragColor;

void main() {
    // Apply a slight vertical distortion for a water-like effect
    vec2 uv = texCoord0;
    uv.y += sin(uv.x * 10.0 + 1.0) * 0.02;

    // Sample the main framebuffer texture via DiffuseSampler
    vec4 color = texture(DiffuseSampler, uv);

    // Blend in a blue tint
    color.rgb = mix(color.rgb, vec3(0.0, 0.0, 1.0), 0.3);
    fragColor = color;
}