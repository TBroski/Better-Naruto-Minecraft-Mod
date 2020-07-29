package com.benarutomod.tbroski.networking.packets.jutsu;

import com.benarutomod.tbroski.common.jutsu.*;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketSetJutsuBoolean {

    //1 = CloneJutsu
    //2 = Body Replacement
    //3 = Invisibility
    //4 = Summoning
    //5 = Fireball
    //6 = Water Shuriken
    //7 = Flying Stones
    //8 = Lightning Ball
    //9 = Gale Palm

    private boolean has;
    private boolean toClient;
    private int jutsuType;

    public PacketSetJutsuBoolean(int jutsuType, boolean has, boolean toClient)
    {
        this.jutsuType = jutsuType;
        this.toClient = toClient;
        this.has = has;
    }

    public static void encode(PacketSetJutsuBoolean msg, PacketBuffer buf)
    {
        buf.writeInt(msg.jutsuType);
        buf.writeBoolean(msg.has);
        buf.writeBoolean(msg.toClient);
    }

    public static PacketSetJutsuBoolean decode(PacketBuffer buf)
    {
        int data = buf.readInt();
        boolean has = buf.readBoolean();
        boolean toClient = buf.readBoolean();
        return new PacketSetJutsuBoolean(data, has, toClient);
    }

    public static void handle(PacketSetJutsuBoolean msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            switch (msg.jutsuType)
            {
                case CloneJutsu.BasicCloneJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setCloneJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setCloneJutsuBoolean(msg.has);
                    }
                    break;
                case BodyReplacementJutsu.BasicBodyReplacementJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setBodyReplacementBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setBodyReplacementBoolean(msg.has);
                    }
                    break;
                case EffectsJutsu.InvisibilityTechniqueID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setInvisibilityBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setInvisibilityBoolean(msg.has);
                    }
                    break;
                case SummoningJutsu.BasicSummoningJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setSummoningBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setSummoningBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.FireballJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setFireballJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setFireballJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.WaterShurikenJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWaterShurikenJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWaterShurikenJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.FlyingStonesJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setFlyingStonesJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setFlyingStonesJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.LightningBallJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setLightningBallJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setLightningBallJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.GalePalmJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setGalePalmJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setGalePalmJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.PhoenixFlowerJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setPhoenixFlowerJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setPhoenixFlowerJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.RagingWavesJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setRagingWavesJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setRagingWavesJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.MudMoatJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setMudMoatJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setMudMoatJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.StunGunJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setStunGunJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setStunGunJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.WindExplosionJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWindExplosionJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWindExplosionJutsuBoolean(msg.has);
                    }
                    break;
                case EffectsJutsu.MoltenFistJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setMoltenFistJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setMoltenFistJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.WaterSharkBulletJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWaterSharkBulletJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWaterSharkBulletJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.LightningArrowJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setLightningArrowJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setLightningArrowJutsuBoolean(msg.has);
                    }
                    break;
                case EffectsJutsu.FistRockJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setFistRockJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setFistRockJutsuBoolean(msg.has);
                    }
                    break;
                case ShootingJutsu.WindArrowJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWindArrowJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setWindArrowJutsuBoolean(msg.has);
                    }
                    break;
                case TransformationJutsu.TransformationJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setTransformationBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setTransformationBoolean(msg.has);
                    }
                    break;
                case SharinganJutsu.AmaterasuJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setAmaterasuJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setAmaterasuJutsuBoolean(msg.has);
                    }
                    break;
                case SharinganJutsu.TsukuyomiJutsuID:
                    if (msg.toClient)
                    {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setTsukuyomiJutsuBoolean(msg.has);
                    }
                    else {
                        ServerPlayerEntity player = ctx.get().getSender();
                        IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                        playercap.setTsukuyomiJutsuBoolean(msg.has);
                    }
                    break;
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
