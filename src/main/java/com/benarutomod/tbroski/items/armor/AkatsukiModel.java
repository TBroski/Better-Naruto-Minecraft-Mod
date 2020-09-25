package com.benarutomod.tbroski.items.armor;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.items.models.ModelAkatsukiCloak;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class AkatsukiModel extends ArmorItem {

    public AkatsukiModel(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
        super(materialIn, slot, builder);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        ModelAkatsukiCloak model = new ModelAkatsukiCloak();
        //model.bipedLeftLeg.showModel = (armorSlot == EquipmentSlotType.CHEST);

        model.isChild = _default.isChild;
        model.isSneak = _default.isSneak;
        model.isSitting = _default.isSitting;
        model.rightArmPose = _default.rightArmPose;
        model.leftArmPose = _default.leftArmPose;

        return (A) model;
    }



    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return Main.MODID + ":textures/models/armor/akatsuki_layer_1.png";
    }
}
