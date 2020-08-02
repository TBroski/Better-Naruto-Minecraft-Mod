package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.FlyingStonesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.RagingWavesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterSharkBulletEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterShurikenEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class WaterNatureJutsuInit {

    public static void registerWaterJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "water_shuriken", BeNMJutsu.Type.WATER_NATURE, 4, 20F, 32, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WaterShurikenEntity entity = new WaterShurikenEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WATER_SHURIKEN_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.75F, 0.5F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setWaterShurikenJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasWaterShurikenJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setWaterShurikenJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasWaterShurikenJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "raging_waves", BeNMJutsu.Type.WATER_NATURE, 6, 45F, 32, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            RagingWavesEntity entity = new RagingWavesEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.RAGING_WAVES_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setRagingWavesJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasRagingWavesJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setRagingWavesJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasRagingWavesJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "water_shark_bullet", BeNMJutsu.Type.WATER_NATURE, 8, 100F, 32, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WaterSharkBulletEntity entity = new WaterSharkBulletEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WATER_SHARK_BULLET_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setWaterSharkBulletJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasWaterSharkBulletJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setWaterSharkBulletJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasWaterSharkBulletJutsuBoolean()));
    }
}
