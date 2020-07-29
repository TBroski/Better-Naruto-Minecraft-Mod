package com.benarutomod.tbroski.networking.packets.settings;

import com.benarutomod.tbroski.client.handler.ClientHandler;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBackSlotSync {

    private int entityId;
    private boolean wearing;
    private String itemName;


    public PacketBackSlotSync(int entityId, boolean wearing, String itemName)
    {
        this.entityId = entityId;
        this.wearing = wearing;
        this.itemName = itemName;
    }

    public static void encode(PacketBackSlotSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.entityId);
        buf.writeBoolean(msg.wearing);
        buf.writeString(msg.itemName);
    }

    public static PacketBackSlotSync decode(PacketBuffer buf)
    {
        int id = buf.readInt();
        boolean wearing = buf.readBoolean();
        String itemName = buf.readString();
        return new PacketBackSlotSync(id, wearing, itemName);
    }

    public static void handle(PacketBackSlotSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ClientHandler.setPlayerBackpack(msg.entityId, msg.wearing, msg.itemName);
        });
        ctx.get().setPacketHandled(true);
    }
}
