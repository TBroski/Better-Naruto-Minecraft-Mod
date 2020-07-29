package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketShinobiLevel {

    //1 = Genin

    private int shinobiLevel;
    private boolean toClient;

    public PacketShinobiLevel(int shinobiLevel, boolean toClient)
    {
        this.shinobiLevel = shinobiLevel;
        this.toClient = toClient;
    }

    public static void encode(PacketShinobiLevel msg, PacketBuffer buf)
    {
        buf.writeInt(msg.shinobiLevel);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketShinobiLevel decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean toClient = buf.readBoolean();
        return new PacketShinobiLevel(data, toClient);
    }

    public static void handle(PacketShinobiLevel msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
                switch (msg.shinobiLevel)
                {
                    case 1:
                        if (msg.toClient)
                        {
                            IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(1);
                        }
                        else {
                            IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(1);
                        }
                        break;
                    case 2:
                        if (msg.toClient)
                        {
                            IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(2);
                        }
                        else {
                            IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(2);
                        }
                        break;
                    case 3:
                        if (msg.toClient)
                        {
                            IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(3);
                        }
                        else {
                            IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(3);
                        }
                        break;
                    case 4:
                        if (msg.toClient)
                        {
                            IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(4);
                        }
                        else {
                            IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(4);
                        }
                        break;
                    case 5:
                        if (msg.toClient)
                        {
                            IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(5);
                        }
                        else {
                            IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                            playercap.setShinobiLevel(5);
                        }
                        break;
                }
        });
        ctx.get().setPacketHandled(true);
    }
}
