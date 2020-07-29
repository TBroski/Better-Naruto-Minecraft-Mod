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

    private int jutsuType;
    private int modifier0;
    private int modifier1;

    public PacketJutsu(int jutsuType, int primaryModifier, int secondaryModifier)
    {
        this.jutsuType = jutsuType;
        this.modifier0 = primaryModifier;
        this.modifier1 = secondaryModifier;
    }

    public static void encode(PacketJutsu msg, PacketBuffer buf)
    {
        buf.writeInt(msg.jutsuType);
        buf.writeInt(msg.modifier0);
        buf.writeInt(msg.modifier1);
    }

    public static PacketJutsu decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        int modifier0 = buf.readInt();
        int modifier1 = buf.readInt();
        return new PacketJutsu(data, modifier0, modifier1);
    }

    public static void handle(PacketJutsu msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            switch (msg.jutsuType)
            {
                case CloneJutsu.BasicCloneJutsuID:
                    CloneJutsu.BasicCloneJutsu(ctx.get().getSender(), 150);
                    break;
                case BodyReplacementJutsu.BasicBodyReplacementJutsuID:
                    BodyReplacementJutsu.BasicBodyReplacementJutsu(ctx.get().getSender(), 50);
                    break;
                case EffectsJutsu.InvisibilityTechniqueID:
                    EffectsJutsu.InvisibilityTechnigue(ctx.get().getSender(), 5);
                    break;
                case EffectsJutsu.HandInfusionID:
                    IPlayerHandler playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                    if (playercap.returnTaijutsu() >= 15) { msg.modifier0 = 3; msg.modifier1 = 2; }
                    else if (playercap.returnTaijutsu() >= 10) { msg.modifier0 = 2; msg.modifier1 = 1; }
                    else if (playercap.returnTaijutsu() >= 5) { msg.modifier0 = 1; msg.modifier1 = 0; }
                    else { msg.modifier0 = 0; msg.modifier1 = 0; }
                    EffectsJutsu.HandInfusion(ctx.get().getSender(), 2, msg.modifier0, msg.modifier1);
                    break;
                case EffectsJutsu.LegInfusionID:
                    playercap = ctx.get().getSender().getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                    if (playercap.returnTaijutsu() >= 15) { msg.modifier0 = 3; msg.modifier1 = 2; }
                    else if (playercap.returnTaijutsu() >= 10) { msg.modifier0 = 2; msg.modifier1 = 1; }
                    else if (playercap.returnTaijutsu() >= 5) { msg.modifier0 = 1; msg.modifier1 = 0; }
                    else { msg.modifier0 = 0; msg.modifier1 = 0; }
                    EffectsJutsu.LegInfusion(ctx.get().getSender(), 2, msg.modifier0, msg.modifier1);
                    break;
                case SummoningJutsu.BasicSummoningJutsuID:
                    SummoningJutsu.BasicSummoningJutsu(ctx.get().getSender(), 75);
                    break;
                case EffectsJutsu.BodyInfusionID:
                    EffectsJutsu.BodyInfusion(ctx.get().getSender(), 3, msg.modifier0, msg.modifier1);
                    break;
                case ShootingJutsu.FireballJutsuID:
                    ShootingJutsu.FireballJutsu(ctx.get().getSender(), 25);
                    break;
                case ShootingJutsu.WaterShurikenJutsuID:
                    ShootingJutsu.WaterShurikenJutsu(ctx.get().getSender(), 5);
                    break;
                case ShootingJutsu.FlyingStonesJutsuID:
                    ShootingJutsu.FlyingStonesJutsu(ctx.get().getSender(), 30);
                    break;
                case ShootingJutsu.LightningBallJutsuID:
                    ShootingJutsu.LightningBallJutsu(ctx.get().getSender(), 35);
                    break;
                case ShootingJutsu.GalePalmJutsuID:
                    ShootingJutsu.GalePalmJutsu(ctx.get().getSender(), 5);
                    break;
                case ShootingJutsu.PhoenixFlowerJutsuID:
                    ShootingJutsu.PhoenixFlowerJutsu(ctx.get().getSender(), 10);
                    break;
                case ShootingJutsu.RagingWavesJutsuID:
                    ShootingJutsu.RagingWavesJutsu(ctx.get().getSender(), 10);
                    break;
                case ShootingJutsu.MudMoatJutsuID:
                    ShootingJutsu.MudMoatJutsu(ctx.get().getSender(), 10);
                    break;
                case ShootingJutsu.StunGunJutsuID:
                    ShootingJutsu.StunGunJutsu(ctx.get().getSender(), 5);
                    break;
                case ShootingJutsu.WindExplosionJutsuID:
                    ShootingJutsu.WindExplosionJutsu(ctx.get().getSender(), 5);
                    break;
                case EffectsJutsu.MoltenFistJutsuID:
                    EffectsJutsu.MoltenFistJutsu(ctx.get().getSender(), 5);
                    break;
                case ShootingJutsu.WaterSharkBulletJutsuID:
                    ShootingJutsu.WaterSharkBulletJutsu(ctx.get().getSender(), 50);
                    break;
                case EffectsJutsu.FistRockJutsuID:
                    EffectsJutsu.FistRockJutsu(ctx.get().getSender(), 5);
                    break;
                case ShootingJutsu.LightningArrowJutsuID:
                    ShootingJutsu.LightningArrowJutsu(ctx.get().getSender(), 10);
                    break;
                case ShootingJutsu.WindArrowJutsuID:
                    ShootingJutsu.WindArrowJutsu(ctx.get().getSender(), 10);
                    break;
                case TransformationJutsu.TransformationJutsuID:
                    TransformationJutsu.TransormationJutsu(ctx.get().getSender(), 10); //Chakra variable used outside the static method. If changed, change 'chakraAmount' variable in class as well.
                    break;
                case SharinganJutsu.AmaterasuJutsuID:
                    SharinganJutsu.AmaterasuJutsu(ctx.get().getSender(), 100);
                    break;
                case SharinganJutsu.TsukuyomiJutsuID:
                    SharinganJutsu.TsukuyomiJutsu(ctx.get().getSender(), 10);
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
