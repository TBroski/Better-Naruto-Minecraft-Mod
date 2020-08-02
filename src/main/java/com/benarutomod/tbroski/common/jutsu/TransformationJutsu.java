package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TransformationJutsu {
    public static final int chakraAmount = 10;

    // Has a lot of bad code, needs lots of work!
    public static final int TransformationJutsuID = 23;
    public static void TransormationJutsu(PlayerEntity playerIn, int chakraAmount) {
            LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                Vec3d vec3d = playerIn.getEyePosition(1.0F);
                Vec3d vec3d1 = playerIn.getLook(1.0F);
                Vec3d vec3d2 = vec3d.add(vec3d1.x * 4, vec3d1.y * 4, vec3d1.z * 4);
                AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(4)).grow(1.0D, 1.0D, 1.0D);
                EntityRayTraceResult rayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 4);
                if (rayTraceResult != null) {
                        Entity entity = rayTraceResult.getEntity();
                        if (entity instanceof LivingEntity && (!(entity instanceof PlayerEntity))) {
                            LivingEntity livingEntity = (LivingEntity) entity;
                            if (playerIn.getPersistentData().getBoolean("mobcontrol") == false) {
                                if (!playerIn.world.isRemote) livingEntity.getType().spawn(playerIn.world, null, null, livingEntity.getPosition(), SpawnReason.MOB_SUMMONED, true, false);
                                playerIn.getPersistentData().putBoolean("mobcontrol", true);
                                Minecraft.getInstance().player.getPersistentData().putBoolean("mobcontrol", true);
                                playerIn.getPersistentData().putInt("controlledmobid", livingEntity.getEntityId());
                                Minecraft.getInstance().player.getPersistentData().putInt("controlledmobid", livingEntity.getEntityId());
                            } else {
                                if (playerIn.getPersistentData().getInt("controlledmobid") != -1 && playerIn.world.getEntityByID(playerIn.getPersistentData().getInt("controlledmobid")) != null) {
                                    if (!playerIn.world.isRemote) playerIn.world.getEntityByID(playerIn.getPersistentData().getInt("controlledmobid")).remove();
                                    playerIn.getPersistentData().putInt("controlledmobid", -1);
                                    Minecraft.getInstance().player.getPersistentData().putInt("controlledmobid", -1);
                                    playerIn.getPersistentData().putBoolean("mobcontrol", false);
                                    Minecraft.getInstance().player.getPersistentData().putBoolean("mobcontrol", false);
                                }
                            }
                            return;
                        }
                    }
                    if (playerIn.getPersistentData().getInt("controlledmobid") != -1 && playerIn.world.getEntityByID(playerIn.getPersistentData().getInt("controlledmobid")) != null) {
                        if (!playerIn.world.isRemote) playerIn.world.getEntityByID(playerIn.getPersistentData().getInt("controlledmobid")).remove();
                    }
                    playerIn.getPersistentData().putBoolean("mobcontrol", false);
                    Minecraft.getInstance().player.getPersistentData().putBoolean("mobcontrol", false);
                    playerIn.getPersistentData().putInt("controlledmobid", -1);
                    Minecraft.getInstance().player.getPersistentData().putInt("controlledmobid", -1);
            }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event)
    {
        if (event.player.getPersistentData().getBoolean("mobcontrol") == true)
        {
            event.player.getPersistentData().putInt("transformationtick", event.player.getPersistentData().getInt("transformationtick"));
            if (!event.player.getEntityWorld().isRemote) {
                LazyOptional<IPlayerHandler> capabilities = event.player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
                if (event.player.getPersistentData().getInt("transformationtick") >= 20) {
                    event.player.getPersistentData().putInt("transformationtick", 0);
                    if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
                        playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                    }
                    else {
                        LivingEntity entity = (LivingEntity) event.player.world.getEntityByID(event.player.getPersistentData().getInt("controlledmobid"));
                        if (entity != null)
                        {
                            entity.getType().spawn(event.player.world, null, null, event.player.getPosition(), SpawnReason.MOB_SUMMONED, true, false);
                            entity.remove();
                        }
                        event.player.getPersistentData().putBoolean("mobcontrol", false);
                        Minecraft.getInstance().player.getPersistentData().putBoolean("mobcontrol", false);
                        event.player.getPersistentData().putInt("controlledmobid", -1);
                        Minecraft.getInstance().player.getPersistentData().putInt("controlledmobid", -1);
                        if (!event.player.isPotionActive(Effects.INVISIBILITY)) {
                            event.player.setInvisible(false);
                        }
                    }
                }
            }
            int entityid = event.player.getPersistentData().getInt("controlledmobid");
            LivingEntity entity = (LivingEntity) event.player.world.getEntityByID(entityid);
            if (entity != null && entity instanceof MobEntity) {
                MobEntity mobEntity = (MobEntity) entity;
                mobEntity.setNoAI(true);
                mobEntity.getPersistentData().putBoolean("iscontrolled", true);
                mobEntity.getPersistentData().putInt("affiliatedplayer", event.player.getEntityId());
                mobEntity.setAttackTarget(null);
                mobEntity.setAggroed(false);
                event.player.setInvisible(true);
                //mobEntity.getCollisionBoundingBox().shrink(1);
                Minecraft.getInstance().gameSettings.thirdPersonView = 1;
                if (!event.player.world.isRemote()) {
                    //mobEntity.setHeadRotation(event.player.cameraYaw, (int) event.player.rotationPitch);
                    mobEntity.setPositionAndRotation(event.player.getPosX(), event.player.getPosY(), event.player.getPosZ(), event.player.rotationYaw, event.player.rotationPitch);
                    //mobEntity.setPositionAndUpdate(event.player.getPosX(), event.player.getPosY(), event.player.getPosZ());
                }
                else {
                    mobEntity.setRotationYawHead(event.player.rotationYawHead);
                    //mobEntity.setHeadRotation(event.player.cameraYaw, (int) event.player.rotationPitch);
                    mobEntity.setPositionAndRotation(event.player.getPosX(), event.player.getPosY(), event.player.getPosZ(), event.player.rotationYaw, event.player.rotationPitch);
                    //mobEntity.setPositionAndUpdate(event.player.getPosX(), event.player.getPosY(), event.player.getPosZ());
                }
            }
            else {
                if (!event.player.world.isRemote) {
                    if (!event.player.isPotionActive(Effects.INVISIBILITY)) {
                        event.player.setInvisible(false);
                    }
                }
                event.player.getPersistentData().putInt("controlledmobid", -1);
                event.player.getPersistentData().putBoolean("mobcontrol", false);
            }
        }
        else if (!event.player.world.isRemote) {
            if (!event.player.isPotionActive(Effects.INVISIBILITY)) {
                event.player.setInvisible(false);
            }
        }
    }

    @SubscribeEvent
    public void damage(LivingDamageEvent event)
    {
        if (event.getEntityLiving().getPersistentData().getBoolean("iscontrolled") == true && event.getSource().damageType.equalsIgnoreCase("inWall"))
        {
            event.setCanceled(true);
        }
        else if (event.getEntityLiving().getPersistentData().getBoolean("iscontrolled") == true)
        {
            event.setCanceled(true);
            PlayerEntity playerAffiliated = (PlayerEntity) event.getEntityLiving().getEntityWorld().getEntityByID(event.getEntityLiving().getPersistentData().getInt("affiliatedplayer"));
            playerAffiliated.attackEntityFrom(event.getSource(), event.getAmount());
        }
    }

    @SubscribeEvent
    public void renderPlayerPre(RenderPlayerEvent.Pre event)
    {
        if (event.getPlayer().getPersistentData().getBoolean("mobcontrol") == true)
        {
            event.setCanceled(true);
        }
    }


    @SubscribeEvent
    public void attackTargetEvent(LivingSetAttackTargetEvent event) {
        if (event.getTarget() instanceof PlayerEntity && event.getEntity() instanceof MobEntity)
        {
            PlayerEntity player = (PlayerEntity) event.getTarget();
            LivingEntity playerAffiliated = (LivingEntity) event.getTarget().world.getEntityByID(player.getPersistentData().getInt("controlledmobid"));
            if (playerAffiliated != null) {
                if (((MobEntity) event.getEntity()).getRevengeTarget() != player) {
                    ((MobEntity) event.getEntity()).setAttackTarget(null);
                    ((MobEntity) event.getEntity()).setAggroed(false);
                }
            }
        }
    }

    @SubscribeEvent
    public void entityJoin(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof PlayerEntity)
        {
            event.getEntity().getPersistentData().putInt("controlledmobid", -1);
            event.getEntity().getPersistentData().putBoolean("mobcontrol", false);
        }
    }

    @SubscribeEvent
    public void playerLeave(PlayerEvent.PlayerLoggedOutEvent event)
    {
        if (event.getPlayer().getPersistentData().getInt("controlledmobid") != -1)
        {
            EntityType type = event.getPlayer().world.getEntityByID(event.getPlayer().getPersistentData().getInt("controlledmobid")).getType();
            type.spawn(event.getPlayer().world, null, null, event.getPlayer().getPosition(), SpawnReason.MOB_SUMMONED, true, false);
            event.getPlayer().world.getEntityByID(event.getPlayer().getPersistentData().getInt("controlledmobid")).remove();
        }
    }
}
