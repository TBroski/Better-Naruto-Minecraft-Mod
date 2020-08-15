package com.benarutomod.tbroski.api.entity;

import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class AbstractAnbuEntity extends AbstractShinobiEntity {

    protected AbstractAnbuEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.KUNAI.get()));
        this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ItemInit.ANBU_HELMET.get()));
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ItemInit.ANBU_CHESTPLATE.get()));
    }
}
