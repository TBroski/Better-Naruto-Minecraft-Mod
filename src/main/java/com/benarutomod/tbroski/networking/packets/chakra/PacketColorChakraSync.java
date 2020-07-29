package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketColorChakraSync {
    private int colorChakra;

    public PacketColorChakraSync(int colorChakra)
    {
        this.colorChakra = colorChakra;
    }

    public static void encode(PacketColorChakraSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.colorChakra);
    }

    public static PacketColorChakraSync decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        return new PacketColorChakraSync(data);
    }

    public static void handle(PacketColorChakraSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft mc = Minecraft.getInstance();
            IPlayerHandler playercap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            playercap.setcolorChakra(msg.colorChakra);
        });
        ctx.get().setPacketHandled(true);
    }
}
