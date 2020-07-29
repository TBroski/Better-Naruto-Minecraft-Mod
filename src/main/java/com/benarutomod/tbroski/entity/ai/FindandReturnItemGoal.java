package com.benarutomod.tbroski.entity.ai;

import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.benarutomod.tbroski.event.EntityEvents;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;

public class FindandReturnItemGoal extends Goal {

    private ItemEntity itemEntity;
    private MobEntity entity;
    private ItemStack returnItem;
    private int cooldown;
    private int cooldownsave;

    public FindandReturnItemGoal(MobEntity entity, int cooldown) {
        this.entity = entity;
        this.cooldown = cooldown;
        this.cooldownsave = cooldown;
        this.returnItem = EntityEvents.getEntityReturnItem(entity);
        this.setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean shouldExecute() {
        this.findFavouriteItem();
        return this.itemEntity != null && this.itemEntity.isAlive() && !this.itemEntity.getItem().hasTag() && this.entity.getNavigator().getPathToEntity(this.itemEntity, 0) != null;
    }

    @Override
    public void tick() {
        this.cooldown += 1;
        PlayerEntity nearplayer = entity.world.getClosestPlayer(entity.getPosX(), entity.getPosY(), entity.getPosZ(), 10D, false);
        this.entity.getLookController().setLookPositionWithEntity(this.itemEntity, 10.0F, (float) this.entity.getVerticalFaceSpeed());
        //this.entity.getNavigator().clearPath();
        this.entity.getNavigator().tryMoveToEntityLiving(this.itemEntity, 0.7F);
        if (this.entity.getDistance(this.itemEntity) <= 2D && this.itemEntity.isAlive() && !itemEntity.getItem().hasTag()) {
            this.itemEntity.remove();
            if (this.cooldown >= this.cooldownsave && returnItem != null) {
                    this.cooldown = 0;
                    this.entity.entityDropItem(returnItem);
                    if (nearplayer != null) {
                        Particles.addParticles(entity, ParticleTypes.HEART, 20);
                        nearplayer.sendMessage(new StringTextComponent("<" + entity.getDisplayName().getString() +"> I think we can make a deal."));
                    }
            }
            else {
                if (nearplayer != null)
                {
                    nearplayer.sendMessage(new StringTextComponent("<" + entity.getDisplayName().getString() + "> I'm not interested with a puny human..."));
                }
            }
        }
    }

    @Override
    public boolean shouldContinueExecuting() {
        return this.itemEntity.isAlive() && this.entity.getNavigator().getPathToEntity(this.itemEntity, 0) != null;
    }

    @Nullable
    private void findFavouriteItem() {
        List<ItemEntity> items = this.entity.world.getEntitiesWithinAABB(ItemEntity.class, this.entity.getBoundingBox().grow(10), itemEntity -> itemEntity.getItem().getItem() == EntityEvents.getEntityFavoriteItem(entity).getItem().getItem());
        if (items.size() > 0) {
            this.itemEntity = items.stream().min(Comparator.comparing(this.entity::getDistance)).get();
        }
    }
}
