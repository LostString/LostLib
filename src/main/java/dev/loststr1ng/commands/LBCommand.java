package dev.loststr1ng.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LBCommand extends BukkitCommand {

    public LBCommand(String name, String description, String usage, List<String> aliases) {
        super(name, description, usage, aliases);
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, String s, String[] strings) {
        return true;
    }
}
