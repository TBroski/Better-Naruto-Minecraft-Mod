package com.benarutomod.tbroski.client.renderer.misc;

import com.benarutomod.tbroski.api.entity.jutsu.AbstractJutsuEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Particles {

    public static void addParticles(LivingEntity entity, IParticleData particleType, int spread, int loopAmount)
    {
        if (!entity.world.isRemote) {
            System.out.println("Can't spawn particles server-side.");
            return;
        }
        for (int i = 0; i < loopAmount; i++) {
            if (Minecraft.getInstance().world != null) {
                if (entity.getRNG().nextBoolean()) {
                    Minecraft.getInstance().world.addParticle(particleType, entity.getPosX() - entity.getRNG().nextInt(spread), entity.getPosY() - entity.getRNG().nextInt(spread), entity.getPosZ() - entity.getRNG().nextInt(spread), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10));
                }
                else {
                    Minecraft.getInstance().world.addParticle(particleType, entity.getPosX() + entity.getRNG().nextInt(spread), entity.getPosY() + entity.getRNG().nextInt(spread), entity.getPosZ() + entity.getRNG().nextInt(spread), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10));
                }
            }
        }
    }

    public static void addParticles(AbstractJutsuEntity entity, IParticleData particleType, int spread, int loopAmount)
    {
        if (!entity.world.isRemote) {
            System.out.println("Can't spawn particles server-side.");
            return;
        }
        for (int i = 0; i < loopAmount; i++) {
            if (Minecraft.getInstance().world != null) {
                if (entity.getRNG().nextBoolean()) {
                    Minecraft.getInstance().world.addParticle(particleType, entity.getPosX() - entity.getRNG().nextInt(spread), entity.getPosY() - entity.getRNG().nextInt(spread), entity.getPosZ() - entity.getRNG().nextInt(spread), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10));
                }
                else {
                    Minecraft.getInstance().world.addParticle(particleType, entity.getPosX() + entity.getRNG().nextInt(spread), entity.getPosY() + entity.getRNG().nextInt(spread), entity.getPosZ() + entity.getRNG().nextInt(spread), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10), 0.1 + (entity.getRNG().nextInt(spread) / 10));
                }
            }
        }
    }

    public static void addParticles(BlockPos blockPosIn, World worldIn, IParticleData particleType, int spread, int loopAmount)
    {
        if (!worldIn.isRemote) {
            System.out.println("Can't spawn particles server-side.");
            return;
        }
        if (worldIn != null) {
            for (int i = 0; i < loopAmount; i++) {
                if (worldIn.getRandom().nextBoolean()) {
                    Minecraft.getInstance().world.addParticle(particleType, blockPosIn.getX() - worldIn.getRandom().nextInt(spread), blockPosIn.getY() - worldIn.getRandom().nextInt(spread), blockPosIn.getZ() - worldIn.getRandom().nextInt(spread), 0.1 + (worldIn.getRandom().nextInt(spread) / 10), 0.1 + (worldIn.getRandom().nextInt(spread) / 10), 0.1 + (worldIn.getRandom().nextInt(spread) / 10));
                }
                else {
                    Minecraft.getInstance().world.addParticle(particleType, blockPosIn.getX() + worldIn.getRandom().nextInt(spread), blockPosIn.getY() + worldIn.getRandom().nextInt(spread), blockPosIn.getZ() + worldIn.getRandom().nextInt(spread), 0.1 + (worldIn.getRandom().nextInt(spread) / 10), 0.1 + (worldIn.getRandom().nextInt(spread) / 10), 0.1 + (worldIn.getRandom().nextInt(spread) / 10));
                }
            }
        }
    }
}
