package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMDojutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
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

public class DojutsuCommand {

    private static final SuggestionProvider<CommandSource> SUGGEST_DOJUTSU = (source, builder) -> {
        List<String> dojutsuNames = new ArrayList<>();
        for (BeNMDojutsu dojutsu : BeNMRegistry.DOJUTSUS.getValues()) {
            dojutsuNames.add(dojutsu.getString());
        }
        return ISuggestionProvider.suggest(dojutsuNames.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_SET = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("set");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    private static final SuggestionProvider<CommandSource> SUGGEST_LEFT_RIGHT = (source, builder) -> {
        List<String> suggestions = new ArrayList<>();
        suggestions.add("left");
        suggestions.add("right");
        return ISuggestionProvider.suggest(suggestions.stream(), builder);
    };

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("dojutsu").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("set", StringArgumentType.string()).suggests(SUGGEST_SET).then(Commands.argument("left/right", StringArgumentType.string()).suggests(SUGGEST_LEFT_RIGHT).then(Commands.argument("dojutsu", StringArgumentType.string()).suggests(SUGGEST_DOJUTSU).executes((context) -> {
            if (StringArgumentType.getString(context, "set").equalsIgnoreCase("set")) {
                boolean left = false;
                if (StringArgumentType.getString(context, "left/right").equalsIgnoreCase("left")) {
                    left = true;
                }
                return setDojutsu(context.getSource(), EntityArgument.getPlayer(context, "target"), left, StringArgumentType.getString(context, "dojutsu"));
            }
            return 0;
        }))))));
    }

    private static int setDojutsu(CommandSource source, PlayerEntity player, boolean left, String dojutsu) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        if (left) {
            playercap.setPlayerLeftDojutsu(DojutsuHelper.getDojutsuFromString(dojutsu));
        }
        else {
            playercap.setPlayerRightDojutsu(DojutsuHelper.getDojutsuFromString(dojutsu));
        }
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketPlayerDojutsuSync(dojutsu, left, true));
        String d = "right";
        if (left) {
            d = "left";
        }
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".dojutsu.set", player.getDisplayName(), d, dojutsu), true);
        return 1;
    }
}
