package com.benarutomod.tbroski.networking.packets.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketJutsuCancelEvent {

    private String jutsuType;

    public PacketJutsuCancelEvent(String jutsuType)
    {
        this.jutsuType = jutsuType;
    }

    public static void encode(PacketJutsuCancelEvent msg, PacketBuffer buf)
    {
        buf.writeString(msg.jutsuType);
    }

    public static PacketJutsuCancelEvent decode(PacketBuffer buf)
    {
        String data = buf.readString();
        return new PacketJutsuCancelEvent(data);
    }

    public static void handle(PacketJutsuCancelEvent msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (jutsu.getName().equalsIgnoreCase(msg.jutsuType)) {
                    IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                    jutsu.throwCancelEvent(ctx.get().getSender());
                    break;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
