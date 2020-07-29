package com.benarutomod.tbroski.networking.packets.chakra;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketChakraBoolean {

    private boolean has;
    private boolean toClient;

    public PacketChakraBoolean(boolean has, boolean toClient)
    {
        this.has = has;
        this.toClient = toClient;
    }

    public static void encode(PacketChakraBoolean msg, PacketBuffer buf)
    {
        buf.writeBoolean(msg.has);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketChakraBoolean decode(PacketBuffer buf)
    {
        boolean has = buf.readBoolean();
        boolean toClient = buf.readBoolean();
        return new PacketChakraBoolean(has, toClient);
    }

    public static void handle(PacketChakraBoolean msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
                if (msg.toClient)
                {
                    ClientPlayerEntity player = Minecraft.getInstance().player;
                    LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setChakraBoolean(msg.has);
                }
                else {
                    LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setChakraBoolean(msg.has);
                }

        });
        ctx.get().setPacketHandled(true);
    }
}
