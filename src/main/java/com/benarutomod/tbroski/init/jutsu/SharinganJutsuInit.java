package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.sharingan.AmaterasuJutsuEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class SharinganJutsuInit {

    public static void registerSharinganJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "amaterasu", BeNMJutsu.Type.SHARINGAN_ABILITY, 15, 300F, 240, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            AmaterasuJutsuEntity entity = new AmaterasuJutsuEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(Items.AIR));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setAmaterasuJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasAmaterasuJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setAmaterasuJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasAmaterasuJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tsukuyomi", BeNMJutsu.Type.SHARINGAN_ABILITY, 8, 200F, 240, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {

        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setTsukuyomiJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasTsukuyomiJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setTsukuyomiJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasTsukuyomiJutsuBoolean()));
    }
}
