package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningBallEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

import java.util.Iterator;
import java.util.List;

public class LightningNatureJutsuInit {

    public static void registerLightningJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lightning_ball", BeNMJutsu.Type.LIGHTNING_NATURE, 4, 50F, 48, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            LightningBallEntity entity = new LightningBallEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.LIGHTNING_BALL_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "stun_gun", BeNMJutsu.Type.LIGHTNING_NATURE, 6, 40F, 48, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            List<LivingEntity> entities = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().grow(6));
            Iterator iterator = entities.iterator();
            while (iterator.hasNext())
            {
                LivingEntity entity = (LivingEntity) iterator.next();
                if (entity instanceof PlayerEntity) {
                    PlayerEntity playerEntity = (PlayerEntity) entity;
                    if (playerEntity != playerIn) {
                        entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1F);
                        playerEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 50, 1));
                    }
                }
                else {
                    entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1F);
                    entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 50, 1));
                }
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lightning_arrow", BeNMJutsu.Type.LIGHTNING_NATURE, 5, 40F, 48, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            LightningArrowEntity entity = new LightningArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.LIGHTNING_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }));
    }
}
