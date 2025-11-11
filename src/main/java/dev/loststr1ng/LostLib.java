package dev.loststr1ng;

import dev.loststr1ng.handlers.CommandHandler;
import dev.loststr1ng.utils.Logger;
import dev.loststr1ng.utils.Messages;
import org.bukkit.plugin.Plugin;

public class LostLib {

    private final Plugin plugin;
    private Messages messages;
    private CommandHandler commandHandler;
    private final Logger logger = new Logger(this);

    public LostLib(Plugin plugin){
        this.plugin = plugin;
        createMessages();
        createCommandHandler();
    }

    public Plugin getPlugin(){
        return plugin;
    }

    public void createMessages(){
        if(this.messages == null){
            this.messages = new Messages(this);
        }
    }

    public void createCommandHandler(){
        if(this.commandHandler == null){
            this.commandHandler = new CommandHandler(this);
        }
    }

    public Messages getMessages() {
        return messages;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public Logger getLogger() {
        return logger;
    }
}
