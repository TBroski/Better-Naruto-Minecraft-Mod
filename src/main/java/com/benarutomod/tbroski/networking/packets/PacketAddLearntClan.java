package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketAddLearntClan {

    private String learntClan;

    public PacketAddLearntClan(String learntClan) {
        this.learntClan = learntClan;
    }

    public static void encode(PacketAddLearntClan msg, PacketBuffer buf) {
        buf.writeString(msg.learntClan);
    }

    public static PacketAddLearntClan decode(PacketBuffer buf) {
        return new PacketAddLearntClan(buf.readString());
    }

    public static void handle(PacketAddLearntClan msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            playercap.addLearntClan(ClanHelper.getClanFromString(msg.learntClan));
        });
        ctx.get().setPacketHandled(true);
    }
}
