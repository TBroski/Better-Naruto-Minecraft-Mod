package com.benarutomod.tbroski.effects;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import java.util.ArrayList;
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
        List<ItemStack> curativeItems = new ArrayList<>();
        curativeItems.add(new ItemStack(Items.AIR));
        return curativeItems;
    }
}
