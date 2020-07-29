package com.benarutomod.tbroski.client.renderer.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class Particles {

    public static void addParticles(LivingEntity entity, IParticleData particleType, int loopAmount)
    {
        for (int i = 0; i < loopAmount; i++) {
            if (Minecraft.getInstance().world != null) {
                Minecraft.getInstance().world.addParticle(particleType, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0.1, 0.1, 0.1);
            }
        }
    }

    public static void addParticles(BlockPos blockPosIn, World worldIn, IParticleData particleType, int loopAmount)
    {
        if (worldIn != null) {
            for (int i = 0; i < loopAmount; i++) {
                worldIn.addParticle(particleType, blockPosIn.getX() + (new Random().nextInt(5) * 0.1), blockPosIn.getY()+ (new Random().nextInt(5) * 0.1), blockPosIn.getZ()+ (new Random().nextInt(5) * 0.1), 0.1, 0.1, 0.1);
            }
        }
    }
}
