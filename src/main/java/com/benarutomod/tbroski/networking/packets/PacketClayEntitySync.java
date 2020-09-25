package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.entity.ClayEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class PacketClayEntitySync {

    private String entityToRepresentId;
    private int entityId;
    private int explosionLevel;

    public PacketClayEntitySync(int entityId, String entityToRepresentRegistryName, int explosiveLevel)
    {
        this.entityId = entityId;
        this.entityToRepresentId = entityToRepresentRegistryName;
        this.explosionLevel = explosiveLevel;
    }

    public static void encode(PacketClayEntitySync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.entityId);
        buf.writeString(msg.entityToRepresentId);
        buf.writeInt(msg.explosionLevel);
    }

    public static PacketClayEntitySync decode(PacketBuffer buf)
    {
        return new PacketClayEntitySync(buf.readInt(), buf.readString(), buf.readInt());
    }

    public static void handle(PacketClayEntitySync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.entityId);
            if (entity instanceof ClayEntity && ((ClayEntity) entity).getEntityToRepresent() == null) {
                ((ClayEntity) entity).setExplosionLevel(msg.explosionLevel);
                Entity entityToRepresent = null;
                for (EntityType<?> type : ForgeRegistries.ENTITIES.getValues()) {
                    if (type.getRegistryName().toString().equalsIgnoreCase(msg.entityToRepresentId)) {
                        entityToRepresent = type.create(Minecraft.getInstance().world);
                        break;
                    }
                }
                if (entityToRepresent != null)
                    ((ClayEntity) entity).setEntityToRepresent(entityToRepresent);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
