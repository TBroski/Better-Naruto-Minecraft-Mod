package com.benarutomod.tbroski.networking.packets.jutsu;

import com.benarutomod.tbroski.common.jutsu.*;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketJutsu {
    //1 = Basic Clone
    //2 = Basic Body Replacement
    //3 = Invisibility Technique
    //4 = Hand Infusion
    //5 = Leg Infusion
    //6 = Summoning Jutsu
    //7 = Body Infusion

    private String jutsuType;

    public PacketJutsu(String jutsuType)
    {
        this.jutsuType = jutsuType;
    }

    public static void encode(PacketJutsu msg, PacketBuffer buf)
    {
        buf.writeString(msg.jutsuType);
    }

    public static PacketJutsu decode(PacketBuffer buf)
    {
        String data = buf.readString();
        return new PacketJutsu(data);
    }

    public static void handle(PacketJutsu msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
/*            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (jutsu.getName().equalsIgnoreCase(msg.jutsuType)) {
                    IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                    int taijutsuModifier0 = 0;
                    int taijutsuModifier1 = 0;
                    if (playercap.returnTaijutsu() >= 15) {
                        taijutsuModifier0 = 3; taijutsuModifier1 = 2;
                    }
                    else if (playercap.returnTaijutsu() >= 10) {
                        taijutsuModifier0 = 2; taijutsuModifier1 = 1;
                    }
                    else if (playercap.returnTaijutsu() >= 5) {
                        taijutsuModifier0 = 1; taijutsuModifier1 = 0;
                    }
                    jutsu.act(ctx.get().getSender(), taijutsuModifier0, taijutsuModifier1);
                    break;
                }
            }*/
            if (msg.jutsuType.equalsIgnoreCase("handinfusion")) {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                int taijutsuModifier0 = 0;
                int taijutsuModifier1 = 0;
                if (playercap.returnTaijutsu() >= 15) {
                    taijutsuModifier0 = 3; taijutsuModifier1 = 2;
                }
                else if (playercap.returnTaijutsu() >= 10) {
                    taijutsuModifier0 = 2; taijutsuModifier1 = 1;
                }
                else if (playercap.returnTaijutsu() >= 5) {
                    taijutsuModifier0 = 1; taijutsuModifier1 = 0;
                }
                EffectsJutsu.HandInfusion(ctx.get().getSender(), 2, taijutsuModifier0, taijutsuModifier1);
            }
            if (msg.jutsuType.equalsIgnoreCase("leginfusion")) {
                IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                int taijutsuModifier0 = 0;
                int taijutsuModifier1 = 0;
                if (playercap.returnTaijutsu() >= 15) {
                    taijutsuModifier0 = 3; taijutsuModifier1 = 2;
                }
                else if (playercap.returnTaijutsu() >= 10) {
                    taijutsuModifier0 = 2; taijutsuModifier1 = 1;
                }
                else if (playercap.returnTaijutsu() >= 5) {
                    taijutsuModifier0 = 1; taijutsuModifier1 = 0;
                }
                EffectsJutsu.LegInfusion(ctx.get().getSender(), 2, taijutsuModifier0, taijutsuModifier1);
            }
            if (msg.jutsuType.equalsIgnoreCase("bodyinfusion")) {
                EffectsJutsu.BodyInfusion(ctx.get().getSender(), 3, 0, 0);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
