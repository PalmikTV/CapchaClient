package de.capcha.utils;


import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class ShaderBackgrundAPI2 {

    public static final String VERTEX_SHADER = "#version 130\n\nvoid main() {\n    gl_TexCoord[0] = gl_MultiTexCoord0;\n    gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;\n}";
    private Minecraft mc = Minecraft.getMinecraft();
    private int program;
    private long startTime;
    private String fragment;

    public ShaderBackgrundAPI2() {
//		this("#ifdef GL_ES\r\nprecision mediump float;\r\n#endif\r\n\r\n#extension GL_OES_standard_derivatives : enable\r\n\r\nuniform float time;\r\nuniform vec2 resolution;\r\n\r\n// \r\nfloat rand(float n)\r\n{\r\n\tfloat fl = floor(n);\r\n\tfloat fc = fract(n);\r\n\treturn mix(fract(sin(fl)), fract(sin(fl + 1.0)), fc);\r\n}\r\n\r\n// \r\nvec2 rand2(in vec2 p)\r\n{\r\n\treturn fract(\r\n\t\tvec2(\r\n\t\t\tsin(p.x * 1.32 + p.y * 54.077),\r\n\t\t\tcos(p.x * 91.32 + p.y * 9.077)\r\n\t\t)\r\n\t);\r\n}\r\n\r\n// iq\r\n// https://www.iquilezles.org/www/articles/voronoilines/voronoilines.htm\r\nfloat voronoi(in vec2 v, in float e)\r\n{\r\n\tvec2 p = floor(v);\r\n\tvec2 f = fract(v);\r\n\t\r\n\tvec2 res = vec2(8.0);\r\n\t\r\n\tfor(int j = -1; j <= 1; ++j)\r\n\t\tfor(int i = -1; i <= 1; ++i)\r\n\t\t{\r\n\t\t\tvec2 b = vec2(i, j);\r\n\t\t\tvec2 r = b - f + rand2(p + b);\r\n\t\t\t\r\n\t\t\t// \r\n\t\t\tfloat d = max(abs(r.x), abs(r.y));\r\n\t\t\t\r\n\t\t\tif(d < res.x)\r\n\t\t\t{\r\n\t\t\t\tres.y = res.x;\r\n\t\t\t\tres.x = d;\r\n\t\t\t}\r\n\t\t\t\r\n\t\t\telse if(d < res.y)\r\n\t\t\t{\r\n\t\t\t\tres.y = d;\r\n\t\t\t}\r\n\t\t}\r\n\t\r\n\tvec2 c = sqrt(res);\r\n\tfloat dist = c.y - c.x;\r\n\t\r\n\t// \r\n\treturn 1.0 - smoothstep(0.0, e, dist);\r\n}\r\n\r\n// \r\nmat2 rotate(in float a)\r\n{\r\n\treturn mat2(cos(a), -sin(a), sin(a), cos(a));\r\n}\r\n\r\nvoid main(void)\r\n{\r\n\t// \r\n\tvec2 uv =  gl_FragCoord.xy / resolution * 4.0 - 23.0;\r\n\tuv.y *= resolution.y / resolution.x;\r\n\tuv *= rotate(0.3);\r\n\t\r\n\t// \r\n\tfloat value = 0.0;     \r\n\tfloat light = 0.0;\r\n\t\r\n\tfloat f = 1.0;    // UV\r\n\tfloat a = 0.7;    // value\r\n\t\r\n\t\r\n\tfor(int i = 0; i < 3; ++i)\r\n\t{\r\n\t\t// \r\n\t\tfloat v1 = voronoi(uv * f + 1.0 + time * 0.2 , 0.1);\r\n\t\tv1 = pow(v1, 2.0);\r\n\t\tvalue += a * rand(v1 * 5.5 + 0.1);\r\n\t\t\r\n\t\t// \r\n\t\tfloat v2 = voronoi(uv * f * 1.5 + 5.0 + time, 0.2) * 1.1;\r\n\t\tv2 = pow(v2, 5.0);\r\n\t\tlight += pow(v1 * (0.5 * v2), 1.5);\r\n\t\t\r\n\t\t// \r\n\t\tf *= 2.0;\r\n\t\ta *= 0.6;\r\n\t}\r\n\t\r\n\t// \r\n\tvec3 color;\r\n\tcolor += vec3(0.8, 0.0, 0.0) * value;\r\n\tcolor += vec3(0.4, 0.0, 0.0) * light;\r\n\t\r\n\t// \r\n\tgl_FragColor = vec4(color, 1.0);\r\n}");
//        this("//----from http://glslsandbox.com/e#56769.0\n" +
//                "//----Removed mouse-dependency, asymetrical from start\n" +
//                "//----\n" +
//                "\n" +
//                "#ifdef GL_ES\n" +
//                "precision mediump float;\n" +
//                "#endif\n" +
//                "\n" +
//                "uniform float time;\n" +
//                "uniform vec2 mouse;\n" +
//                "uniform vec2 resolution;\n" +
//                "\n" +
//                "// a raymarching experiment by kabuto\n" +
//                "\n" +
//                "#define R_FACTOR 21.\n" +
//                "#define G_FACTOR 0.\n" +
//                "#define B_FACTOR 21.\n" +
//                "\n" +
//                "const int MAXITER = 42;\n" +
//                "\n" +
//                "vec3 field(vec3 p) {\n" +
//                "\tp *= .1;\n" +
//                "\tfloat f = 0.1;\n" +
//                "\tfor (int i = 0; i < 5; i++) {\n" +
//                "\t\tp = p.yzx*mat3(.8,.6,0,-.6,.8,0,0,0,1);\n" +
//                "\t\tp += vec3(.123,.456,.789)*float(i);\n" +
//                "\t\tp = abs(fract(p)-.5);\n" +
//                "\t\tp *= 2.0;\n" +
//                "\t\tf *= 2.0;\n" +
//                "\t}\n" +
//                "\tp *= p;\n" +
//                "\treturn sqrt(p+p.yzx)/f-.002;\n" +
//                "}\n" +
//                "\n" +
//                "void main( void ) {\n" +
//                "\tvec3 dir = normalize(vec3((gl_FragCoord.xy-resolution*.5)/resolution.x,1.));\n" +
//                "\tvec3 pos = vec3(.4, .5,time);\n" +
//                "\tvec3 color = vec3(0);\n" +
//                "\tfor (int i = 0; i < MAXITER; i++) {\n" +
//                "\t\tvec3 f2 = field(pos);\n" +
//                "\t\tfloat f = min(min(f2.x,f2.y),f2.z);\n" +
//                "\t\t\n" +
//                "\t\tpos += dir*f;\n" +
//                "\t\tcolor += float(MAXITER-i)/(f2+.001);\n" +
//                "\t}\n" +
//                "\tvec3 color3 = vec3(1.-1./(1.+color*(.09/float(MAXITER*MAXITER))));\n" +
//                "\tcolor3 *= color3;\n" +
//                "\tgl_FragColor = vec4(color3.r*R_FACTOR, color3.g*G_FACTOR, color3.b*B_FACTOR,1.);\n" +
//                "}");
    	 this("precision mediump float;\n" +
                 "\n" +
                 "uniform float time;\n" +
                 "uniform vec2 mouse;\n" +
                 "uniform vec2 resolution;\n" +
                 "\n" +
                 "float spike(float x) {\n" +
                 "\tx = mod(x, 2.0);\n" +
                 "\t\n" +
                 "\tif (x < 1.0)\n" +
                 "\t\treturn x * x;\n" +
                 "\t\n" +
                 "\tx = 2.0 - x;\n" +
                 "\t\n" +
                 "\treturn x * x;\n" +
                 "}\n" +
                 "\n" +
                 "\n" +
                 "float noise(float x, float amplitude) {\n" +
                 "\tfloat n = sin(x*8.0 + time) * 0.05 +\n" +
                 "\t\tsin(x*27.3 + time*0.5) * 0.005 + \n" +
                 "\t\tsin(time) * 0.01;\n" +
                 "\treturn n * amplitude;\n" +
                 "}\n" +
                 "\n" +
                 "const vec3 sky_high = vec3(255, 221, 128) / 255.0;\n" +
                 "const vec3 sky_low = vec3(255, 174, 107) / 255.0;\n" +
                 "const vec3 water = vec3(97, 160, 171) / 255.0;\n" +
                 "\n" +
                 "float fog = 10.0;\n" +
                 "\n" +
                 "void main() {\n" +
                 "\n" +
                 "\tvec2 uv = (gl_FragCoord.xy / resolution.xy);\n" +
                 "\tvec2 sunuv = (gl_FragCoord.xy * 2.0 - resolution) / min(resolution.x, resolution.y);\n" +
                 "\n" +
                 "\tfloat v = 0.0;\n" +
                 "\t\n" +
                 "\tvec3 sun = vec3(179, 107, 0) / 255.0;\n" +
                 "\t\n" +
                 "\tif (distance(sunuv, vec2(0.0)) > 0.5)\n" +
                 "\t\tsun = vec3(0.0);\n" +
                 "\n" +
                 "\tfloat n0 = noise(uv.x * 3.18, 0.3);\n" +
                 "\tfloat n1 = noise(uv.x * 2.34, 0.4);\n" +
                 "\tfloat n2 = noise(uv.x * 2.14, 0.6);\n" +
                 "\tfloat n3 = noise(uv.x * 1.4, 0.8);\n" +
                 "\tfloat n4 = noise(uv.x * 1.23, 1.0);\n" +
                 "\t\n" +
                 "\tif (uv.y < 0.2 + n4) {\n" +
                 "\t\tv = 0.5;\n" +
                 "\t} else if (uv.y < 0.3 + n3) {\n" +
                 "\t\tv = 0.4;\n" +
                 "\t} else if (uv.y < 0.35 + n2) {\n" +
                 "\t\tv = 0.3;\n" +
                 "\t} else if (uv.y < 0.385 + n1) {\n" +
                 "\t\tv = 0.2;\n" +
                 "\t} else if (uv.y < 0.41 + n0) {\n" +
                 "\t\tv = 0.1;\n" +
                 "\t}\n" +
                 "\n" +
                 "\tvec3 color = mix(sky_low, sky_high, uv.y) + sun;\n" +
                 "\t\n" +
                 "\tvec3 red = vec3(1.0,0.2,0.0);\n" +
                 "\tvec2 P0 = sunuv + vec2(0.0, 0.3);\n" +
                 "\tfloat r = (1.0 - length(P0));\n" +
                 "\tred = red * vec3(r);\n" +
                 "\n" +
                 "\tif (v > 0.0) {\n" +
                 "\t\tvec3 water_color = water * v;\n" +
                 "\t\tcolor = mix(mix(sky_high, sky_low, 0.5), water_color, clamp(1.0-(uv.y*uv.y*uv.y)*fog, 0.0, 1.0)) + red;\n" +
                 "\t\t\n" +
                 "\t}\n" +
                 "\t\n" +
                 "\tgl_FragColor = vec4(color, 1.0);\n" +
                 "}");

    }

    public ShaderBackgrundAPI2(String fragment) {
        this.program = GL20.glCreateProgram();
        this.startTime = System.currentTimeMillis();
        initShader(fragment);
        this.fragment = fragment;
    }

    public void initShader(String frag) {
        int vertex = GL20.glCreateShader(35633), fragment = GL20.glCreateShader(35632);
        GL20.glShaderSource(vertex, VERTEX_SHADER);
        GL20.glShaderSource(fragment, frag);
        GL20.glValidateProgram(this.program);
        GL20.glCompileShader(vertex);
        GL20.glCompileShader(fragment);
        GL20.glAttachShader(this.program, vertex);
        GL20.glAttachShader(this.program, fragment);
        GL20.glLinkProgram(this.program);
    }

    public void renderFirst() {
        GL11.glClear(16640);
        bindShader();
    }

    public void renderSecond(int scaledWidth, int scaledHeight) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glBegin(7);
        GL11.glTexCoord2d(0.0D, 1.0D);
        GL11.glVertex2d(0.0D, 0.0D);
        GL11.glTexCoord2d(0.0D, 0.0D);
        GL11.glVertex2d(0.0D, scaledHeight);
        GL11.glTexCoord2d(1.0D, 0.0D);
        GL11.glVertex2d(scaledWidth, scaledHeight);
        GL11.glTexCoord2d(1.0D, 1.0D);
        GL11.glVertex2d(scaledWidth, 0.0D);
        GL11.glEnd();
        GL20.glUseProgram(0);
    }

    public void renderShader() {
        ScaledResolution sr = new ScaledResolution(this.mc);
        this.renderFirst();
        this.addDefaultUniforms();
        this.renderSecond(sr.getScaledWidth(), sr.getScaledHeight());
    }

    public void renderShader(int width, int height) {
        this.renderFirst();
        this.addDefaultUniforms();
        this.renderSecond(width, height);
    }

    public void bindShader() {
        GL20.glUseProgram(this.program);
    }

    public void addDefaultUniforms() {
        GL20.glUniform2f(GL20.glGetUniformLocation(this.program, "resolution"), this.mc.displayWidth,
                this.mc.displayHeight);
        float time = (float) (System.currentTimeMillis() - this.startTime) / 1000.0F;
        GL20.glUniform1f(GL20.glGetUniformLocation(this.program, "time"), time);
    }
}
