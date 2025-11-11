package dev.loststr1ng.handlers;

import dev.loststr1ng.LostLib;
import dev.loststr1ng.commands.CustomCommand;
import dev.loststr1ng.commands.LBCommand;
import dev.loststr1ng.commands.SubCommand;
import dev.loststr1ng.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler {

    private final Plugin plugin;
    private final LostLib lib;
    private final Map<String, CustomCommand> commands;
    private final List<SubCommand> subcommands;

    public CommandHandler(LostLib lib){
        this.plugin = lib.getPlugin();
        this.lib = lib;
        this.commands = new HashMap<>();
        this.subcommands = new ArrayList<>();
    }

    public boolean addCommand(CustomCommand customCommand){
        if(commands.containsKey(customCommand.name())){
            return false;
        }
        commands.put(customCommand.name(), customCommand);
        return true;
    }

    public boolean addSubCommand(SubCommand subCommand){
        if(commands.containsKey(subCommand.parent())){
            if(!subcommands.contains(subCommand)){
                subcommands.add(subCommand);
            }
        }
        return false;
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
        LBCommand lbCommand = new LBCommand(customCommand);
        CommandMap commandMap = getCommandMap();
        if(commandMap == null) return;
        commandMap.register(plugin.getName(), lbCommand);
    }


    public CustomCommand getCommand(@NotNull String name){
        return commands.get(name);
    }

}
