package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.entity.EtherealItemEntity;
import com.benarutomod.tbroski.init.EntityInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class PacketSpawnEntity {

    private int entityId;
    private UUID uniqueId;
    private double x;
    private double y;
    private double z;
    private int speedX;
    private int speedY;
    private int speedZ;
    private int pitch;
    private int yaw;
    private EntityType<?> type;
    private int data;

    public PacketSpawnEntity(int p_i50777_1_, UUID p_i50777_2_, double p_i50777_3_, double p_i50777_5_, double p_i50777_7_, float p_i50777_9_, float p_i50777_10_, EntityType<?> p_i50777_11_, int p_i50777_12_, Vec3d p_i50777_13_) {
        this.entityId = p_i50777_1_;
        this.uniqueId = p_i50777_2_;
        this.x = p_i50777_3_;
        this.y = p_i50777_5_;
        this.z = p_i50777_7_;
        this.pitch = MathHelper.floor(p_i50777_9_ * 256.0F / 360.0F);
        this.yaw = MathHelper.floor(p_i50777_10_ * 256.0F / 360.0F);
        this.type = p_i50777_11_;
        this.data = p_i50777_12_;
        this.speedX = (int)(MathHelper.clamp(p_i50777_13_.x, -3.9D, 3.9D) * 8000.0D);
        this.speedY = (int)(MathHelper.clamp(p_i50777_13_.y, -3.9D, 3.9D) * 8000.0D);
        this.speedZ = (int)(MathHelper.clamp(p_i50777_13_.z, -3.9D, 3.9D) * 8000.0D);
    }

    public static void encode(PacketSpawnEntity msg, PacketBuffer buf) {
        buf.writeVarInt(msg.entityId);
        buf.writeUniqueId(msg.uniqueId);
        buf.writeDouble(msg.x);
        buf.writeDouble(msg.y);
        buf.writeDouble(msg.z);
        buf.writeByte(msg.pitch);
        buf.writeByte(msg.yaw);
        buf.writeVarInt(Registry.ENTITY_TYPE.getId(msg.type));
        buf.writeInt(msg.data);
        buf.writeShort(msg.speedX);
        buf.writeShort(msg.speedY);
        buf.writeShort(msg.speedZ);
    }

    public static PacketSpawnEntity decode(PacketBuffer buf) {
        return new PacketSpawnEntity(buf.readVarInt(), buf.readUniqueId(), buf.readDouble(), buf.readDouble(), buf.readDouble(), buf.readByte(), buf.readByte(), Registry.ENTITY_TYPE.getByValue(buf.readVarInt()), buf.readInt(), new Vec3d(buf.readShort(), buf.readShort(), buf.readShort()));
    }

    public static void handle(PacketSpawnEntity msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            handlePacket(msg.x, msg.y, msg.z, msg.type, msg.entityId, msg.uniqueId, msg.pitch, msg.yaw);
        });
        ctx.get().setPacketHandled(true);
    }

    @OnlyIn(Dist.CLIENT)
    public static void handlePacket(double d0, double d1, double d2, EntityType<?> type, int id, UUID uuid, float pitch, float yaw) {
        Entity entity = null;

        if (type == EntityInit.ETHEREAL_ITEM.get()) {
            entity = new EtherealItemEntity(Minecraft.getInstance().world, d0, d1, d2);
        }

        if (entity != null) {
            entity.setPacketCoordinates(d0, d1, d2);
            entity.rotationPitch = (float) (pitch * 360) / 256.0F;
            entity.rotationYaw = (float) (yaw * 360) / 256.0F;
            entity.setEntityId(id);
            entity.setUniqueId(uuid);
            Minecraft.getInstance().world.addEntity(id, entity);
        }
    }
}
