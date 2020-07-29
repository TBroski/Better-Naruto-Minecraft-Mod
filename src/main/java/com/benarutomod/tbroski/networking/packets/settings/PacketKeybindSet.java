package com.benarutomod.tbroski.networking.packets.settings;

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

public class PacketKeybindSet {

    //1 = Key1
    //2 = Key2
    //3 = Key3 etc.

    private int key;
    private String jutsu;
    private boolean toClient;

    public PacketKeybindSet(int key, String jutsu, boolean toClient)
    {
        this.key = key;
        this.toClient = toClient;
        this.jutsu = jutsu;
    }

    public static void encode(PacketKeybindSet msg, PacketBuffer buf)
    {
        buf.writeInt(msg.key);
        buf.writeString(msg.jutsu);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketKeybindSet decode(PacketBuffer buf)
    {
        int key = buf.readInt();
        String jutsu = buf.readString();
        boolean toClient = buf.readBoolean();
        return new PacketKeybindSet(key, jutsu, toClient);
    }

    public static void handle(PacketKeybindSet msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            switch (msg.key)
            {
                case 1:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind1(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind1(msg.jutsu);
                    }
                    break;
                case 2:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind2(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind2(msg.jutsu);
                    }
                    break;
                case 3:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind3(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind3(msg.jutsu);
                    }
                    break;
                case 4:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind4(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind4(msg.jutsu);
                    }
                    break;
                case 5:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind5(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind5(msg.jutsu);
                    }
                    break;
                case 6:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind6(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind6(msg.jutsu);
                    }
                    break;
                case 7:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind7(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind7(msg.jutsu);
                    }
                    break;
                case 8:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind8(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind8(msg.jutsu);
                    }
                    break;
                case 9:
                    if (msg.toClient)
                    {
                        Minecraft mc = Minecraft.getInstance();
                        ClientPlayerEntity player = mc.player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind9(msg.jutsu);
                    }
                    else if(!msg.toClient)
                    {
                        ServerPlayerEntity player = ctx.get().getSender();
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setKeybind9(msg.jutsu);
                    }
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
