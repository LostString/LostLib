package dev.loststr1ng.handlers;

import dev.loststr1ng.LostLib;
import dev.loststr1ng.commands.CommandBase;
import dev.loststr1ng.commands.CustomCommand;
import dev.loststr1ng.commands.LBCommand;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private final Plugin plugin;
    private final LostLib lib;
    private final Map<String, CustomCommand> commands;


    public CommandHandler(LostLib lib){
        this.plugin = lib.getPlugin();
        this.lib = lib;
        this.commands = new HashMap<>();
    }

    public boolean addCommand(CustomCommand customCommand){
        if(commands.containsKey(customCommand.name())){
            return false;
        }
        commands.put(customCommand.name(), customCommand);
        return true;
    }

    private CommandMap getCommandMap(){
        CommandMap commandMap = null;
        try{
            Field f = plugin.getServer().getPluginManager().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            commandMap = (CommandMap) f.get(plugin.getServer().getPluginManager());
        }catch (Exception e){
            e.getStackTrace();
        }
        return commandMap;
    }

    public void registerCommands(){
        for(String cmd : commands.keySet()){
            registerCommand(commands.get(cmd));
        }
    }

    public void registerCommand(CustomCommand customCommand){
        LBCommand lbCommand = new LBCommand(customCommand, lib);
        CommandMap commandMap = getCommandMap();
        if(commandMap == null) return;
        commandMap.register(plugin.getName(), lbCommand);
    }


    public CommandBase getCommand(@NotNull String name){
        return commands.get(name);
    }

}
