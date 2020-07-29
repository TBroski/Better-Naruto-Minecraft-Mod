package com.benarutomod.tbroski.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEntityClaySync {

    private int playerID;
    private int entityID;

    public PacketEntityClaySync(int playerID, int entityID)
    {
        this.playerID = playerID;
        this.entityID = entityID;
    }

    public static void encode(PacketEntityClaySync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.playerID);
        buf.writeInt(msg.entityID);
    }

    public static PacketEntityClaySync decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        int data2 = buf.readInt();
        return new PacketEntityClaySync(data, data2);
    }

    public static void handle(PacketEntityClaySync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.entityID);
            if (entity != null) entity.getPersistentData().putInt("affiliatedclayplayer", msg.playerID);
        });
        ctx.get().setPacketHandled(true);
    }
}
