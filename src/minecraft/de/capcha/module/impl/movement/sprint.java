package de.capcha.module.impl.movement;

import com.google.common.eventbus.Subscribe;
import de.capcha.event.impl.EventUpdate;
import de.capcha.interfaces.ModuleInterface;
import de.capcha.module.Category;
import de.capcha.module.Module;
import org.lwjgl.input.Keyboard;

@ModuleInterface(name = "Sprint", description = "It will automatically Sprint", category = Category.Movement)
public class sprint extends Module {

    @Subscribe
    public void onUpdate(EventUpdate e) {
        if (!mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0) {
            mc.thePlayer.setSprinting(true);
        }
    }

     @Override
    public void onDisabled() {
        super.onDisable();
        mc.thePlayer.setSprinting(false);
     }

}
