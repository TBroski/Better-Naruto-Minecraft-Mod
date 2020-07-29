package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketChakraSync {
    private float chakra;

    public PacketChakraSync(float chakra)
    {
        this.chakra = chakra;
    }

    public static void encode(PacketChakraSync msg, PacketBuffer buf)
    {
        buf.writeFloat(msg.chakra);
    }

    public static PacketChakraSync decode(PacketBuffer buf)
    {
        float data = buf.readFloat();
        return new PacketChakraSync(data);
    }

    public static void handle(PacketChakraSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            IPlayerHandler playercap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            playercap.setChakra(msg.chakra);
        });
        ctx.get().setPacketHandled(true);
    }
}
