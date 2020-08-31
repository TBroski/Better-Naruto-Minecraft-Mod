package com.benarutomod.tbroski.items.nbt;

import com.benarutomod.tbroski.entity.ClayEntity;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.List;

public class ClaySpawnEggItemBase extends Item {
    public ClaySpawnEggItemBase() {
        super(new Item.Properties().maxStackSize(4));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World worldIn = context.getWorld();
        PlayerEntity playerIn = context.getPlayer();
        Hand handIn = context.getHand();
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        BlockPos pos = context.getPos();
        if (itemstack.hasTag() && itemstack.getTag().getString("affiliatedmob") != null) {
            for (EntityType<?> entityType : ForgeRegistries.ENTITIES.getValues()) {
                if (entityType.getRegistryName().toString().equalsIgnoreCase(itemstack.getTag().getString("affiliatedmob"))) {
                    ClayEntity entity = new ClayEntity(worldIn, entityType, playerIn.getUniqueID(), 0);
                    if (!playerIn.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                    }
                    if (playerIn.getRevengeTarget() != null) {
                        entity.setAttackTarget(playerIn.getRevengeTarget());
                        entity.setAttackTarget(playerIn.getLastAttackedEntity());
                    } else {
                        List<LivingEntity> mobs = entity.world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(20), null);
                        Iterator iterator = mobs.iterator();
                        while (iterator.hasNext()) {
                            LivingEntity livingEntity = (LivingEntity) iterator.next();
                            if (livingEntity != playerIn && livingEntity != entity) {
                                entity.setAttackTarget(livingEntity);
                                break;
                            }
                        }
                    }
                    worldIn.addEntity(entity);
                }
            }
            }
        return super.onItemUse(context);
    }
}
