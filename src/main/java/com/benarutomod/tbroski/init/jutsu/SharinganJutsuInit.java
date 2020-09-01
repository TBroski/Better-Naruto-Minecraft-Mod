package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.item.EtherealItem;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.entity.jutsu.AbstractNinjutsuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.sharingan.AmaterasuJutsuEntity;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.LazyOptional;

import java.util.List;

public class SharinganJutsuInit {

    public static void registerSharinganJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "amaterasu", BeNMJutsu.Type.SHARINGAN_ABILITY, 15, 300F, 496, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote)
                checkForBlindess(playerIn, playerCapability, 6);
            AmaterasuJutsuEntity entity = new AmaterasuJutsuEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(Items.AIR));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "tsukuyomi", BeNMJutsu.Type.SHARINGAN_ABILITY, 8, 200F, 496, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote)
                checkForBlindess(playerIn, playerCapability, 6);
            if (!playerIn.world.isRemote) {
                Vec3d vec3d = playerIn.getEyePosition(1.0F);
                Vec3d vec3d1 = playerIn.getLook(1.0F);
                Vec3d vec3d2 = vec3d.add(vec3d1.x * 10, vec3d1.y * 10, vec3d1.z * 10);
                AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(10)).grow(1.0D, 1.0D, 1.0D);
                EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 10);
                if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof LivingEntity) {
                    int x = ((ServerWorld) playerIn.world).getPlayers().indexOf(playerIn) * 100;
                    int z = ((ServerWorld) playerIn.world).getPlayers().indexOf(playerIn) * 100;
                    LivingEntity entity = (LivingEntity) entityRayTraceResult.getEntity();
                    playerIn.addPotionEffect(new EffectInstance(EffectInit.TSUKUYOMI.get(), 400));
                    entity.addPotionEffect(new EffectInstance(EffectInit.TSUKUYOMI.get(), 400));
                    playerIn.setPositionAndUpdate(x, 2, z);
                    entity.setPosition(x - 5, 2, z);
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            if (!playerIn.world.isRemote) {
                Vec3d vec3d = playerIn.getEyePosition(1.0F);
                Vec3d vec3d1 = playerIn.getLook(1.0F);
                Vec3d vec3d2 = vec3d.add(vec3d1.x * 10, vec3d1.y * 10, vec3d1.z * 10);
                AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(10)).grow(1.0D, 1.0D, 1.0D);
                EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 10);

                if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof PlayerEntity) {
                    LazyOptional<IPlayerHandler> targetc = entityRayTraceResult.getEntity().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler target_cap = targetc.orElse(new PlayerCapability());
                    LazyOptional<IPlayerHandler> playerc = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                    IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

                    if (target_cap.returnGenjutsu() > player_cap.returnGenjutsu()) {
                        return false;
                    }
                }
                if (entityRayTraceResult != null) {
                    return true;
                }
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "susanoo", BeNMJutsu.Type.SHARINGAN_ABILITY, 24, 0.3F, 496, 32, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote)
                checkForBlindess(playerIn, playerCapability, 1500);
            int tick = playerIn.getPersistentData().getInt(Main.MODID + "_susanoo_tick");
            playerIn.getPersistentData().putInt(Main.MODID + "_susanoo_tick", tick + 1);
            if (!playerIn.world.isRemote) {
                int susanoo = 1;
                if (tick > Config.COMMON.susanooStageIncrement.get()) {
                    susanoo = 2;
                }
                if (tick > Config.COMMON.susanooStageIncrement.get() * 2) {
                    susanoo = 3;
                }
                float addedDamage = 0.0F;
                float addedRange = 0.0F;
                if (playerCapability.getSusanooMainHand().getItem() instanceof EtherealItem) {
                    addedDamage += ((EtherealItem) playerCapability.getSusanooMainHand().getItem()).getProperties().getDamage();
                    addedRange += ((EtherealItem) playerCapability.getSusanooMainHand().getItem()).getProperties().getRange();
                }
                if (playerCapability.getSusanooOffHand().getItem() instanceof EtherealItem) {
                    addedDamage += ((EtherealItem) playerCapability.getSusanooOffHand().getItem()).getProperties().getDamage();
                    addedRange += ((EtherealItem) playerCapability.getSusanooOffHand().getItem()).getProperties().getRange();
                }
                List<LivingEntity> entities = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().grow((susanoo * 2) + addedRange));
                for (LivingEntity entity : entities) {
                    if (entity != playerIn && playerIn.getRNG().nextInt(5) == 0) {
                        float yaw = playerIn.rotationYaw;
                        float pitch = 0;
                        float velocity = 0.3F;
                        double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                        double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                        double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                        entity.setMotion(motionX, motionY, motionZ);
                        entity.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 0.5F + addedDamage);
                    }
                }
            }
            if (playerIn.world.isRemote) {
                playerIn.eyeHeight = 4.0F;
            }
        }).addCancelEventListener(playerIn -> {
            playerIn.getPersistentData().putInt(Main.MODID + "_susanoo_tick", 0);
            if (playerIn.world.isRemote) {
                playerIn.eyeHeight = 1.62F;
            }
        }).addDamageEventListener((amount, source, defender) -> {
            LazyOptional<IPlayerHandler> playerc = defender.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());
            boolean flag = false;
            float reducedDamage = amount;
            if (player_cap.getSusanooMainHand().getItem() instanceof EtherealItem) {
                reducedDamage -= ((EtherealItem) player_cap.getSusanooMainHand().getItem()).getProperties().getDamage();
            }
            if (player_cap.getSusanooOffHand().getItem() instanceof EtherealItem) {
                reducedDamage -= ((EtherealItem) player_cap.getSusanooOffHand().getItem()).getProperties().getDamage();
            }
            if (reducedDamage < amount) {
                Math.min(reducedDamage, 0.0F);
                flag = true;
                defender.attackEntityFrom(source, reducedDamage);
            }

            if (((player_cap.getSusanooMainHand().getItem() instanceof EtherealItem && ((EtherealItem) player_cap.getSusanooMainHand().getItem()).getProperties().doesCancelNinjutsu()) || (player_cap.getSusanooOffHand().getItem() instanceof EtherealItem && ((EtherealItem) player_cap.getSusanooOffHand().getItem()).getProperties().doesCancelNinjutsu())) && source.getImmediateSource() instanceof AbstractNinjutsuEntity) {
                flag = true;
            }
            return flag;
        }));
    }

    private static boolean checkForBlindess(PlayerEntity playerIn, IPlayerHandler playerCapability, int bound) {
        if (playerCapability.returnPlayerLeftDojutsu() == DojutsuInit.MANGEKYOU_SHARINGAN || playerCapability.returnPlayerRightDojutsu() == DojutsuInit.MANGEKYOU_SHARINGAN) {
            int rand = playerIn.getRNG().nextInt(bound);
            if (rand == 0) {
                playerIn.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 400, playerIn.getRNG().nextInt(2)));
                return true;
            }
        }
        return false;
    }
}
