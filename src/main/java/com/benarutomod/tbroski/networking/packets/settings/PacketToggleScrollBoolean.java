package com.benarutomod.tbroski.networking.packets.settings;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketToggleScrollBoolean {

    private boolean has;
    private boolean toClient;
    private int playerID;

    public PacketToggleScrollBoolean(boolean has, boolean toClient, int playerID)
    {
        this.has = has;
        this.toClient = toClient;
        this.playerID = playerID;
    }

    public static void encode(PacketToggleScrollBoolean msg, PacketBuffer buf)
    {
        buf.writeBoolean(msg.has);
        buf.writeBoolean(msg.toClient);
        buf.writeInt(msg.playerID);
    }

    public static PacketToggleScrollBoolean decode(PacketBuffer buf)
    {
        boolean has = buf.readBoolean();
        boolean toClient = buf.readBoolean();
        int playerID = buf.readInt();
        return new PacketToggleScrollBoolean(has, toClient, playerID);
    }

    public static void handle(PacketToggleScrollBoolean msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
                if (msg.toClient)
                {
                    ClientPlayerEntity player = (ClientPlayerEntity) Minecraft.getInstance().world.getEntityByID(msg.playerID);
                    LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setToggleScrollRenderer(msg.has);
                }
                else {
                    LazyOptional<IPlayerHandler> capabilities = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                    playercap.setToggleScrollRenderer(msg.has);
                }

        });
        ctx.get().setPacketHandled(true);
    }
}
