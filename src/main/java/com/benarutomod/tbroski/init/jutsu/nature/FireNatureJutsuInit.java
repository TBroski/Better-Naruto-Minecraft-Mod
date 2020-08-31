package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.PhoenixFlowerEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.BijuuHelper;
import net.minecraft.item.ItemStack;

public class FireNatureJutsuInit {

    public static void registerFireJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "fireball", BeNMJutsu.Type.FIRE_NATURE, 4, 50F, 16, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                FireballEntity entity = new FireballEntity(playerIn.world, playerIn, BijuuHelper.hasAttribute(AbstractBijuuEntity.BijuuAttributes.BLUE_FIRE, playerIn) && playerCapability.returnBodyInfusionToggled());
                entity.setItem(new ItemStack(ItemInit.FIREBALL_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "phoenix_flower", BeNMJutsu.Type.FIRE_NATURE, 6, 35F, 16, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                PhoenixFlowerEntity entity = new PhoenixFlowerEntity(playerIn.world, playerIn, BijuuHelper.hasAttribute(AbstractBijuuEntity.BijuuAttributes.BLUE_FIRE, playerIn) && playerCapability.returnBodyInfusionToggled());
                entity.setItem(new ItemStack(ItemInit.PHOENIX_FLOWER_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "molten_fist", BeNMJutsu.Type.FIRE_NATURE, 4, 0.5F, 16, 32, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            // Marker
        }).addAttackEventListener((attacker, target) -> {
            target.setFire(2);
        }));
    }
}
