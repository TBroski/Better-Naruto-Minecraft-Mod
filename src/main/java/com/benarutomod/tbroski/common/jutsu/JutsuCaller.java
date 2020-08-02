package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsu;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class JutsuCaller {

    public static void JutsuCaller(PlayerEntity playerIn, String jutsuName)
    {
        if (jutsuName != null) {
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (("jutsu." + jutsu.getCorrelatedPlugin().getPluginId() + "." + jutsu.getName()).equalsIgnoreCase(jutsuName)) {
                    if (jutsu.isToggle()) {
                        String nbtName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
                        if (playerIn.getPersistentData().getBoolean(nbtName) == false) {
                            playerIn.getPersistentData().putBoolean(nbtName, true);
                            playerIn.sendMessage(new StringTextComponent(new TranslationTextComponent("jutsu." + jutsu.getCorrelatedPlugin().getPluginId() + "." + jutsu.getName()).getString() + "!"));
                        } else {
                            playerIn.getPersistentData().putBoolean(nbtName, false);
                        }
                    }
                    else {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(jutsu.getName()));
                    }
                }
            }
/*            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".clone")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(CloneJutsu.BasicCloneJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".replacement")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(BodyReplacementJutsu.BasicBodyReplacementJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".invisibility")) {
                if (playerIn.getPersistentData().getBoolean("invisibilitytechnigue") == false) {
                    playerIn.getPersistentData().putBoolean("invisibilitytechnigue", true);
                    playerIn.sendMessage(new StringTextComponent("Invisibility Technique!"));
                } else {
                    playerIn.getPersistentData().putBoolean("invisibilitytechnigue", false);
                }
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".fireball")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.FireballJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".flyingstones")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.FlyingStonesJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".lightningball")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.LightningBallJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".galepalm")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.GalePalmJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".watershuriken")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.WaterShurikenJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".phoenixflower")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.PhoenixFlowerJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".mudmoat")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.MudMoatJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".stungun")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.StunGunJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".windexplosion")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.WindExplosionJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".ragingwaves")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.RagingWavesJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".moltenfist")) {
                if (playerIn.getPersistentData().getBoolean("moltenfisttechnigue") == false) {
                    playerIn.getPersistentData().putBoolean("moltenfisttechnigue", true);
                    playerIn.sendMessage(new StringTextComponent("Molten Fist Technique!"));
                } else {
                    playerIn.getPersistentData().putBoolean("moltenfisttechnigue", false);
                }
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".fistrock")) {
                if (playerIn.getPersistentData().getBoolean("fistrocktechnigue") == false) {
                    playerIn.getPersistentData().putBoolean("fistrocktechnigue", true);
                    playerIn.sendMessage(new StringTextComponent("Fist Rock Technique!"));
                } else {
                    playerIn.getPersistentData().putBoolean("fistrocktechnigue", false);
                }
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".lightningarrow")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.LightningArrowJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".windarrow")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.WindArrowJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".watersharkbullet")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(ShootingJutsu.WaterSharkBulletJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".transformation")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(TransformationJutsu.TransformationJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".amaterasu")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(SharinganJutsu.AmaterasuJutsuID, 1, 1));
            }
            if (jutsuName.equalsIgnoreCase("jutsu." + Main.MODID + ".tsukuyomi")) {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(SharinganJutsu.TsukuyomiJutsuID, 1, 1));
            }*/
        }
    }
}
