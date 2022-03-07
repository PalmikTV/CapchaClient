package de.capcha.command;

import de.capcha.capcha2;
import de.capcha.command.impl.Bind;
import de.capcha.event.impl.EventChat;

import java.util.ArrayList;

import static de.capcha.capcha.clientColorName;

public class CommandManager {

    ArrayList<Command> commands = new ArrayList<>();

    public CommandManager() {
        init();
    }

    private final void init() {
        commands.add(new Bind());
    }

    public void handleChat(EventChat e) {
        for (Command command : commands) {
            if (e.getMessage().startsWith(command.getPrefix())) {
                e.setCancelled(true);

                if (e.getMessage().startsWith(command.getPrefix() + (command.getName())) || e.getMessage().startsWith(command.getPrefix() + command.getAlias())){
                    String message = e.getMessage().replace(command.getPrefix() + ((e.getMessage().startsWith(command.getPrefix() + (command.getName()))) ? command.getName() : command.getAlias()) + " ", " ");

                    command.onCommand(message.split(" "));
                } else {
                    capcha2.INSTANCE.sendMessage("§cError: Command not Found");
                }

            }
        }
    }

}
