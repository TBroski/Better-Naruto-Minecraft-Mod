package com.benarutomod.tbroski.networking.packets.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketJutsuNBTSync {

    private String nbtName;
    private boolean toggled;

    public PacketJutsuNBTSync(String nbtName, boolean toggled)
    {
        this.nbtName = nbtName;
        this.toggled = toggled;
    }

    public static void encode(PacketJutsuNBTSync msg, PacketBuffer buf)
    {
        buf.writeString(msg.nbtName);
        buf.writeBoolean(msg.toggled);
    }

    public static PacketJutsuNBTSync decode(PacketBuffer buf)
    {
        String data = buf.readString();
        boolean toggled = buf.readBoolean();
        return new PacketJutsuNBTSync(data, toggled);
    }

    public static void handle(PacketJutsuNBTSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            ctx.get().getSender().getPersistentData().putBoolean(msg.nbtName, msg.toggled);
        });
        ctx.get().setPacketHandled(true);
    }
}
