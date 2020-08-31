package com.benarutomod.tbroski.effects;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.init.EffectInit;
import com.benarutomod.tbroski.util.helpers.BijuuHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

public class BijuuModeEffect extends Effect {

    public BijuuModeEffect() {
        super(EffectType.BENEFICIAL, 0x808080);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        if (entityLivingBaseIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entityLivingBaseIn;
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            if (BijuuHelper.hasAttribute(AbstractBijuuEntity.BijuuAttributes.FLIGHT, player)) {
                player.abilities.allowFlying = true;
                player.abilities.isFlying = true;
                player.sendPlayerAbilities();
            }
            if (BijuuHelper.hasAttribute(AbstractBijuuEntity.BijuuAttributes.WATER_BREATHING, player)) {
                player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 45));
            }
            if (playercap.returnPlayerToadSageMode() > 0) {
                player.addPotionEffect(new EffectInstance(EffectInit.SAGE_CHAKRA_REG.get(), 45));
            }
        }
    }

    @Override
    public void removeAttributesModifiersFromEntity(LivingEntity entityLivingBaseIn, AbstractAttributeMap attributeMapIn, int amplifier) {
        super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
        if (entityLivingBaseIn instanceof PlayerEntity && !((PlayerEntity) entityLivingBaseIn).abilities.isCreativeMode) {
            ((PlayerEntity) entityLivingBaseIn).abilities.isFlying = false;
            ((PlayerEntity) entityLivingBaseIn).abilities.allowFlying = false;
            ((PlayerEntity) entityLivingBaseIn).sendPlayerAbilities();
        }
    }
}
