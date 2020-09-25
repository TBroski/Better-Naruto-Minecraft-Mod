package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketAddLearntClan;
import com.benarutomod.tbroski.networking.packets.PacketClanSync;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class ClanCommand {

    private static final SuggestionProvider<CommandSource> SUGGEST_CLAN = (source, builder) -> {
        List<String> clanNames = new ArrayList<>();
        for (BeNMClan clan : BeNMRegistry.CLANS.getValues()) {
            clanNames.add(clan.getString());
        }
        return ISuggestionProvider.suggest(clanNames.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_LEARN = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("learn");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("clan").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("learn", StringArgumentType.string()).suggests(SUGGEST_LEARN).then(Commands.argument("clan", StringArgumentType.string()).suggests(SUGGEST_CLAN).executes((context) -> {
            if (StringArgumentType.getString(context, "learn").equalsIgnoreCase("learn")) {
                return setClan(context.getSource(), EntityArgument.getPlayer(context, "target"), StringArgumentType.getString(context, "clan"));
            }
            return 0;
        })))));
    }

    private static int setClan(CommandSource source, ServerPlayerEntity target, String clan) {
        IPlayerHandler playercap = target.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        playercap.addLearntClan(ClanHelper.getClanFromString(clan));
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> target), new PacketAddLearntClan(clan));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".clan.learn", clan, target.getDisplayName()), true);
        return 1;
    }
}
