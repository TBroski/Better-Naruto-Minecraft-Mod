package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.PhoenixFlowerEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class FireNatureJutsuInit {

    public static void registerFireJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "fireball", BeNMJutsu.Type.FIRE_NATURE, 4, 50F, 16, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            FireballEntity entity = new FireballEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.FIREBALL_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setFireballJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasFireballJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setFireballJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasFireballJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "phoenix_flower", BeNMJutsu.Type.FIRE_NATURE, 6, 35F, 16, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            PhoenixFlowerEntity entity = new PhoenixFlowerEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.PHOENIX_FLOWER_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setPhoenixFlowerJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasPhoenixFlowerJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setFireballJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasPhoenixFlowerJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "molten_fist", BeNMJutsu.Type.FIRE_NATURE, 4, 0.5F, 16, 32, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            //Suspected Syncing Issue, needs to be fixed before release
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setMoltenFistJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasMoltenFistJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setFireballJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasMoltenFistJutsuBoolean()));
    }
}
