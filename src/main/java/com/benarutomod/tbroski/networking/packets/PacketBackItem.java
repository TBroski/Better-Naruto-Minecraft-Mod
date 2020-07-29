package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.items.tools.BeNMSwordItemBase;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketBackItem {

    public PacketBackItem()
    {
    }

    public static void encode(PacketBackItem msg, PacketBuffer buf)
    {
    }

    public static PacketBackItem decode(PacketBuffer buf)
    {
        return new PacketBackItem();
    }

    public static void handle(PacketBackItem msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            if (player.getHeldItemMainhand().isEmpty() && Main.getBackpackStack(player).getItem() instanceof BeNMSwordItemBase) {
                player.setItemStackToSlot(EquipmentSlotType.MAINHAND, Main.getBackpackStack(player).copy());
                player.inventory.setInventorySlotContents(41, ItemStack.EMPTY);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
