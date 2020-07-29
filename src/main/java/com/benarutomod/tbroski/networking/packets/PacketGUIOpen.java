package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.client.gui.player.ShinobiStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketGUIOpen {

    public PacketGUIOpen()
    {

    }

    public static void encode(PacketGUIOpen msg, PacketBuffer buf)
    {
    }

    public static PacketGUIOpen decode(PacketBuffer buf)
    {
        return new PacketGUIOpen();
    }

    public static void handle(PacketGUIOpen msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            Minecraft.getInstance().displayGuiScreen(new ShinobiStats());
        });
        ctx.get().setPacketHandled(true);
    }
}
