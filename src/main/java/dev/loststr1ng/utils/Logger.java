package dev.loststr1ng.utils;

import dev.loststr1ng.LostLib;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Logger {

    private final LostLib lostLib;

    public Logger(LostLib lostLib){
        this.lostLib = lostLib;
    }

    public void info(String message){
        sendLog("INFO", message, "gray");
    }

    public void warn(String message){
        sendLog("WARN", message, "gold");
    }

    public void error(String message){
        sendLog("ERROR", message, "red");
    }

    public void debug(String message){ sendLog("DEBUG", message, "green");}

    private void sendLog(String level, String message, String color){
        lostLib.getPlugin().getServer()
                .getConsoleSender()
                .sendMessage(MiniMessage.miniMessage().deserialize("<"+color+">[" +lostLib.getPlugin().getName().toUpperCase()+" - "+ level+ "]: " + message));
    }

}
