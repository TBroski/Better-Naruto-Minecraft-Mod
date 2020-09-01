package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.boil.BurningAquaGunEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.boil.CorrosiveArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.magnet.BlackIronFistEntity;
import com.benarutomod.tbroski.init.EffectInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.BijuuHelper;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;

import java.util.List;

public class BoilNatureJutsuInit {

    public static void registerBoilJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "acid_rain", BeNMJutsu.Type.BOIL_NATURE, 10, 1.0F, 144, 0, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
            if (blockRayTraceResult != null) {
                if (!playerIn.world.isRemote) {
                    BurningAquaGunEntity entity = new BurningAquaGunEntity(playerIn, playerIn.world);
                    entity.setItem(new ItemStack(ItemInit.BURNING_AQUA_GUN_JUTSU.get()));
                    if (playerIn.getRNG().nextBoolean()) {
                        entity.setPosition(blockRayTraceResult.getPos().getX() + playerIn.getRNG().nextInt(5), blockRayTraceResult.getPos().getY() + 10 + taijutsuModifier0, blockRayTraceResult.getPos().getZ() + playerIn.getRNG().nextInt(5));
                    }
                    else {
                        entity.setPosition(blockRayTraceResult.getPos().getX() - playerIn.getRNG().nextInt(5), blockRayTraceResult.getPos().getY() + 10 + taijutsuModifier0, blockRayTraceResult.getPos().getZ() - playerIn.getRNG().nextInt(5));
                    }
                    entity.setMotion(0, -1.1, 0);
                    playerIn.world.addEntity(entity);
                }
                if (playerIn.world.isRemote) {
                    Particles.addParticles(blockRayTraceResult.getPos().up(10 + taijutsuModifier0), playerIn.world, ParticleTypes.LARGE_SMOKE, 5, 10);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
            if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos())) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "burning_aqua_gun", BeNMJutsu.Type.BOIL_NATURE, 4, 20F, 144, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BurningAquaGunEntity entity = new BurningAquaGunEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.BURNING_AQUA_GUN_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "corrosive_arrow", BeNMJutsu.Type.BOIL_NATURE, 6, 50F, 144, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                CorrosiveArrowEntity entity = new CorrosiveArrowEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.CORROSIVE_ARROW_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "boiling_mist_geyser", BeNMJutsu.Type.BOIL_NATURE, 12, 0.8F, 144, 48, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
            if (blockRayTraceResult != null) {
                if (!playerIn.world.isRemote) {
                    List<LivingEntity> entityList = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, new AxisAlignedBB(blockRayTraceResult.getPos().south(3).east(3), blockRayTraceResult.getPos().north(3).west(3)));
                    for (LivingEntity entity : entityList) {
                        entity.addPotionEffect(new EffectInstance(EffectInit.EXHAUSTION.get(), 200));
                    }
                }
                if (playerIn.world.isRemote) {
                    Particles.addParticles(blockRayTraceResult.getPos().up(3), playerIn.world, new BlockParticleData(ParticleTypes.BLOCK, Blocks.SLIME_BLOCK.getDefaultState()), 3, 5);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
            if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos())) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "boiling_hands", BeNMJutsu.Type.BOIL_NATURE, 9, 0.7F, 144, 64, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            // Marker
        }).addAttackEventListener((attacker, target) -> {
            target.addPotionEffect(new EffectInstance(EffectInit.EXHAUSTION.get(), 40));
        }));
    }
}
