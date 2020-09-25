package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.benarutomod.tbroski.entity.projectile.jutsu.storm.BlackHuntingEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.storm.DemonDragonStormEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.storm.LaserCircusEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;

import java.util.List;

public class StormNatureJutsuInit {

    public static void registerStormJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "black_hunting", BeNMJutsu.Type.STORM_NATURE, 10, 170F, 176, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BlackHuntingEntity entity = new BlackHuntingEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.BLACK_HUNTING_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "thunder_cloud_inner_wave", BeNMJutsu.Type.STORM_NATURE, 20, 2F, 176, 16, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                List<LivingEntity> entityList = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().grow(5));
                for (LivingEntity entity : entityList) {
                    if (entity != playerIn && entity.getRNG().nextInt(2100) == 0) {
                        LightningBoltEntity bolt = new LightningBoltEntity(playerIn.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), false);
                        playerIn.world.addEntity(bolt);
                    }
                }
            }
            else {
                Particles.addParticles(playerIn, ParticleTypes.LARGE_SMOKE, 5, 20);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "laser_circus", BeNMJutsu.Type.STORM_NATURE, 14, 0.5F, 176, 32, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                LaserCircusEntity entity = new LaserCircusEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.LASER_CIRCUS_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 4F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "demon_dragon_storm", BeNMJutsu.Type.STORM_NATURE, 10, 300F, 176, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                DemonDragonStormEntity entity = new DemonDragonStormEntity(playerIn, playerIn.world);
                entity.setItem(ItemStack.EMPTY);
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.5F, 6F);
                playerIn.world.addEntity(entity);
            }
        }));
    }
}
