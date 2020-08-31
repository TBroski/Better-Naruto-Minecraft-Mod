package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.GalePalmEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindExplosionEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.item.ItemStack;

public class WindNatureJutsuInit {

    public static void registerWindJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "gale_palm", BeNMJutsu.Type.WIND_NATURE, 4, 20F, 80, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            GalePalmEntity entity = new GalePalmEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.GALE_PALM_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 0.1F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wind_explosion", BeNMJutsu.Type.WIND_NATURE, 6, 80F, 80, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WindExplosionEntity entity = new WindExplosionEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_EXPLOSION_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "wind_arrow", BeNMJutsu.Type.WIND_NATURE, 5, 40F, 80, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            WindArrowEntity entity = new WindArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }));
    }
}
