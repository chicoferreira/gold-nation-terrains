package com.github.chicoferreira.goldnation.terrains;

import com.github.chicoferreira.goldnation.terrains.config.Configuration;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class Constants {

    public final String allowedWorld;
    public final String playerJoinTimeout;
    public final long joinTimeoutTime;

    public final String commandNotAPlayer;
    public final String commandPlayerOffline;

    public final String commandUsage;
    public final List<String> commandHelp;
    public final String commandHelpSyntax;
    public final String helpCommandName;

    public final String commandPlayerNoPermissions;

    public final String commandErrorOccured;
    public final String commandCouldntModifyMoney;

    public final int minTerrainSize;
    public final int maxTerrainSize;
    public final double terrainPricePerBlock;

    public final String commandSizeLowerThanMin;
    public final String commandSizeHigherThanMax;

    public final String commandBuyLimit;
    public final String commandBuyNotInWorld;
    public final String commandBuyNotEnoughMoney;
    public final String commandBuyNearbyTerrains;
    public final String commandBuySuccessful;

    public final String activated;
    public final String disactivated;

    public final String commandNotInTerrain;

    public final List<String> commandInfo;
    public final String emptyString;

    public final long commandAbandonVerificationTime;
    public final String commandNotOwner;
    public final String commandAbandonSuccess;
    public final String commandAbandonVerification;

    public final String commandListEmptyTerrains;
    public final List<String> commandList;
    public final String commandListFormat;
    public final String commandListFormatHold;
    public final String commandListFormatClickCommand;

    public final String commandGoNotFoundIndex;
    public final String commandGoSuccess;

    public final String commandTogglePvpEnabled;
    public final String commandTogglePvpDisabled;

    public final String commandFriendAdded;
    public final String commandFriendAlreadyAdded;
    public final String commandFriendRemoved;
    public final String commandFriendNotFound;
    public final String commandFriendSelfAdd;

    public final String commandSetSpawnSuccess;

    public final String commandExpandNotEnoughMoney;
    public final String commandExpandNearbyTerrains;
    public final String commandExpandSuccessful;

    public final String terrainCantBreakBlock;
    public final String terrainCantPlaceBlock;

    public final String commandSellRemovedFromSale;
    public final String commandSellNotInSale;
    public final String commandSellPutUp;

    public final String commandAcquireOwned;
    public final String commandAcquireNotInSale;
    public final String commandAcquireNotEnoughMoney;
    public final String commandAcquireSuccess;
    public final String commandAcquireSuccessBuyer;

    public Constants(Configuration configuration) {
        this.allowedWorld = configuration.getString("settings.allowed world");
        this.minTerrainSize = configuration.getInt("settings.minumum terrain size");
        this.maxTerrainSize = configuration.getInt("settings.maximum terrain size");
        this.terrainPricePerBlock = configuration.getDouble("settings.price per block");
        this.commandNotAPlayer = translateColors(configuration.getString("commands.not a player"));
        this.commandPlayerOffline = translateColors(configuration.getString("commands.player offline"));
        this.commandPlayerNoPermissions = translateColors(configuration.getString("commands.no permission"));
        this.commandUsage = translateColors(configuration.getString("commands.engine.usage"));
        this.commandHelp = translateColors(configuration.getStringList("commands.engine.help"));
        this.commandHelpSyntax = translateColors(configuration.getString("commands.engine.help syntax"));
        this.helpCommandName = translateColors(configuration.getString("commands.engine.help command name"));
        this.commandErrorOccured = translateColors(configuration.getString("commands.engine.error occured"));
        this.commandCouldntModifyMoney = translateColors(configuration.getString("commands.engine.couldnt modify money"));
        this.commandSizeLowerThanMin = translateColors(configuration.getString("commands.multiple.size lower than minimum"))
                .replace("<min>", Integer.toString(minTerrainSize));
        this.commandSizeHigherThanMax = translateColors(configuration.getString("commands.multiple.size higher than maximum"))
                .replace("<max>", Integer.toString(maxTerrainSize));
        this.activated = configuration.getString("commands.multiple.activated string");
        this.disactivated = configuration.getString("commands.multiple.disactivated string");
        this.emptyString = configuration.getString("commands.multiple.empty string");
        this.commandNotInTerrain = translateColors(configuration.getString("commands.multiple.not in any terrain"));
        this.commandNotOwner = translateColors(configuration.getString("commands.multiple.not owner"));
        this.commandBuyLimit = translateColors(configuration.getString("commands.buy.limit"));
        this.commandBuyNotInWorld = translateColors(configuration.getString("commands.buy.not in world"));
        this.commandBuyNotEnoughMoney = translateColors(configuration.getString("commands.buy.not enough money"));
        this.commandBuyNearbyTerrains = translateColors(configuration.getString("commands.buy.nearby terrains"));
        this.commandBuySuccessful = translateColors(configuration.getString("commands.buy.success"));
        this.commandInfo = translateColors(configuration.getStringList("commands.info"));
        this.commandAbandonVerification = translateColors(configuration.getString("commands.abandon.verification"));
        this.commandAbandonVerificationTime = configuration.getLong("commands.abandon.verification duration");
        this.commandAbandonSuccess = translateColors(configuration.getString("commands.abandon.success"));
        this.commandListEmptyTerrains = translateColors(configuration.getString("commands.list.empty"));
        this.commandList = translateColors(configuration.getStringList("commands.list.success"));
        this.commandListFormat = translateColors(configuration.getString("commands.list.format"));
        this.commandListFormatHold = translateColors(configuration.getString("commands.list.format hold"));
        this.commandListFormatClickCommand = translateColors(configuration.getString("commands.list.format click sugestion"));
        this.commandGoNotFoundIndex = translateColors(configuration.getString("commands.go.index not found"));
        this.commandGoSuccess = translateColors(configuration.getString("commands.go.success"));
        this.commandTogglePvpEnabled = translateColors(configuration.getString("commands.pvp.enabled"));
        this.commandTogglePvpDisabled = translateColors(configuration.getString("commands.pvp.disabled"));
        this.commandFriendAdded = translateColors(configuration.getString("commands.friend.added"));
        this.commandFriendAlreadyAdded = translateColors(configuration.getString("commands.friend.already added"));
        this.commandFriendRemoved = translateColors(configuration.getString("commands.friend.removed"));
        this.commandFriendNotFound = translateColors(configuration.getString("commands.friend.not found"));
        this.commandFriendSelfAdd = translateColors(configuration.getString("commands.friend.self add"));
        this.commandSetSpawnSuccess = translateColors(configuration.getString("commands.set spawn.success"));
        this.commandExpandNotEnoughMoney = translateColors(configuration.getString("commands.expand.not enough money"));
        this.commandExpandNearbyTerrains = translateColors(configuration.getString("commands.expand.nearby terrains"));
        this.commandExpandSuccessful = translateColors(configuration.getString("commands.expand.success"));
        this.playerJoinTimeout = translateColors(configuration.getString("listener.join.could not load in time"));
        this.joinTimeoutTime = configuration.getLong("listener.join.load timeout");
        this.terrainCantPlaceBlock = translateColors(configuration.getString("listener.cannot place block"));
        this.terrainCantBreakBlock = translateColors(configuration.getString("listener.cannot break block"));

        this.commandSellRemovedFromSale = translateColors(configuration.getString("commamnds.sell.removed from sale"));
        this.commandSellNotInSale = translateColors(configuration.getString("commands.sell.not in sale"));
        this.commandSellPutUp = translateColors(configuration.getString("commands.sell.put up"));
        this.commandAcquireNotInSale = translateColors(configuration.getString("commands.acquire.not in sale"));
        this.commandAcquireOwned = translateColors(configuration.getString("commands.acquire.owned"));
        this.commandAcquireNotEnoughMoney = translateColors(configuration.getString("commands.acquire.not enough money"));
        this.commandAcquireSuccess = translateColors(configuration.getString("commands.acquire.success"));
        this.commandAcquireSuccessBuyer = translateColors(configuration.getString("commands.acquire.success owner"));
    }

    private String translateColors(String message) {
        if (message != null) {
            return ChatColor.translateAlternateColorCodes('&', message);
        }
        return null;
    }

    private List<String> translateColors(List<String> messages) {
        return messages.stream()
                .map(this::translateColors)
                .collect(Collectors.toList());
    }
}
