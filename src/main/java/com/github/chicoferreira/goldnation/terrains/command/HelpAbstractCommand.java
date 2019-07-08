package com.github.chicoferreira.goldnation.terrains.command;

import com.github.chicoferreira.goldnation.terrains.command.context.CommandContexts;
import com.github.chicoferreira.goldnation.terrains.plugin.TerrainsPlugin;
import com.github.chicoferreira.goldnation.terrains.user.User;

public class HelpAbstractCommand extends AbstractCommand {

    private TerrainsPlugin plugin;

    public HelpAbstractCommand(TerrainsPlugin plugin, String name, String description) {
        super(name, description);
        this.plugin = plugin;
    }

    @Override
    public boolean execute(User user, CommandContexts commandContexts) {
        for (String s : plugin.getConstants().commandHelp) {
            user.sendMessage(s.replace("<commandName>", this.getName()));
        }
        return true;
    }
}
