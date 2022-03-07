package de.capcha.command.impl;

import de.capcha.capcha2;
import de.capcha.command.Command;
import org.lwjgl.input.Keyboard;

public class Bind extends Command {

    public Bind() {
        super("Bind", "b", ".bind [module] [key]");
    }

    @Override
    public void onCommand(String[] args) {
        capcha2.INSTANCE.sendMessage(String.valueOf(args.length));
        if (args.length != 3) {
            capcha2.INSTANCE.sendMessage(getHelp());
        } else {
            try {

                capcha2.INSTANCE.getModuleManager().getModule(args[1]).setKey(Keyboard.getKeyIndex(args[2]));
                capcha2.INSTANCE.sendMessage("Bound" + capcha2.INSTANCE.getModuleManager().getModule(args[1]).getName() + " to: " + Keyboard.getKeyName(capcha2.INSTANCE.getModuleManager().getModule(args[2]).getKey()));
            } catch (Exception e) {
                capcha2.INSTANCE.sendMessage("Something went wrong!\nError: " + e.getMessage());
            }
        }
    }

    @Override
    public void Command(String[] args) {

    }

}
