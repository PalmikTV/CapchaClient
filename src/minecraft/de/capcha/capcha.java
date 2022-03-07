package de.capcha;

import com.google.common.eventbus.EventBus;
import de.capcha.utils.ShaderBackgrundAPI2;
import de.capcha.utils.ShaderBackgrundAPI3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.Display;
import com.google.common.eventbus.EventBus;

public class capcha {
    public static String clientName = "Capcha";
    public static String clientColorName = "§5Capcha";
    public static String clientVersion = "b0.1";
    public static String clientStartingTitle = "Injecting Skid";
    public static ShaderBackgrundAPI2 backgrundAPI2;
    public static ShaderBackgrundAPI3 backgrundAPI3;
    public static ShaderBackgrundAPI2 getBackgrundAPI2() {
        return backgrundAPI2;
    }
    public static ShaderBackgrundAPI3 getBackgrundAPI3() {
        return backgrundAPI3;
    }
    public static void Start(){
        backgrundAPI2 = new ShaderBackgrundAPI2();
        backgrundAPI3 = new ShaderBackgrundAPI3();
        Display.setTitle(capcha.clientName + " " + capcha.clientVersion);
    }
}
