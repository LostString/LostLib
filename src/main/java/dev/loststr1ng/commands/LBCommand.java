package dev.loststr1ng.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LBCommand extends BukkitCommand {

    private CustomCommand command;

    public LBCommand(CustomCommand command) {
        super(command.name(), command.description(), command.usage(), command.aliases());
        this.command = command;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, String[] args) {
        command.execute(sender, label, args);
        return true;
    }

    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String label, String[] args){
        command.tabComplete(sender, label, args);
        return List.of();
    }
}
