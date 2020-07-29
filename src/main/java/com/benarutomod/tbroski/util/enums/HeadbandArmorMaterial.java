package com.benarutomod.tbroski.util.enums;

import com.benarutomod.tbroski.Main;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum HeadbandArmorMaterial implements IArmorMaterial
{
    HEADBAND(Main.MODID + ":headband", 3, new int[] {5,5,5,3}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2F, () -> {
    return Ingredient.fromItems(Items.IRON_INGOT);
});

    private static final int[] MAX_DAMAGE_ARRAY = new int[] {5,5,5,5};
    private final String name;
    private final int maxDamageFactor;
    private final int[] damageReductionArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final LazyValue<Ingredient> repairMaterial;

    private HeadbandArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn)
    {
        this.name = nameIn;
        this.maxDamageFactor = maxDamageFactorIn;
        this.damageReductionArray = damageReductionArrayIn;
        this.enchantability = enchantabilityIn;
        this.soundEvent = soundEventIn;
        this.toughness = toughnessIn;
        this.repairMaterial = new LazyValue<>(repairMaterialIn);
    }

    @Override
    public int getDurability(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }
}

