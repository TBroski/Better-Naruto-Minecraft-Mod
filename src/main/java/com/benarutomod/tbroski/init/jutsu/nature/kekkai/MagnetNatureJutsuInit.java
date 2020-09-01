package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.magnet.BlackIronFistEntity;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.BijuuHelper;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.*;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class MagnetNatureJutsuInit {

    public static void registerMagnetJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "matter_repulsion", BeNMJutsu.Type.MAGNET_NATURE, 2, 15F, 96, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                Vec3d vec = playerIn.getPositionVector();
                Vec3d vec3 = new Vec3d(vec.x, vec.y + playerIn.getEyeHeight(), vec.z);
                Vec3d vec3a = playerIn.getLook(1.0F);
                Vec3d vec3b = vec3.add(vec3a.getX() * 5, vec3a.getY() * 5, vec3a.getZ() * 5);
                BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, playerIn));
                if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) && playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlockHardness(playerIn.world, blockRayTraceResult.getPos()) <= 0.5F) {
                    BlockPos pos = blockRayTraceResult.getPos();
                    FallingBlockEntity blockEntity = new FallingBlockEntity(playerIn.world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, playerIn.world.getBlockState(pos));
                    float yaw = playerIn.rotationYaw;
                    float pitch = 0;
                    float velocity = 1.0F;
                    double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                    blockEntity.setMotion(motionX, motionY + 0.6D, motionZ);
                    playerIn.world.addEntity(blockEntity);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            Vec3d vec = playerIn.getPositionVector();
            Vec3d vec3 = new Vec3d(vec.x,vec.y + playerIn.getEyeHeight(),vec.z);
            Vec3d vec3a = playerIn.getLook(1.0F);
            Vec3d vec3b = vec3.add(vec3a.getX() * 5, vec3a.getY() *  5, vec3a.getZ() *  5);
            BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
            if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) && playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlockHardness(playerIn.world, blockRayTraceResult.getPos()) <= 0.5F) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "self_levitation", BeNMJutsu.Type.MAGNET_NATURE, 11, 1.5F, 96, 16, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            playerIn.abilities.allowFlying = true;
            playerIn.abilities.isFlying = true;
            playerIn.sendPlayerAbilities();
            if (playerIn.world.isRemote)
                playerIn.world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.SAND.getDefaultState()), playerIn.getPosX() + new Random().nextDouble(), playerIn.getPosY() - 1, playerIn.getPosZ() + new Random().nextDouble(), 0.2, 0.2, 0.2);
        }).addCancelEventListener((playerIn) -> {
            if (!playerIn.abilities.isCreativeMode) {
                playerIn.abilities.isFlying = false;
                playerIn.abilities.allowFlying = false;
                playerIn.sendPlayerAbilities();
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "lightning_god_self", BeNMJutsu.Type.MAGNET_NATURE, 10, 0.5F, 96, 32, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (playerIn.world.isRemote)
                playerIn.eyeHeight = 5.0F;
        }).addCancelEventListener(playerIn -> {
            playerIn.eyeHeight = 1.62F;
        }).addDamageEventListener((amount, source, defender) -> {
            defender.attackEntityFrom(source, Math.min(amount - 2F, 0.0F));
            return true;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "black_iron_fist", BeNMJutsu.Type.MAGNET_NATURE, 6, 125F, 96, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BlackIronFistEntity entity = new BlackIronFistEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.BLACK_IRON_FIST_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.8F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "iron_sand_wave", BeNMJutsu.Type.MAGNET_NATURE, 2, 0.1F, 96, 64, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                FallingBlockEntity blockEntity = new FallingBlockEntity(playerIn.world, playerIn.getPosX(), playerIn.getPosY() + 2, playerIn.getPosZ(), BlockInit.IRON_SAND.get().getDefaultState());
                float yaw = playerIn.rotationYaw;
                float pitch = 0;
                float velocity = 1.0F;
                double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                blockEntity.setMotion(motionX, motionY, motionZ);
                playerIn.world.addEntity(blockEntity);
            }
        }));
    }
}
