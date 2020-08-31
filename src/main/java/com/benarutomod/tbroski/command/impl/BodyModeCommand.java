package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerBodyModeSync;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import com.benarutomod.tbroski.util.helpers.BodyHelper;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import com.mojang.brigadier.CommandDispatcher;
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

public class BodyModeCommand {

    private static final SuggestionProvider<CommandSource> SUGGEST_BODY_MODE = (source, builder) -> {
        List<String> bodyNames = new ArrayList<>();
        for (BeNMBody body : BeNMRegistry.BODY_MODES.getValues()) {
            bodyNames.add(body.getName());
        }
        return ISuggestionProvider.suggest(bodyNames.stream(), builder);
    };

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("bodymode").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("bodymode", StringArgumentType.string()).suggests(SUGGEST_BODY_MODE).executes((context) -> setBodyMode(context.getSource(), EntityArgument.getPlayer(context, "target"), StringArgumentType.getString(context, "bodymode"))))));
    }

    private static int setBodyMode(CommandSource source, PlayerEntity player, String bodymode) {
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        playercap.setPlayerBodyMode(BodyHelper.getBodyFromString(bodymode));
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketPlayerBodyModeSync(bodymode, player.getEntityId(), true));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".bodymode.set", player.getDisplayName(), bodymode), true);
        return 1;
    }
}
