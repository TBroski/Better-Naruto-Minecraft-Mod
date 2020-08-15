package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.TailedBeastBombEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class BijuuJutsuInit {

    public static void registerBijuuJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tailed_beast_bomb", BeNMJutsu.Type.BIJUU_ABILITY, 20, 300F, 192, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            TailedBeastBombEntity entity = new TailedBeastBombEntity(playerIn, playerIn.world);
            entity.setItem(new ItemStack(ItemInit.TAILED_BEAST_BOMB.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setTailedBeastBombBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasTailedBeastBombBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setTailedBeastBombBoolean(has);
        }, (playerCapability) -> playerCapability.hasTailedBeastBombBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tailed_beast_transformation", BeNMJutsu.Type.BIJUU_ABILITY, 24, 0.3F, 192, 16, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            // Called in events elsewhere.
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setTailedBeastTransformationBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasTailedBeastTransformationBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setTailedBeastTransformationBoolean(has);
        }, (playerCapability) -> playerCapability.hasTailedBeastTransformationBoolean()));
    }
}
