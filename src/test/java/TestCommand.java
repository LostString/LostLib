import dev.loststr1ng.commands.CustomCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TestCommand extends CustomCommand {

    @Override
    public String name() {
        return "";
    }

    @Override
    public String description() {
        return "";
    }

    @Override
    public String usage() {
        return "";
    }

    @Override
    public List<String> aliases() {
        return List.of();
    }

    @Override
    public String permission() {
        return "";
    }

    @Override
    public boolean playerOnly() {
        return false;
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {

    }

    @Override
    public List<String> tabComplete(CommandSender sender, String label, String[] args) {
        return List.of();
    }
}
