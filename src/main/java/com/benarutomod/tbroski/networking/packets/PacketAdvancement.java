package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.Main;
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
                    Advancement adv = ctx.get().getSender().server.getAdvancementManager().getAdvancement(new ResourceLocation(Main.MODID + ":" + msg.advancement));
                    AdvancementProgress ap = ctx.get().getSender().getAdvancements().getProgress(adv);
                    if (!ap.isDone()) {
                        Iterator iterator = ap.getRemaningCriteria().iterator();
                        while (iterator.hasNext()) {
                            String criterion = (String) iterator.next();
                            ctx.get().getSender().getAdvancements().grantCriterion(adv, criterion);
                        }
                    }
        });
        ctx.get().setPacketHandled(true);
    }
}
