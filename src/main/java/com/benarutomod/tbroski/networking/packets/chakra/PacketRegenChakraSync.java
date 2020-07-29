package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketRegenChakraSync {

    private float regenChakra;
    private boolean toClient;

    public PacketRegenChakraSync(float regenChakra, boolean toClient)
    {
        this.regenChakra = regenChakra;
        this.toClient = toClient;
    }

    public static void encode(PacketRegenChakraSync msg, PacketBuffer buf)
    {
        buf.writeFloat(msg.regenChakra);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketRegenChakraSync decode(PacketBuffer buf)
    {
        float data = buf.readFloat();
        boolean toClient = buf.readBoolean();
        return new PacketRegenChakraSync(data, toClient);
    }

    public static void handle(PacketRegenChakraSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setregenChakra(msg.regenChakra);
            }
            else {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setregenChakra(msg.regenChakra);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
