package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketNature;
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

public class NatureCommand {

    private static final SuggestionProvider<CommandSource> SUGGEST_SET_ALL = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("set");
        suggestions.add("all");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_NATURE = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("fire");
        suggestions.add("wind");
        suggestions.add("lightning");
        suggestions.add("water");
        suggestions.add("earth");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("nature").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("set/all", StringArgumentType.string()).suggests(SUGGEST_SET_ALL).executes((context) -> {
            if (StringArgumentType.getString(context, "set/all").equalsIgnoreCase("all")) {
                return allNature(context.getSource(), EntityArgument.getPlayer(context, "target"));
            }
            return 0;
        }).then(Commands.argument("nature", StringArgumentType.string()).suggests(SUGGEST_NATURE).then(Commands.argument("has", BoolArgumentType.bool()).executes((context) -> {
            if (StringArgumentType.getString(context, "set/all").equalsIgnoreCase("set")) {
                return setNature(context.getSource(), EntityArgument.getPlayer(context, "target"), BoolArgumentType.getBool(context, "has"), StringArgumentType.getString(context, "nature"));
            }
            return 0;
        }))))));
    }

    private static int allNature(CommandSource source, PlayerEntity player) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));

        playercap.setFireNature(true);
        playercap.setWaterNature(true);
        playercap.setWindNature(true);
        playercap.setEarthNature(true);
        playercap.setLightningNature(true);
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(1, true, true));
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(2, true, true));
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(3, true, true));
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(4, true, true));
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(5, true, true));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".nature.all", player.getDisplayName()), true);
        return 1;
    }

    private static int setNature(CommandSource source, PlayerEntity player, boolean has, String nature) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        if (nature.equalsIgnoreCase("fire")) {
            playercap.setFireNature(has);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(1, has, true));
            source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".nature.set", nature, player.getDisplayName(), has), true);
            return 1;
        }
        else if (nature.equalsIgnoreCase("water")) {
            playercap.setWaterNature(has);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(2, has, true));
            source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".nature.set", nature, player.getDisplayName(), has), true);
            return 1;
        }
        else if (nature.equalsIgnoreCase("earth")) {
            playercap.setEarthNature(has);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(3, has, true));
            source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".nature.set", nature, player.getDisplayName(), has), true);
            return 1;
        }
        else if (nature.equalsIgnoreCase("wind")) {
            playercap.setWindNature(has);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(4, has, true));
            source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".nature.set", nature, player.getDisplayName(), has), true);
            return 1;
        }
        else if (nature.equalsIgnoreCase("lightning")) {
            playercap.setLightningNature(has);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketNature(5, has, true));
            source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".nature.set", nature, player.getDisplayName(), has), true);
            return 1;
        }
        return 0;
    }
}
