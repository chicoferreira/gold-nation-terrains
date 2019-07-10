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

    public final String commandErrorOccured = "§cOcorreu um erro ao tentar fazer essa operação. Por favor, tente novamente.";
    public final String commandCouldntModifyMoney = "§cOcorreu um erro ao tentar modificar o seu dinheiro. Por favor, tente novamente.";

    public final int minTerrainSize = 10;
    public final int maxTerrainSize = 50;
    public final double terrainPricePerBlock = 50.D;

    public final String commandBuySizeLowerThanMin = "§cO tamanho do terreno tem que exceder <min>.".replace("<min>", Integer.toString(minTerrainSize));
    public final String commandBuySizeHigherThanMax = "§cO tamanho do terreno não pode exceder <max>.".replace("<max>", Integer.toString(minTerrainSize));
    public final String commandBuyNotEnoughMoney = "§cVocê precisa de <price> para comprar um terreno de <size>x<size>.";
    public final String commandBuyNearbyTerrains = "§cExistem terrenos por perto.";
    public final String commandBuySuccessful = "§aVocê comprou com sucesso um terreno §f<size>x<size> §apor §f<price> §acoins.";
}
