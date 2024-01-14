#ifdef GL_ES
precision mediump float;
#endif

uniform float u_transitionPercent;
uniform sampler2D u_texture;
uniform sampler2D u_mask;

varying vec4 v_color;
varying vec2 v_texCoord0;

void main()
{
    vec4 texColor = texture2D(u_texture, v_texCoord0);
    vec4 mask = texture2D(u_mask, v_texCoord0);
    if (mask.r < u_transitionPercent) {
        gl_FragColor = vec4(texColor.r, texColor.g, texColor.b, 1);
    } else {
        gl_FragColor = vec4(texColor.r, texColor.g, texColor.b, 0);
    }
}