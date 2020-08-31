package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.chakrastyles.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.MinecraftForge;
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
    //8 = Wood
    //9 = Lava
    //10 = Ice
    //11 = Boil
    //12 = Scorch
    //13 = Storm
    //14 = Explosion

    private int nature;
    private boolean has;
    private boolean showGUI;
    private boolean toClient;

    public PacketNature(int nature, boolean has, boolean showGUI, boolean toClient)
    {
        this.nature = nature;
        this.has = has;
        this.showGUI = showGUI;
        this.toClient = toClient;
    }

    public static void encode(PacketNature msg, PacketBuffer buf)
    {
        buf.writeInt(msg.nature);
        buf.writeBoolean(msg.has);
        buf.writeBoolean(msg.showGUI);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketNature decode(PacketBuffer buf)
    {
        return new PacketNature(buf.readInt(), buf.readBoolean(), buf.readBoolean(), buf.readBoolean());
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
                        if (msg.showGUI)
                            Minecraft.getInstance().displayGuiScreen(new FirePaperGui());
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
                        if (msg.showGUI)
                            Minecraft.getInstance().displayGuiScreen(new WaterPaperGui());
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
                        if (msg.showGUI)
                            Minecraft.getInstance().displayGuiScreen(new EarthPaperGui());
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
                        if (msg.showGUI)
                            Minecraft.getInstance().displayGuiScreen(new WindPaperGui());
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
                        if (msg.showGUI)
                            Minecraft.getInstance().displayGuiScreen(new LightningPaperGui());
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
                case 8:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setWoodNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setWoodNature(msg.has);
                    }
                    break;
                case 9:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setLavaNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setLavaNature(msg.has);
                    }
                    break;
                case 10:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setIceNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setIceNature(msg.has);
                    }
                    break;
                case 11:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setBoilNature(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setBoilNature(msg.has);
                    }
                    break;
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
