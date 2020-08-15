package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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

public class BeNMPointsCommand {

    private static final SuggestionProvider<CommandSource> SUGGEST_SET_ADD = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("set");
        suggestions.add("add");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("benmpoints").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("set/add", StringArgumentType.string()).suggests(SUGGEST_SET_ADD).then(Commands.argument("amount", IntegerArgumentType.integer()).executes((context) -> {
            if (StringArgumentType.getString(context, "set/add").equalsIgnoreCase("set")) {
                return setBeNMPoints(context.getSource(), EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "amount"));
            }
            else if (StringArgumentType.getString(context, "set/add").equalsIgnoreCase("add")) {
                return addBeNMPoints(context.getSource(), EntityArgument.getPlayer(context, "target"), IntegerArgumentType.getInteger(context, "amount"));
            }
            return 0;
        })))));
    }

    private static int addBeNMPoints(CommandSource source, PlayerEntity player, int amount) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        playercap.addBeNMPoints(amount);
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".benmpoints.add", amount, player.getDisplayName(), playercap.returnBeNMPoints()), true);
        return 1;
    }

    private static int setBeNMPoints(CommandSource source, PlayerEntity player, int amount) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        playercap.setBeNMPoints(amount);
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".benmpoints.set", player.getDisplayName(), amount), true);
        return 1;
    }
}
