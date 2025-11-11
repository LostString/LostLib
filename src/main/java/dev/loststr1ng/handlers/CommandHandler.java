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

public class CommandHandler implements CommandExecutor, TabCompleter {

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
        handle();
    }

    public void registerCommand(CustomCommand customCommand){
        LBCommand lbCommand = new LBCommand(customCommand.name(), customCommand.description(), customCommand.usage(), customCommand.aliases());
        CommandMap commandMap = getCommandMap();
        if(commandMap == null) return;
        commandMap.register(plugin.getName(), lbCommand);
    }

    public void handle(){
        CommandMap commandMap = getCommandMap();
        if(commandMap == null) return;
        for(String cmd : commands.keySet()){
            PluginCommand pluginCommand = plugin.getServer().getPluginCommand(cmd);
            if(pluginCommand == null) return;
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }
    }

    public CustomCommand getCommand(@NotNull String name){
        return commands.get(name);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command command, @NotNull String s, String[] args) {
        String commandName = command.getName();
        if(commands.containsKey(commandName)){
            CustomCommand customCommand = getCommand(commandName);
            Messages messages = lib.getMessages();
            if(messages == null) return false;
            if(customCommand == null) return false;
            if(customCommand.playerOnly() && !(sender instanceof Player)){
                messages.sendMessage(sender, messages.getPlayerOnly(), true);
            }
            if(customCommand.permission() != null){
                if(sender.hasPermission(customCommand.permission())){
                    customCommand.execute(sender, command, s, args);
                    return true;
                }else {
                    messages.sendMessage(sender, messages.getNoPermission(), true);
                }
            }else {
                customCommand.execute(sender, command, s, args);
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        String cmdName = command.getName();
        if(commands.containsKey(cmdName)){
            CustomCommand customCommand = getCommand(cmdName);
            if(customCommand == null) return List.of();
            return customCommand.tabComplete(sender, command, s, args);
        }
        return List.of();
    }
}
