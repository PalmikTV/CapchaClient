package de.capcha.module;

import de.capcha.capcha2;
import de.capcha.interfaces.MinecraftInterface;
import de.capcha.interfaces.ModuleInterface;
import net.minecraft.client.Minecraft;

import javax.jnlp.SingleInstanceListener;

public abstract class Module implements MinecraftInterface {

    private ModuleInterface moduleInterface = getClass().getAnnotation(ModuleInterface.class);
    private String name = moduleInterface.name(), description = moduleInterface.description();
    private Category category = moduleInterface.category();
    private int key;
    private boolean toggled;

    public Module() {
        if (toggled) {
            capcha2.INSTANCE.getEventBus().register(this);
        }
        setup();
    }

    public void setup() {

    }

    public final void toggle() {
        if (toggled) {
            onDisable();
            toggled = false;
        } else {
            onEnable();
            toggled = true;
        }
    }

    public final void setToggled(boolean toggled) {
        if (toggled) {
            if (!this.toggled) {
                toggle();
            }
        } else {
            if (this.toggled)
                toggle();
        }
    }

    public final void onEnable() {
        capcha2.INSTANCE.getEventBus().register(this);
        capcha2.INSTANCE.sendMessage("Enabled " + this.name);
    }

    public final void onDisable() {
        capcha2.INSTANCE.getEventBus().unregister(this);
        capcha2.INSTANCE.sendMessage("§cDisabled " + this.name);
    }

    public final String getName() {
        return name;
    }

    public final String getDescription() {
        return description;
    }

    public final int getKey() {
        return key;
    }

    public final void setKey(int key) {
        this.key = key;
    }

    public final boolean isToggle() {
        return toggled;
    }

    public abstract void onDisabled();
}
