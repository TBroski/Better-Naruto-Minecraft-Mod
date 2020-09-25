package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.misc.Particles;
import net.minecraft.block.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class EffectsJutsu {

    public static final int effectDuration = 55;

    public static void HandInfusion(PlayerEntity playerIn, int chakraAmount, int primaryModifier, int secondaryModifier) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playerIn.getPersistentData().putInt("handtick", playerIn.getPersistentData().getInt("handtick") + 1);
            if (playerIn.getPersistentData().getInt("handtick") >= 20) {
                playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, effectDuration, primaryModifier));
                playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, effectDuration, secondaryModifier));
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                playerIn.getPersistentData().putInt("handtick", 0);
            }
            if (playercap.returnChakraControl() > 2F && playerIn.collidedHorizontally) {
                playerIn.setMotion(playerIn.getMotion().x, 0.2F, playerIn.getMotion().z);
            }
        } else {
            playercap.setHandInfusionToggled(false);
            playerIn.sendStatusMessage(new TranslationTextComponent("jutsu." + Main.MODID + ".message.notenoughchakra"), true);
        }
    }

    public static void LegInfusion(PlayerEntity playerIn, int chakraAmount, int primaryModifier, int secondaryModifier) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        Vec3d playerMotion = playerIn.getMotion();
        if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playerIn.getPersistentData().putInt("legtick", playerIn.getPersistentData().getInt("legtick") + 1);
            int i = (int) Math.round(playerIn.getPosY() - playerIn.getYOffset() - 0.96f);
            BlockState j = playerIn.world.getBlockState(new BlockPos((int) playerIn.getPosX(), i, (int) playerIn.getPosZ()));
            int c = (int) Math.round(playerIn.getPosY() - playerIn.getYOffset() - 0.4f);
            BlockState k = playerIn.world.getBlockState(new BlockPos((int) playerIn.getPosX(), c, (int) playerIn.getPosZ()));
            int h = (int) Math.round(playerIn.getPosY() - playerIn.getYOffset() - 0.87f);
            BlockState n = playerIn.world.getBlockState(new BlockPos((int) playerIn.getPosX(), h, (int) playerIn.getPosZ()));
            if (playerIn.getPersistentData().getInt("legtick") >= 20) {
                playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, effectDuration, primaryModifier));
                playerIn.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, effectDuration, secondaryModifier));
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                playerIn.getPersistentData().putInt("legtick", 0);
            }
            if (playercap.returnChakraControl() >= 5F) {
                playerIn.getPersistentData().putBoolean("onwaterblock", false);
                if (k == Blocks.WATER.getDefaultState() || k == Fluids.FLOWING_WATER.getDefaultState().getBlockState()) {
                    playerIn.setMotion(playerMotion.x, playerMotion.y + 0.2D, playerMotion.z);
                    if (playerMotion.y > 0.6D) {
                        playerIn.setMotion(playerMotion.x, 0.6D, playerMotion.y); // 0.6D
                    }
                } else if (n == Blocks.WATER.getDefaultState() || n == Fluids.FLOWING_WATER.getDefaultState().getBlockState()) {
                    playerIn.setMotion(playerMotion.x, playerMotion.y + 0.1D, playerMotion.z);
                    if (playerMotion.y > 0.2D) {
                        playerIn.setMotion(playerMotion.x, 0.2D, playerMotion.z);
                    }
                } else if ((j == Blocks.WATER.getDefaultState() || j == Fluids.FLOWING_WATER.getDefaultState().getBlockState()) && playerMotion.y < 0.0D) {
                    playerIn.setMotion(playerMotion.x, 0.0D, playerMotion.z);
                    playerIn.onGround = true;
                    playerIn.getPersistentData().putBoolean("onwaterblock", true);
                }
            }
            if (playercap.returnChakraControl() >= 7.5F) {
                if (playerMotion.y < 0.07 && !playerIn.onGround && !playerIn.getPersistentData().getBoolean("doublejump")) {
                    float yaw = playerIn.rotationYaw;
                    float pitch = playerIn.rotationPitch;
                    float velocity = 1.0F;
                    double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                    playerIn.setMotion(motionX, motionY + 0.5F, motionZ);
                    playerIn.fallDistance = 0.0F;
                    playerIn.getPersistentData().putBoolean("doublejump", true);
                }
                if (!playerIn.isAirBorne || playerIn.onGround) {
                    playerIn.getPersistentData().putBoolean("doublejump", false);
                }
            }
        } else {
            playercap.setLegInfusionToggled(false);
            playerIn.sendStatusMessage(new TranslationTextComponent("jutsu." + Main.MODID + ".message.notenoughchakra"), true);
        }
    }


    public static void BodyInfusion(PlayerEntity playerIn, int chakraAmount, int primaryModifier, int secondaryModifier) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playerIn.getPersistentData().putInt("bodytick", playerIn.getPersistentData().getInt("bodytick") + 1);
            if (playerIn.getPersistentData().getInt("bodytick") >= 20) {
                playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, effectDuration, primaryModifier));
                if (playercap.returnChakraControl() >= 5) {
                    playerIn.addPotionEffect(new EffectInstance(Effects.SATURATION, effectDuration, secondaryModifier));
                }
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                playerIn.getPersistentData().putInt("bodytick", 0);
            }
        } else {
            playercap.setBodyInfusionToggled(false);
            playerIn.sendStatusMessage(new TranslationTextComponent("jutsu." + Main.MODID + ".message.notenoughchakra"), true);
        }
    }
}
