package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.BodyHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerHasBodyModeSync {

    //1 = Curse Mark
    //2 = Toad Sage

    private int bodyMode;
    private int value;
    private boolean toClient;

    public PacketPlayerHasBodyModeSync(int bodyMode, int value, boolean toClient)
    {
        this.bodyMode = bodyMode;
        this.value = value;
        this.toClient = toClient;
    }

    public static void encode(PacketPlayerHasBodyModeSync msg, PacketBuffer buf)
    {
        buf.writeInt(msg.bodyMode);
        buf.writeInt(msg.value);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketPlayerHasBodyModeSync decode(PacketBuffer buf)
    {
        int bodyMode = buf.readInt();
        int value = buf.readInt();
        boolean toClient = buf.readBoolean();
        return new PacketPlayerHasBodyModeSync(bodyMode, value, toClient);
    }

    public static void handle(PacketPlayerHasBodyModeSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            switch (msg.bodyMode) {
                case 1:
                    if (msg.toClient) {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setPlayerCurseMark(msg.value);
                    } else {
                        LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setPlayerCurseMark(msg.value);
                    }
                    break;
                case 2:
                    if (msg.toClient) {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setPlayerToadSageMode(msg.value);
                    } else {
                        LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                        playercap.setPlayerToadSageMode(msg.value);
                    }
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
