package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import io.netty.handler.codec.sctp.SctpOutboundByteStreamHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketClanSync {

    private String clan;
    private boolean toClient;

    public PacketClanSync(String clan, boolean toClient)
    {
        this.clan = clan;
        this.toClient = toClient;
    }

    public static void encode(PacketClanSync msg, PacketBuffer buf)
    {
        buf.writeString(msg.clan);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketClanSync decode(PacketBuffer buf)
    {
        String clan = buf.readString();
        boolean toClient = buf.readBoolean();
        return new PacketClanSync(clan, toClient);
    }

    public static void handle(PacketClanSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                playercap.setPlayerClan(ClanHelper.getClanFromString(msg.clan));
            }
            else {
                ServerPlayerEntity player = ctx.get().getSender();
                LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                playercap.setPlayerClan(ClanHelper.getClanFromString(msg.clan));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
