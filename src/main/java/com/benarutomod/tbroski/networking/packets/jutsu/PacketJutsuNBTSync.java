package com.benarutomod.tbroski.networking.packets.jutsu;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketJutsuNBTSync {

    private int playerID;
    private String nbtName;
    private boolean toggled;

    public PacketJutsuNBTSync(int playerID, String nbtName, boolean toggled)
    {
        this.playerID = playerID;
        this.nbtName = nbtName;
        this.toggled = toggled;
    }

    public static void encode(PacketJutsuNBTSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.playerID);
        buf.writeString(msg.nbtName);
        buf.writeBoolean(msg.toggled);
    }

    public static PacketJutsuNBTSync decode(PacketBuffer buf)
    {
        int id = buf.readInt();
        String data = buf.readString();
        boolean toggled = buf.readBoolean();
        return new PacketJutsuNBTSync(id, data, toggled);
    }

    public static void handle(PacketJutsuNBTSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.playerID);
            if (entity != null) entity.getPersistentData().putBoolean(msg.nbtName, msg.toggled);
        });
        ctx.get().setPacketHandled(true);
    }
}
