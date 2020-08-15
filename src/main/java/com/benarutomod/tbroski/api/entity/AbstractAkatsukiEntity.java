package com.benarutomod.tbroski.api.entity;

import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class AbstractAkatsukiEntity extends AbstractShinobiEntity {

    public abstract ItemStack getMainHandItemStack();
    protected AbstractAkatsukiEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        CompoundNBT nbt = new CompoundNBT();
        ItemStack headband = new ItemStack(ItemInit.HEADBAND_HELMET.get());
        headband.setTag(nbt);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, this.getMainHandItemStack());
        this.setItemStackToSlot(EquipmentSlotType.HEAD, headband);
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ItemInit.AKATSUKI_CHESTPLATE.get()));
    }
}
