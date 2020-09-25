package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketMaxChakraSync {

    private float maxChakra;
    private int restrictedChakra;
    private boolean toClient;

    public PacketMaxChakraSync(float maxChakra, int restrictedChakra, boolean toClient) {
        this.maxChakra = maxChakra;
        this.restrictedChakra = restrictedChakra;
        this.toClient = toClient;
    }

    public static void encode(PacketMaxChakraSync msg, PacketBuffer buf) {
        buf.writeFloat(msg.maxChakra);
        buf.writeInt(msg.restrictedChakra);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketMaxChakraSync decode(PacketBuffer buf) {
        float data = buf.readFloat();
        int data2 = buf.readInt();
        return new PacketMaxChakraSync(data, data2, buf.readBoolean());
    }

    public static void handle(PacketMaxChakraSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                Minecraft mc = Minecraft.getInstance();
                mc.player.getPersistentData().putInt("restrictedchakra", msg.restrictedChakra);
                IPlayerHandler playercap = mc.player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setmaxChakra(msg.maxChakra);
            }
            else {
                ServerPlayerEntity player= ctx.get().getSender();
                player.getPersistentData().putInt("restrictedchakra", msg.restrictedChakra);
                IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setmaxChakra(msg.maxChakra);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
