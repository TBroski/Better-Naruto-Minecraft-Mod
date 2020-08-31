package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractFireJutsuEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBlueFireSync {

    private int entityId;
    private boolean blueFire;

    public PacketBlueFireSync(int entityId, boolean blueFire) {
        this.entityId = entityId;
        this.blueFire = blueFire;
    }

    public static void encode(PacketBlueFireSync msg, PacketBuffer buf) {
        buf.writeInt(msg.entityId);
        buf.writeBoolean(msg.blueFire);
    }

    public static PacketBlueFireSync decode(PacketBuffer buf) {
        return new PacketBlueFireSync(buf.readInt(), buf.readBoolean());
    }

    public static void handle(PacketBlueFireSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.entityId);
             if (entity instanceof AbstractFireJutsuEntity) {
                ((AbstractFireJutsuEntity) entity).setBlueFire(msg.blueFire);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
