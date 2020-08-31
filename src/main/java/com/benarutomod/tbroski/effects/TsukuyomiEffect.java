package com.benarutomod.tbroski.effects;

import com.benarutomod.tbroski.init.DimensionInit;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.world.dimension.DimensionType;

import java.util.List;

public class TsukuyomiEffect extends Effect {

    public TsukuyomiEffect() {
        super(EffectType.HARMFUL, 0xd45050);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return ImmutableList.of();
    }

    @Override
    public void applyAttributesModifiersToEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        entityLivingBaseIn.getPersistentData().putInt("pastx", entityLivingBaseIn.getPosition().getX());
        entityLivingBaseIn.getPersistentData().putInt("pasty", entityLivingBaseIn.getPosition().getY());
        entityLivingBaseIn.getPersistentData().putInt("pastz", entityLivingBaseIn.getPosition().getZ());
        entityLivingBaseIn.getPersistentData().putInt("pastdim", entityLivingBaseIn.dimension.getId());
        entityLivingBaseIn.changeDimension(DimensionType.byName(DimensionInit.TSUKUYOMI_DIMENSION));
        super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        entityLivingBaseIn.changeDimension(DimensionType.getById(entityLivingBaseIn.getPersistentData().getInt("pastdim")));
        entityLivingBaseIn.setPositionAndUpdate(entityLivingBaseIn.getPersistentData().getInt("pastx"), entityLivingBaseIn.getPersistentData().getInt("pasty"), entityLivingBaseIn.getPersistentData().getInt("pastz"));
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
    }
}
