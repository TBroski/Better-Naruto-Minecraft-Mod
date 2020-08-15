package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Iterator;
import java.util.function.Supplier;

public class PacketAdvancement {

    private String advancement;

    public PacketAdvancement(String advancement)
    {
        this.advancement = advancement;
    }

    public static void encode(PacketAdvancement msg, PacketBuffer buf)
    {
        buf.writeString(msg.advancement);
    }

    public static PacketAdvancement decode(PacketBuffer buf)
    {
        String data = buf.readString();
        return new PacketAdvancement(data);
    }

    public static void handle(PacketAdvancement msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            AdvancementHelper.grantAdvancement(ctx.get().getSender(), msg.advancement);
        });
        ctx.get().setPacketHandled(true);
    }
}
