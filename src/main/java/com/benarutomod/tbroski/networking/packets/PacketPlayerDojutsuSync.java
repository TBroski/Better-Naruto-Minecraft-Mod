package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayerDojutsuSync {

    private String dojutsu;
    private boolean leftEye;
    private boolean toClient;

    public PacketPlayerDojutsuSync(String dojutsu, boolean leftEye, boolean toClient)
    {
        this.dojutsu = dojutsu;
        this.leftEye = leftEye;
        this.toClient = toClient;
    }

    public static void encode(PacketPlayerDojutsuSync msg, PacketBuffer buf)
    {
        buf.writeString(msg.dojutsu);
        buf.writeBoolean(msg.leftEye);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketPlayerDojutsuSync decode(PacketBuffer buf)
    {
        String dojutsu = buf.readString();
        boolean leftEye = buf.readBoolean();
        boolean toClient = buf.readBoolean();
        return new PacketPlayerDojutsuSync(dojutsu, leftEye, toClient);
    }

    public static void handle(PacketPlayerDojutsuSync msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            if (msg.leftEye) {
                if (msg.toClient) {
                    ClientPlayerEntity player = Minecraft.getInstance().player;
                    LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setPlayerLeftDojutsu(DojutsuHelper.getDojutsuFromString(msg.dojutsu));
                } else {
                    LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setPlayerLeftDojutsu(DojutsuHelper.getDojutsuFromString(msg.dojutsu));
                }
            }
            else {
                if (msg.toClient) {
                    ClientPlayerEntity player = Minecraft.getInstance().player;
                    LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setPlayerRightDojutsu(DojutsuHelper.getDojutsuFromString(msg.dojutsu));
                } else {
                    LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setPlayerRightDojutsu(DojutsuHelper.getDojutsuFromString(msg.dojutsu));
                }
            }

        });
        ctx.get().setPacketHandled(true);
    }
}
