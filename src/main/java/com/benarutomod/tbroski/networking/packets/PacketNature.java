package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketNature {

    //1 = Fire
    //2 = Water
    //3 = Earth
    //4 = Wind
    //5 = Lightning
    //6 = *CHAKRA*
    //7 = Magnet

    private int nature;
    private boolean has;
    private boolean toClient;

    public PacketNature(int nature, boolean has, boolean toClient)
    {
        this.nature = nature;
        this.has = has;
        this.toClient = toClient;
    }

    public static void encode(PacketNature msg, PacketBuffer buf)
    {
        buf.writeInt(msg.nature);
        buf.writeBoolean(msg.has);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketNature decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean has = buf.readBoolean();
        boolean toClient = buf.readBoolean();
        return new PacketNature(data, has, toClient);
    }

    public static void handle(PacketNature msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            switch (msg.nature)
            {
                case 1:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setFireNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setFireNature(msg.has);
                    }
                    break;
                case 2:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setWaterNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setWaterNature(msg.has);
                    }
                    break;
                case 3:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setEarthNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setEarthNature(msg.has);
                    }
                    break;
                case 4:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setWindNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setWindNature(msg.has);
                    }
                    break;
                case 5:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setLightningNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setLightningNature(msg.has);
                    }
                    break;
                case 6:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setChakraBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setChakraBoolean(msg.has);
                    }
                    break;
                case 7:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setMagnetNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setMagnetNature(msg.has);
                    }
                    break;
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
