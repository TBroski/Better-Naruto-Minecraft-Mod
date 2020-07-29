package com.benarutomod.tbroski.networking.packets.settings;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketEyeSlotSync {
    private boolean toClient;
    private int playerEyeSlot;

    public PacketEyeSlotSync(int playerEyeSlot, boolean toClient)
    {
        this.playerEyeSlot = playerEyeSlot;
        this.toClient = toClient;
    }

    public static void encode(PacketEyeSlotSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.playerEyeSlot);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketEyeSlotSync decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean toClient = buf.readBoolean();
        return new PacketEyeSlotSync(data, toClient);
    }

    public static void handle(PacketEyeSlotSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (!msg.toClient) {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setplayerEyeSlot(msg.playerEyeSlot);
            }
            else {
                IPlayerHandler playercap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setplayerEyeSlot(msg.playerEyeSlot);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
