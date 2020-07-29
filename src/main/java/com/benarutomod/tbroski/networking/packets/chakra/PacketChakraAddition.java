package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Random;
import java.util.function.Supplier;

public class PacketChakraAddition {

    public PacketChakraAddition()
    {
        ;
    }

    public static void encode(PacketChakraAddition msg, PacketBuffer buf)
    {
    }

    public static PacketChakraAddition decode(PacketBuffer buf)
    {
        return new PacketChakraAddition();
    }

    public static void handle(PacketChakraAddition msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (!ctx.get().getSender().abilities.isCreativeMode) {
                ctx.get().getSender().getHeldItem(Hand.MAIN_HAND).shrink(1);
            }
            IPlayerHandler player_cap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            player_cap.setcolorChakra(new Random().nextInt(6));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> ctx.get().getSender()), new PacketColorChakraSync(player_cap.returncolorChakra()));
            player_cap.setmaxChakra(player_cap.returnPlayerClan().getStartingChakra());
            player_cap.setChakra(player_cap.returnPlayerClan().getStartingChakra());
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> ctx.get().getSender()), new PacketMaxChakraSync(player_cap.returnmaxChakra(), ctx.get().getSender().getPersistentData().getInt("restrictedchakra")));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> ctx.get().getSender()), new PacketChakraSync(player_cap.returnChakra()));
            player_cap.addregenChakra(7.5F);
            player_cap.setChakraBoolean(true);
        });
        ctx.get().setPacketHandled(true);
    }
}
