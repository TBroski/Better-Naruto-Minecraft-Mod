package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketCurseMarkMeleeAttacked {


    public PacketCurseMarkMeleeAttacked()
    {
    }

    public static void encode(PacketCurseMarkMeleeAttacked msg, PacketBuffer buf)
    {
    }

    public static PacketCurseMarkMeleeAttacked decode(PacketBuffer buf)
    {
        return new PacketCurseMarkMeleeAttacked();
    }

    public static void handle(PacketCurseMarkMeleeAttacked msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            PlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

            player_cap.setPlayerCurseMark(1);
            Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ItemInit.FANGS_SYMBOL.get()));

        });
        ctx.get().setPacketHandled(true);
    }
}
