package com.benarutomod.tbroski.event;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.benarutomod.tbroski.entity.ai.FindandReturnItemGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.api.entity.AbstractShinobiEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketEntityClaySync;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Iterator;
import java.util.List;

public class EntityEvents {

    public static void AddEntityAi(EntityJoinWorldEvent event)
    {
        MobEntity entity = (MobEntity) event.getEntity();
        if (!(entity instanceof AbstractShinobiEntity) && !(entity instanceof AbstractCloneEntity) && entity.isNonBoss()) {
            entity.goalSelector.addGoal(1, new FindandReturnItemGoal(entity, 3000));
        }
    }

    public static void OnAttackTarget(LivingSetAttackTargetEvent event) {
        Entity entityTarget = event.getTarget();
        Entity entity = event.getEntity();
        if (entityTarget instanceof PlayerEntity && !entity.world.isRemote)
        {
            PlayerEntity playerEntity = (PlayerEntity) entityTarget;
            LazyOptional<IPlayerHandler> player_cap = playerEntity.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerHandler = player_cap.orElse(new PlayerCapability());

            String playerEntityAffiliation = playerHandler.returnPlayerEntityAffiliation();
            if (entity.getPersistentData().getInt("affiliatedclayplayer") != 0) {
                LivingEntity affiliatedPlayer = (LivingEntity) event.getEntity().world.getEntityByID(entity.getPersistentData().getInt("affiliatedplayer"));

                if (entityTarget == affiliatedPlayer) {
                    if (affiliatedPlayer.getRevengeTarget() != null) {
                        ((MobEntity) entity).setAttackTarget(affiliatedPlayer.getRevengeTarget());
                    } else if (affiliatedPlayer.getLastAttackedEntity() != null) {
                        ((MobEntity) entity).setAttackTarget(affiliatedPlayer.getLastAttackedEntity());
                    } else {
                        List<LivingEntity> mobs = entity.world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(20), null);
                        Iterator iterator = mobs.iterator();
                        while (iterator.hasNext()) {
                            LivingEntity livingEntity = (LivingEntity) iterator.next();
                            if (livingEntity != affiliatedPlayer && livingEntity != entity && livingEntity.getType() != entity.getType() && livingEntity.getPersistentData().getInt("affiliatedplayer") != 0) {
                                ((MobEntity) entity).setAttackTarget(livingEntity);
                                break;
                            }
                            else {
                                ((MobEntity) entity).setAggroed(false);
                            }
                        }
                    }
                }
            }
            if (playerEntityAffiliation.equalsIgnoreCase(entity.getEntityString()) && entity.getPersistentData().getInt("affiliatedplayer") == 0)
            {
                //((MobEntity) entity).targetSelector.addGoal(3, new TargetHurtRevengeGoal((MobEntity) entity, playerEntity));
                ((MobEntity) entity).setAttackTarget(null);
                ((MobEntity) entity).setAggroed(false);
            }
        }
    }

    public static ItemStack getEntityFavoriteItem(MobEntity entity)
    {
        return new ItemStack(ItemInit.SCROLL.get());
    }
    public static ItemStack getEntityReturnItem(MobEntity entity)
    {
        ItemStack itemStack = new ItemStack(ItemInit.SCROLL.get());
        CompoundNBT nbt = new CompoundNBT();

        nbt.putString("entityaffiliation", entity.getEntityString());
        itemStack.setTag(nbt);
        itemStack.setDisplayName(new StringTextComponent("Summoning Contract: Signed by " + entity.getDisplayName().getString()));
        return itemStack;
    }

    public static void entityTick(LivingEvent.LivingUpdateEvent event)
    {
        if (event.getEntity().getPersistentData().getBoolean("benmsummoned") == true || event.getEntity().getPersistentData().getInt("affiliatedplayer") != 0)
        {
            event.getEntity().getPersistentData().putInt("benmsummonedtick", event.getEntity().getPersistentData().getInt("benmsummonedtick") + 1);
            if (event.getEntity().getPersistentData().getInt("benmsummonedtick") >= 600) {
                Particles.addParticles(event.getEntityLiving(), ParticleTypes.CLOUD, 1, 20);
                event.getEntity().world.createExplosion(event.getEntity(), event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), 5F, false, Explosion.Mode.DESTROY);
                event.getEntity().getPersistentData().putInt("benmsummonedtick", 0);
                event.getEntity().remove();
            }
        }
        if (event.getEntity().getPersistentData().getInt("affiliatedclayplayer") != 0)
        {
            if (!event.getEntity().world.isRemote) NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> event.getEntity()), new PacketEntityClaySync(event.getEntity().getPersistentData().getInt("affiliatedclayplayer"), event.getEntity().getEntityId()));
        }
    }
}
