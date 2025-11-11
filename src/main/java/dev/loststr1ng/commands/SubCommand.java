package dev.loststr1ng.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface SubCommand {

    public String parent();
    public String name();
    public void execute(CommandSender sender, Command command, String label, String[] args);

}
