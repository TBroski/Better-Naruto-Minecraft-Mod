package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketTruthSeekingOrbsSync {

    private int playerId;
    private int truthSeekingOrbsAmount;

    public PacketTruthSeekingOrbsSync(int playerId, int truthSeekingOrbsAmount) {
        this.playerId = playerId;
        this.truthSeekingOrbsAmount = truthSeekingOrbsAmount;
    }

    public static void encode(PacketTruthSeekingOrbsSync msg, PacketBuffer buf) {
        buf.writeInt(msg.playerId);
        buf.writeInt(msg.truthSeekingOrbsAmount);
    }

    public static PacketTruthSeekingOrbsSync decode(PacketBuffer buf) {
        return new PacketTruthSeekingOrbsSync(buf.readInt(), buf.readInt());
    }

    public static void handle(PacketTruthSeekingOrbsSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            Entity entity = Minecraft.getInstance().world.getEntityByID(msg.playerId);
            if (entity instanceof PlayerEntity) {
                LazyOptional<IPlayerHandler> player_cap = entity.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
                playerc.setTruthSeekingOrbAmount(msg.truthSeekingOrbsAmount);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
