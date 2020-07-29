package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.List;

public class SummoningJutsu {

    public static final int BasicSummoningJutsuID = 6;
    public static void BasicSummoningJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> player_cap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerHandler = player_cap.orElse(new PlayerCapability());
        if (chakraAmount < playerHandler.returnChakra()) {
            if (!playerHandler.returnPlayerEntityAffiliation().equalsIgnoreCase("")) {
                playerHandler.addChakra(-chakraAmount * (playerHandler.returnChakraControl() - 100));
                for (EntityType<?> entityType : ForgeRegistries.ENTITIES.getValues()) {
                    if (entityType.getRegistryName().toString().equalsIgnoreCase(playerHandler.returnPlayerEntityAffiliation())) {
                        Entity entity = entityType.spawn(playerIn.world, null, null, playerIn.getPosition(), SpawnReason.MOB_SUMMONED, true, false);
                        entity.getPersistentData().putBoolean("benmsummoned", true);
                        entity.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ItemInit.HEADBAND_HELMET.get()));
                        if (playerIn.getRevengeTarget() != null && entity instanceof MobEntity) {
                            ((MobEntity) entity).setAttackTarget(playerIn.getRevengeTarget());
                        } else if (playerIn.getLastAttackedEntity() != null) {
                            ((MobEntity) entity).setAttackTarget(playerIn.getLastAttackedEntity());
                        } else {
                            List<LivingEntity> mobs = entity.world.getEntitiesWithinAABB(LivingEntity.class, entity.getBoundingBox().grow(20), null);
                            Iterator iterator = mobs.iterator();
                            while (iterator.hasNext()) {
                                LivingEntity livingEntity = (LivingEntity) iterator.next();
                                if (livingEntity != playerIn && livingEntity != entity) {
                                    ((MobEntity) entity).setAttackTarget(livingEntity);
                                    break;
                                }
                            }
                        }
                        if (!playerHandler.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Summoning Jutsu! " + (-chakraAmount * ((100 - playerHandler.returnChakraControl()) * 0.01)) + " Chakra"));
                    }
                }
            }
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
}
