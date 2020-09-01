package com.benarutomod.tbroski.effects;

import com.benarutomod.tbroski.init.DamageInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

public class ExhaustionEffect extends Effect {
    public ExhaustionEffect() {
        super(EffectType.HARMFUL, 0x808080);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        System.out.println(duration / 20 == (int) duration / 20F);
        System.out.println(duration / 20F);
        return duration / 20 == (int) duration / 20F;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        entityLivingBaseIn.attackEntityFrom(DamageInit.EXHAUSTION, 1F);
    }
}
