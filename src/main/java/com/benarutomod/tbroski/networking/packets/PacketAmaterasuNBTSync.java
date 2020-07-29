package com.benarutomod.tbroski.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketAmaterasuNBTSync {


    private int entityID;
    private boolean onFire;

    public PacketAmaterasuNBTSync(int entityID, boolean onFire)
    {
        this.entityID = entityID;
        this.onFire = onFire;
    }

    public static void encode(PacketAmaterasuNBTSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.entityID);
        buf.writeBoolean(msg.onFire);
    }

    public static PacketAmaterasuNBTSync decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean data2 = buf.readBoolean();
        return new PacketAmaterasuNBTSync(data, data2);
    }

    public static void handle(PacketAmaterasuNBTSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.entityID);
            if (entity != null) entity.getPersistentData().putBoolean("amaterasufire", msg.onFire);
        });
        ctx.get().setPacketHandled(true);
    }
}
