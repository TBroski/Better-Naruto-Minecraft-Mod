package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.TailedBeastBombEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.item.ItemStack;

public class BijuuJutsuInit {

    public static void registerBijuuJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tailed_beast_bomb", BeNMJutsu.Type.BIJUU_ABILITY, 20, 285F, 448, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            TailedBeastBombEntity entity = new TailedBeastBombEntity(playerIn, playerIn.world);
            entity.setItem(new ItemStack(ItemInit.TAILED_BEAST_BOMB.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> playerIn.getPersistentData().getBoolean(Main.MODID + "_tailed_beast_transformation")));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tailed_beast_transformation", BeNMJutsu.Type.BIJUU_ABILITY, 24, 0.3F, 448, 16, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (playerIn.world.isRemote) {
                playerIn.setInvisible(true);
                playerIn.eyeHeight = 13.0F;
            }
        }).addCancelEventListener(playerIn -> {
            if (playerIn.world.isRemote) {
                playerIn.setInvisible(false);
                playerIn.eyeHeight = 1.62F;
            }
        }));
    }
}
