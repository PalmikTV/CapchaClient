package de.capcha;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import de.capcha.command.CommandManager;
import de.capcha.event.impl.EventChat;
import de.capcha.event.impl.EventKey;
import de.capcha.module.Module;
import de.capcha.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public enum capcha2 {

    INSTANCE;

    private EventBus eventBus;
    private ModuleManager ModuleManager;

    private CommandManager commandManager;

    public final void init() {
        eventBus = new EventBus();
        ModuleManager = new ModuleManager();
        commandManager = new CommandManager();

        eventBus.register(this);
    }

    @Subscribe
    public void onKey(EventKey e) {
        for (Module m : ModuleManager.getModules()) {
            if (m.getKey() == e.getKey()) {
                m.toggle();
            }
        }
    }

    @Subscribe
    public void onChat(EventChat e) {
        commandManager.handleChat(e);

    }

            public final void stop() {
                eventBus.unregister(this);
    }

            public final EventBus getEventBus() {
                return eventBus;
    }

    public final ModuleManager getModuleManager() {
        return ModuleManager;
    }

    public final CommandManager getCommandManager() {
        return commandManager;
    }

    public final void sendMessage(String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Capcha: " + message));
    }

}
