package dev.loststr1ng.commands;

import org.bukkit.command.CommandSender;

public interface SubCommandBase {
    /**
     * Sub Command name
     * @return name
     */
    public String name();

    /**
     * Execute method
     * @param sender sender
     * @param label label
     * @param args args
     */
    public void execute(CommandSender sender, String label, String[] args);
}
