package com.benarutomod.tbroski.networking.packets;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketTaijutsu {

    private int taijutsu;
    private boolean toClient;

    public PacketTaijutsu(int taijutsu, boolean toClient)
    {
        this.taijutsu = taijutsu;
        this.toClient = toClient;
    }

    public static void encode(PacketTaijutsu msg, PacketBuffer buf)
    {
        buf.writeInt(msg.taijutsu);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketTaijutsu decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean toClient = buf.readBoolean();
        return new PacketTaijutsu(data, toClient);
    }

    public static void handle(PacketTaijutsu msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
                if (msg.toClient)
                {
                    LazyOptional<IPlayerHandler> player_cap = Minecraft.getInstance().player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
                    playerc.setTaijutsu(msg.taijutsu);
                }
                else {
                    LazyOptional<IPlayerHandler> player_cap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
                    playerc.setTaijutsu(msg.taijutsu);
                }
        });
        ctx.get().setPacketHandled(true);
    }
}
