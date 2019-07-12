package com.github.chicoferreira.goldnation.terrains;

import java.util.Arrays;
import java.util.List;

public class Constants {

    public final String playerJoinTimeout = "§cNão foi possível carregar as suas informações.§r\n§cPor favor, tente novamente.";
    public final String commandNotAPlayer = "§cApenas jogadores podem executar esse comando.";
    public final String commandPlayerOffline = "§cEsse jogador não está online.";

    public final String commandUsage = "§cPor favor, use: <usage>.";
    public final List<String> commandHelp = Arrays.asList("", "    §6§lAJUDA SOBRE <commandName>", "", "<commands>", "");
    public final String commandHelpSyntax = "  §e- §f/<command> §6- §e<description>";
    public final String helpCommandName = "ajuda";

    public final String commandErrorOccured = "§cOcorreu um erro ao tentar fazer essa operação. Por favor, tente novamente.";
    public final String commandCouldntModifyMoney = "§cOcorreu um erro ao tentar modificar o seu dinheiro. Por favor, tente novamente.";

    public final int minTerrainSize = 10;
    public final int maxTerrainSize = 50;
    public final double terrainPricePerBlock = 50.D;

    public final String commandSizeLowerThanMin = "§cO tamanho do terreno tem que exceder <min>.".replace("<min>", Integer.toString(minTerrainSize));
    public final String commandSizeHigherThanMax = "§cO tamanho do terreno não pode exceder <max>.".replace("<max>", Integer.toString(maxTerrainSize));

    public final String commandBuyNotEnoughMoney = "§cVocê precisa de <price> para comprar um terreno de <size>x<size>.";
    public final String commandBuyNearbyTerrains = "§cExistem terrenos por perto.";
    public final String commandBuySuccessful = "§aVocê comprou com sucesso um terreno §f<size>x<size> §apor §f<price> §acoins.";

    public final String activated = "Ativado";
    public final String disactivated = "Desativado";

    public final String commandNotInTerrain = "§cVocê não está em nenhum terreno.";

    public final List<String> commandInfo = Arrays.asList(
            "",
            "§eInformações do terreno de §6<owner>",
            "",
            "§eTamanho: §f<size>",
            "§eArea: §7x: §f<areaStartX> §7z: §f<areaStartZ> até §7x: §f<areaEndX> §7z: §f<areaEndZ>",
            "§eSpawn: §7x: §f<spawnX> §7y: §f<spawnY> §7z: §f<spawnZ> §7yaw: §f<spawnYaw> §7pitch: §f<spawnPitch>",
            "§ePvp: §f<translatedPvpState>",
            "§eAmigos: §f<friends>",
            ""
    );
    public final String emptyFriendsList = "Nenhum";

    public final long commandAbandonVerificationTime = 3000;
    public final String commandNotOwner = "§cVocê não é o dono deste terreno.";
    public final String commandAbandonSuccess = "§eAbandonado terreno com sucesso. Você pode comprá-lo novamente se mudar de ideias.";
    public final String commandAbandonVerification = "§eDigite o comando novamente para abandonar o terreno.";

    public final String commandListEmptyTerrains = "§cVocê não tem terrenos.";
    public final List<String> commandList = Arrays.asList(
            "",
            "     §6Lista de terrenos",
            "",
            "<terrains>",
            ""
    );
    public final String commandListFormat = "  §e- §f<spawnX> <spawnY> <spawnZ>";
    public final String commandListFormatHold = "§eClique aqui para teleportar para esse terreno.";
    public final String commandListFormatClickCommand = "/terreno ir <index>";

    public final String commandGoNotFoundIndex = "§cVocê não tem terrenos de índice <index>.";
    public final String commandGoSuccess = "§aTeleportado com sucesso.";

    public final String commandTogglePvpEnabled = "§eO pvp foi ativado no terreno.";
    public final String commandTogglePvpDisabled = "§eO pvp foi desativado no terreno.";

    public final String commandFriendAdded = "§eAdicionado <friend> como amigo no terreno.";
    public final String commandFriendAlreadyAdded = "§eVocê já tem <friend> como amigo neste terreno.";
    public final String commandFriendRemoved = "§eRemovido <friend> como amigo do terreno.";
    public final String commandFriendNotFound = "§c<friend> não está adicionado como amigo neste terreno.";
    public final String commandFriendSelfAdd = "§cVocê não pode adicionar você mesmo como amigo.";

    public final String commandSetSpawnSuccess = "§eSpawn do terreno alterado para a sua localização.";

    public final String commandExpandNotEnoughMoney = "§cVocê precisa de <price> para expandir o seu terreno <size> blocos.";
    public final String commandExpandNearbyTerrains = "§cExistem terrenos pelos lugares que você quer expandir.";
    public final String commandExpandSuccessful = "§eExpandido o terreno <sizeToExpand> blocos por §f<price> §ecoins. Agora você tem um terreno de §f<newSize>x<newSize>§e.";
}
