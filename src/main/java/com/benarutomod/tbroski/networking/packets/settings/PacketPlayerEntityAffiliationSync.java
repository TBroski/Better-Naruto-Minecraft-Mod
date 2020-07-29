package com.benarutomod.tbroski.networking.packets.settings;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerEntityAffiliationSync {
    private String affiliation;
    private boolean toClient;

    public PacketPlayerEntityAffiliationSync(String affiliation, boolean toClient)
    {
        this.affiliation = affiliation;
        this.toClient = toClient;
    }

    public static void encode(PacketPlayerEntityAffiliationSync msg, PacketBuffer buf)
    {
        buf.writeString(msg.affiliation);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketPlayerEntityAffiliationSync decode(PacketBuffer buf)
    {
        String data = buf.readString();
        boolean toClient = buf.readBoolean();
        return new PacketPlayerEntityAffiliationSync(data, toClient);
    }

    public static void handle(PacketPlayerEntityAffiliationSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                Minecraft mc = Minecraft.getInstance();
                IPlayerHandler playercap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setPlayerEntityAffiliation(msg.affiliation);
            }
            else {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setPlayerEntityAffiliation(msg.affiliation);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
