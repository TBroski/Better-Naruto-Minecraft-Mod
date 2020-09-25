package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.enums.Nature;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketNature;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClanHelper {

    private static List<BeNMClan> clansWithNPC = new ArrayList<>();
    private static Random rand = new Random();

    public static BeNMClan getClanFromString(String clan) {
        for (BeNMClan benmClan : BeNMRegistry.CLANS.getValues()) {
            if (benmClan.getString().equalsIgnoreCase(clan)) {
                return benmClan;
            }
        }
        return null;
    }

    public static Nature addNatureFromClan(ServerPlayerEntity player) {
        LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

        if (playercap.returnPlayerClan().getClanNature() == Nature.NULL) {
           int rClan = rand.nextInt(5);
           switch (rClan) {
               case 0:
                   playercap.setFireNature(true);
                   NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(1, true, true, true));
                   AdvancementHelper.grantAdvancement(player, Main.MODID + ":chakra/firenature");
                   return Nature.FIRE;
               case 1:
                   playercap.setWaterNature(true);
                   NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(2, true, true, true));
                   AdvancementHelper.grantAdvancement(player, Main.MODID + ":chakra/waternature");
                   return Nature.WATER;
               case 2:
                   playercap.setLightningNature(true);
                   NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(5, true, true, true));
                   AdvancementHelper.grantAdvancement(player, Main.MODID + ":chakra/lightningnature");
                   return Nature.LIGHTNING;
               case 3:
                   playercap.setWindNature(true);
                   NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(4, true, true, true));
                   AdvancementHelper.grantAdvancement(player, Main.MODID + ":chakra/windnature");
                   return Nature.WIND;
               case 4:
                   playercap.setEarthNature(true);
                   NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(3, true, true, true));
                   AdvancementHelper.grantAdvancement(player, Main.MODID + ":chakra/earthnature");
                   return Nature.EARTH;
           }
        }

        switch (playercap.returnPlayerClan().getClanNature()) {
            case FIRE:
                playercap.setFireNature(true);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(1, true, true, true));
                AdvancementHelper.grantAdvancement(player, Main.MODID + ":firenature");
                return Nature.FIRE;
            case WATER:
                playercap.setWaterNature(true);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(2, true, true, true));
                AdvancementHelper.grantAdvancement(player, Main.MODID + ":waternature");
                return Nature.WATER;
            case LIGHTNING:
                playercap.setLightningNature(true);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(5, true, true, true));
                AdvancementHelper.grantAdvancement(player, Main.MODID + ":lightningnature");
                return Nature.LIGHTNING;
            case WIND:
                playercap.setWindNature(true);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(4, true, true, true));
                AdvancementHelper.grantAdvancement(player, Main.MODID + ":windnature");
                return Nature.WIND;
            case EARTH:
                playercap.setEarthNature(true);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(3, true, true, true));
                AdvancementHelper.grantAdvancement(player, Main.MODID + ":earthnature");
                return Nature.EARTH;
        }

        return playercap.returnPlayerClan().getClanNature();
    }

    public static List<BeNMClan> getClansWithNPC() {
        return clansWithNPC;
    }

    public static void create() {
        for (BeNMClan clan : BeNMRegistry.CLANS.getValues()) {
            if (clan.hasNPC()) {
                clansWithNPC.add(clan);
            }
        }
    }
}
