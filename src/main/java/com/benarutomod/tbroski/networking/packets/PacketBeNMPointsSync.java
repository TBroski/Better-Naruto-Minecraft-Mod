package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBeNMPointsSync {
    private int points;
    private boolean toClient;

    public PacketBeNMPointsSync(int points, boolean toClient)
    {
        this.points = points;
        this.toClient = toClient;
    }

    public static void encode(PacketBeNMPointsSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.points);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketBeNMPointsSync decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean toClient = buf.readBoolean();
        return new PacketBeNMPointsSync(data, toClient);
    }

    public static void handle(PacketBeNMPointsSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                Minecraft mc = Minecraft.getInstance();
                IPlayerHandler playercap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setBeNMPoints(msg.points);
            }
            else if (!msg.toClient)
            {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setBeNMPoints(msg.points);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
