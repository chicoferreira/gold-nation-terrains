package com.github.chicoferreira.goldnation.terrains;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public final String playerJoinTimeout = "§cNão foi possível carregar as suas informações.§r\n§cPor favor, tente novamente.";
    public final String commandNotAPlayer = "§cApenas jogadores podem executar esse comando.";

    public final String commandUsage = "§cPor favor, use: <usage>.";
    public final List<String> commandHelp = Arrays.asList("", "    §6§lAJUDA SOBRE <commandName>", "", "<commands>", "");
    public final String commandHelpSyntax = "  §e- §f/<command> §6- §e<description>";
    public final String helpCommandName = "ajuda";

    public final int minTerrainSize = 10;
    public final int maxTerrainSize = 50;
}
