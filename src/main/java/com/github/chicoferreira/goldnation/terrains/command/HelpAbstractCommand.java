package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class HelpAbstractCommand extends AbstractCommand {

    public HelpAbstractCommand(TerrainsPlugin plugin, String name, String description) {
        super(plugin, name, description);
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        Command help = this.getSubcommand(getPlugin().getConstants().helpCommandName);
        if (help != null) {
            return help.execute(user, commandContexts);
        }
        return true;
    }

}
