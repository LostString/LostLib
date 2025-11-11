package dev.loststr1ng.utils;

import dev.loststr1ng.LostLib;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    private final LostLib lostLib;

    public Messages(LostLib lostLib){
        this.lostLib = lostLib;
    }

    private String playerOnly = "&cOnly player can execute this command";
    private String noPermission = "&cYou don't have permission to execute this command";
    private String prefix = "";
    private boolean miniMessage = false;

    public boolean isMiniMessage() {
        return miniMessage;
    }

    public void setMiniMessage(boolean minimessage) {
        this.miniMessage = minimessage;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPlayerOnly() {
        return playerOnly;
    }

    public void setPlayerOnly(String playerOnly) {
        this.playerOnly = playerOnly;
    }

    public String getNoPermission() {
        return noPermission;
    }

    public void setNoPermission(String noPermission) {
        this.noPermission = noPermission;
    }

    /**
     * Get colored Text by legacy or minimessage
     * @param message message
     * @return colored message
     */
    public Component getColored(String message){
        if(isMiniMessage()){
            return MiniMessage.miniMessage().deserialize(message)
                    .decoration(TextDecoration.ITALIC, false);
        }else {
            return LegacyComponentSerializer.legacyAmpersand().deserialize(message);
        }
    }

    /**
     * Get colored list
     * @param list list
     * @return colored list
     */
    public List<Component> getColoredList(List<String> list){
        List<Component> colored = new ArrayList<>();
        for(String line: list){
            colored.add(getColored(line));
        }
        return colored;
    }

    /**
     * Send a message to a sender
     * @param sender sender
     * @param message message
     * @param prefixed is prefixed
     */
    public void sendMessage(CommandSender sender, String message, boolean prefixed){
        Component colored = getColored(message);
        if(prefixed){
            colored = getColored(prefix + message);
            sender.sendMessage(colored);
            return;
        }else {
            sender.sendMessage(colored);
        }
    }

    /**
     * Send a list of message to a player
     * @param sender sender
     * @param message messages
     */
    public void sendMessageList(CommandSender sender, List<String> message){
        for(String m: message){
            sendMessage(sender, m, false);
        }
    }


}
