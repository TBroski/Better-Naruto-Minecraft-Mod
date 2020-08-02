package com.benarutomod.tbroski.networking.packets.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.jutsu.EffectsJutsu;
import com.benarutomod.tbroski.common.jutsu.JutsuCaller;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketJutsuCaller {

    private String jutsuType;
    private int keybind;

    public PacketJutsuCaller(String jutsuType, int keybind)
    {
        this.jutsuType = jutsuType;
        this.keybind = keybind;
    }

    public static void encode(PacketJutsuCaller msg, PacketBuffer buf)
    {
        buf.writeString(msg.jutsuType);
        buf.writeInt(msg.keybind);
    }

    public static PacketJutsuCaller decode(PacketBuffer buf)
    {
        String data = buf.readString();
        int keybind = buf.readInt();
        return new PacketJutsuCaller(data, keybind);
    }

    public static void handle(PacketJutsuCaller msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            if (msg.keybind < 0) {
                JutsuCaller.JutsuCaller(ctx.get().getSender(), msg.jutsuType);
                return;
            }
            switch (msg.keybind) {
                case 1:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind1());
                    break;
                case 2:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind2());
                    break;
                case 3:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind3());
                    break;
                case 4:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind4());
                    break;
                case 5:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind5());
                    break;
                case 6:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind6());
                    break;
                case 7:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind7());
                    break;
                case 8:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind8());
                    break;
                case 9:
                    JutsuCaller.JutsuCaller(ctx.get().getSender(), playercap.returnKeybind9());
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
