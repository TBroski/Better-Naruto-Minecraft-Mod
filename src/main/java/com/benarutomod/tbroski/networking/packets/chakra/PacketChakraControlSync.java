package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketChakraControlSync {
    private float data;
    private boolean toClient;

    public PacketChakraControlSync(float data, boolean toClient)
    {
        this.data = data;
        this.toClient = toClient;
    }

    public static void encode(PacketChakraControlSync msg, PacketBuffer buf)
    {
        buf.writeFloat(msg.data);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketChakraControlSync decode(PacketBuffer buf)
    {
        float data = buf.readFloat();
        boolean toClient = buf.readBoolean();
        return new PacketChakraControlSync(data, toClient);
    }

    public static void handle(PacketChakraControlSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (!msg.toClient) {
                ServerPlayerEntity player = ctx.get().getSender();
                IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setChakraControl(msg.data);
            }
            else if (msg.toClient)
            {
                ClientPlayerEntity player = Minecraft.getInstance().player;
                IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setChakraControl(msg.data);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
