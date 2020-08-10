package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.GalePalmEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindExplosionEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class WindNatureJutsuInit {

    public static void registerWindJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "gale_palm", BeNMJutsu.Type.WIND_NATURE, 4, 20F, 80, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            GalePalmEntity entity = new GalePalmEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.GALE_PALM_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 0.1F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setGalePalmJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasGalePalmJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setGalePalmJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasGalePalmJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wind_explosion", BeNMJutsu.Type.WIND_NATURE, 6, 80F, 80, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WindExplosionEntity entity = new WindExplosionEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_EXPLOSION_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setWindExplosionJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasWindExplosionJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setWindExplosionJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasWindExplosionJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wind_arrow", BeNMJutsu.Type.WIND_NATURE, 5, 40F, 80, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WindArrowEntity entity = new WindArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setWindArrowJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasWindArrowJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setWindArrowJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasWindArrowJutsuBoolean()));
    }
}
