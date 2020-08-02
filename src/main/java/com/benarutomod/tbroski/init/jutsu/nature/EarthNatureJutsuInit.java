package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.FlyingStonesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.MudMoatEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class EarthNatureJutsuInit {

    public static void registerEarthJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "flying_stones", BeNMJutsu.Type.EARTH_NATURE, 4, 55F, 64, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            FlyingStonesEntity entity = new FlyingStonesEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.FLYING_STONES_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.1F, 1.2F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setFlyingStonesJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasFlyingStonesJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setFlyingStonesJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasFlyingStonesJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "mud_moat", BeNMJutsu.Type.EARTH_NATURE, 6, 60F, 64, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            MudMoatEntity entity = new MudMoatEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.MUD_MOAT_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setMudMoatJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasMudMoatJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setMudMoatJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasMudMoatJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "fist_rock", BeNMJutsu.Type.EARTH_NATURE, 4, 0.5F, 64, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            //Calls NBT in ForgeEventSubscriber::livingDamage
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setFistRockJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasFistRockJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setFistRockJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasFistRockJutsuBoolean()));
    }
}
