package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketSetJutsuBoolean;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class JutsuCommand {

    private static final SuggestionProvider<CommandSource> SUGGEST_SET_ALL = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("set");
        suggestions.add("all");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_JUTSU = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            suggestions.add(jutsu.getName());
        }
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("jutsu").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("set/all", StringArgumentType.string()).suggests(SUGGEST_SET_ALL).executes((context) -> {
            if (StringArgumentType.getString(context, "set/all").equalsIgnoreCase("all")) {
                return allJutsu(context.getSource(), EntityArgument.getPlayer(context, "target"));
            }
            return 0;
        }).then(Commands.argument("jutsu", StringArgumentType.string()).suggests(SUGGEST_JUTSU).then(Commands.argument("has", BoolArgumentType.bool()).executes((context) -> {
            if (StringArgumentType.getString(context, "set/all").equalsIgnoreCase("set")) {
                return setJutsu(context.getSource(), EntityArgument.getPlayer(context, "target"), BoolArgumentType.getBool(context, "has"), StringArgumentType.getString(context, "jutsu"));
            }
            return 0;
        }))))));
    }


    private static int allJutsu(CommandSource source, PlayerEntity player) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        for (BeNMJutsu j : BeNMRegistry.JUTSUS.getValues()) {
            j.sync(playercap, j,true);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketSetJutsuBoolean(j.getName(), true, true));
        }
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".jutsu.all", player.getDisplayName()), true);
        return 1;
    }

    private static int setJutsu(CommandSource source, PlayerEntity player, boolean has, String jutsuName) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        BeNMJutsu jutsu = null;
        for (BeNMJutsu j : BeNMRegistry.JUTSUS.getValues()) {
            if (j.getName().equalsIgnoreCase(jutsuName)) {
                jutsu = j;
                break;
            }
        }
        if (jutsu == null) return 0;
        jutsu.sync(playercap, jutsu, has);
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketSetJutsuBoolean(jutsuName, has, true));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".jutsu.set", player.getDisplayName(), jutsuName, has), true);
        return 1;
    }
}
