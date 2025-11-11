package dev.loststr1ng.commands;

import dev.loststr1ng.LostLib;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class LBCommand extends BukkitCommand {

    private final CustomCommand command;
    private final LostLib lib;

    public LBCommand(CustomCommand command, LostLib lib) {
        super(command.name(), command.description(), command.usage(), command.aliases());
        this.command = command;
        this.lib = lib;

    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        if(command.playerOnly() && !(sender instanceof Player)){
            lib.getMessages().sendMessage(sender, lib.getMessages().getPlayerOnly(), true);
            return true;
        }
        if(command.permission() != null && !command.permission().isEmpty()){
            if(sender.hasPermission(command.permission())){
                lib.getMessages().sendMessage(sender, lib.getMessages().getNoPermission(), true);
                return true;
            }
            command.execute(sender, label, args);
            return true;
        }
        if(args.length >= 1){
            SubCommandBase subCommand = command.getSubCommand(args[0]);
            if(subCommand == null){
                command.execute(sender, label, args);
            }else {
                subCommand.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
            }
            return true;
        }
        command.execute(sender, label, args);
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args){
        if(args.length == 0){
            // return sub commands and custom completers
            List<String> completer = command.tabComplete(sender, label, args);
            completer.addAll(command.subCommandBaseMap.keySet());
            return completer;
        }
        return command.tabComplete(sender, label, args);
    }


}
