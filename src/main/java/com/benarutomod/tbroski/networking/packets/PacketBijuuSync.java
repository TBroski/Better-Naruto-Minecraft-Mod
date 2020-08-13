package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBijuuSync {

    private int entityID;
    private String bijuu;

    public PacketBijuuSync(int entityID, String bijuu)
    {
        this.entityID = entityID;
        this.bijuu = bijuu;
    }

    public static void encode(PacketBijuuSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.entityID);
        buf.writeString(msg.bijuu);
    }

    public static PacketBijuuSync decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        String data2 = buf.readString();
        return new PacketBijuuSync(data, data2);
    }

    public static void handle(PacketBijuuSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.entityID);
            if (entity instanceof PlayerEntity) {
                ClientPlayerEntity player = (ClientPlayerEntity) entity;
                LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

                playercap.setPlayerBijuu(msg.bijuu);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
