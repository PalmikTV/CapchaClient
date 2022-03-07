package de.capcha.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import de.capcha.event.impl.EventUpdate;
import de.capcha.interfaces.ModuleInterface;
import de.capcha.module.Category;
import de.capcha.module.Module;
import org.lwjgl.input.Keyboard;

@ModuleInterface(name = "vanillafly", description = "fly on shit servers", category = Category.Movement)
public class vanillafly extends Module {

        @Subscribe
        public void onUpdate(EventUpdate e) {
            mc.thePlayer.capabilities.isFlying = true;
        }

        @Override
        public void onDisabled() {
        super.onDisable();
            mc.thePlayer.capabilities.isFlying = false;
        }

}


