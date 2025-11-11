package dev.loststr1ng.commands;


import org.bukkit.command.CommandSender;

import java.util.List;

public interface CommandBase {

    /**
     * Get command name
     * @return command name
     */
    public String name();

    /**
     * Get command description
     * @return description
     */
    public String description();

    /**
     * Get command usage
     * @return usage message
     */
    public String usage();

    /**
     * Get command aliases
     * @return command aliases
     */
    public List<String> aliases();

    /**
     * Get the required permission to execute this command
     * @return command permission
     */
    public String permission();

    /**
     * Can only player execute this command
     * @return if only player can execute
     */
    public boolean playerOnly();

    /**
     * The execute method of the command
     * @param sender who executed te command
     * @param label command name
     * @param args args in the command
     */
    public void execute(CommandSender sender, String label, String[] args);

    /**
     * The tab completer method of the command
     * @param sender who is executing te command
     * @param label command name
     * @param args args
     * @return The tab complete list
     */
    public List<String> tabComplete(CommandSender sender, String label, String[] args);
}
