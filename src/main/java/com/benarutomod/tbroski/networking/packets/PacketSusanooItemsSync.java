package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSusanooItemsSync {

    private boolean toClient;
    private int playerid;
    private ItemStack mainHand;
    private ItemStack offHand;

    public PacketSusanooItemsSync(int playerid, ItemStack mainHand, ItemStack offHand, boolean toClient) {
        this.playerid = playerid;
        this.mainHand = mainHand;
        this.offHand = offHand;
        this.toClient = toClient;
    }

    public static void encode(PacketSusanooItemsSync msg, PacketBuffer buf) {
        buf.writeInt(msg.playerid);
        buf.writeItemStack(msg.mainHand);
        buf.writeItemStack(msg.offHand);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketSusanooItemsSync decode(PacketBuffer buf) {
        return new PacketSusanooItemsSync(buf.readInt(), buf.readItemStack(), buf.readItemStack(), buf.readBoolean());
    }

    public static void handle(PacketSusanooItemsSync msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (msg.toClient) {
                Entity entity = Minecraft.getInstance().world.getEntityByID(msg.playerid);
                if (entity instanceof PlayerEntity) {
                    PlayerEntity player = (PlayerEntity) entity;
                    IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                    playercap.setSusanooMainHand(msg.mainHand);
                    playercap.setSusanooMainHand(msg.offHand);
                }
            }
            else {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                playercap.setSusanooMainHand(msg.mainHand);
                playercap.setSusanooOffHand(msg.offHand);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
