package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.BodyHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerBodyModeSync {

    private String bodyMode;
    private int playerID;
    private boolean toClient;

    public PacketPlayerBodyModeSync(String bodyMode, int playerID,  boolean toClient)
    {
        this.bodyMode = bodyMode;
        this.playerID = playerID;
        this.toClient = toClient;
    }

    public static void encode(PacketPlayerBodyModeSync msg, PacketBuffer buf)
    {
        buf.writeString(msg.bodyMode);
        buf.writeInt(msg.playerID);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketPlayerBodyModeSync decode(PacketBuffer buf)
    {
        String bodyMode = buf.readString();
        int playerID = buf.readInt();
        boolean toClient = buf.readBoolean();
        return new PacketPlayerBodyModeSync(bodyMode, playerID, toClient);
    }

    public static void handle(PacketPlayerBodyModeSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                Entity player = Minecraft.getInstance().world.getEntityByID(msg.playerID);
                if (player instanceof ClientPlayerEntity) {
                    LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setPlayerBodyMode(BodyHelper.getBodyFromString(msg.bodyMode));
                }
            } else {
                LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                playercap.setPlayerBodyMode(BodyHelper.getBodyFromString(msg.bodyMode));
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
