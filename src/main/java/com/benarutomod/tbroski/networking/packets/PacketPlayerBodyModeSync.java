package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.BodyHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerBodyModeSync {

    private String bodyMode;
    private boolean toClient;

    public PacketPlayerBodyModeSync(String bodyMode, boolean toClient)
    {
        this.bodyMode = bodyMode;
        this.toClient = toClient;
    }

    public static void encode(PacketPlayerBodyModeSync msg, PacketBuffer buf)
    {
        buf.writeString(msg.bodyMode);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketPlayerBodyModeSync decode(PacketBuffer buf)
    {
        String bodyMode = buf.readString();
        boolean toClient = buf.readBoolean();
        return new PacketPlayerBodyModeSync(bodyMode, toClient);
    }

    public static void handle(PacketPlayerBodyModeSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                playercap.setPlayerBodyMode(BodyHelper.getBodyFromString(msg.bodyMode));
            } else {
                LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                playercap.setPlayerBodyMode(BodyHelper.getBodyFromString(msg.bodyMode));
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
