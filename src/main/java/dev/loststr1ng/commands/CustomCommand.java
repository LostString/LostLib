package dev.loststr1ng.commands;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CustomCommand implements CommandBase {

    public final Map<String,SubCommandBase> subCommandBaseMap;

    public CustomCommand() {
        this.subCommandBaseMap = new HashMap<>();
    }

    public void addSubCommand(SubCommandBase subCommandBase){
        subCommandBaseMap.put(subCommandBase.name(), subCommandBase);
    }

    public SubCommandBase getSubCommand(String name){
        return subCommandBaseMap.get(name);
    }
}
