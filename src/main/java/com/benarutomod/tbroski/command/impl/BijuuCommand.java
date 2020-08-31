package com.benarutomod.tbroski.command.impl;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBijuuSync;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.impl.SummonCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class BijuuCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("bijuu").requires((commandSource) -> commandSource.hasPermissionLevel(3)).then(Commands.argument("target", EntityArgument.player()).then(Commands.argument("bijuu", EntityArgument.entity()).executes((context) -> setBijuu(context.getSource(), EntityArgument.getPlayer(context, "target"), EntityArgument.getEntity(context, "bijuu"))))));
    }

    private static int setBijuu(CommandSource source, ServerPlayerEntity player, Entity bijuu) {
        if (!(bijuu instanceof AbstractBijuuEntity)) {
            source.sendFeedback(new StringTextComponent("Non-valid bijuu."), true);
            return 0;
        }
        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
        playercap.setPlayerBijuu(bijuu.getType().getRegistryName().toString());
        NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBijuuSync(player.getEntityId(), bijuu.getType().getRegistryName().toString()));
        source.sendFeedback(new TranslationTextComponent("commands." + Main.MODID + ".bijuu.set", player.getDisplayName(), bijuu.getType().getRegistryName().toString()), true);
        return 1;
    }
}
