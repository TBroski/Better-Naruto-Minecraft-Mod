package com.benarutomod.tbroski.common.jutsu;

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
import net.minecraftforge.common.util.LazyOptional;

public class EffectsJutsu {

    public static final int effectDuration = 55;

    public static final int InvisibilityTechniqueID = 3;
    public static void InvisibilityTechnigue(PlayerEntity playerIn, int chakraAmount) {
        if (!playerIn.getEntityWorld().isRemote) {
            LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
            if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
                playerIn.getPersistentData().putInt("invisibilitytick", playerIn.getPersistentData().getInt("invisibilitytick") + 1);
                if (playerIn.getPersistentData().getInt("invisibilitytick") >= 20) {
                    playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, effectDuration));
                    playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                    playerIn.getPersistentData().putInt("invisibilitytick", 0);
                }
            } else {
                clientPlayer.getPersistentData().putBoolean("invisibilitytechnigue", false);
                playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
            }
        }
    }

    public static final int HandInfusionID = 4;
    public static void HandInfusion(PlayerEntity playerIn, int chakraAmount, int primaryModifier, int secondaryModifier) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
        if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playerIn.getPersistentData().putInt("handtick", playerIn.getPersistentData().getInt("handtick") + 1);
            if (playerIn.getPersistentData().getInt("handtick") >= 20) {
                playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, effectDuration, primaryModifier));
                playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, effectDuration, secondaryModifier));
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                playerIn.getPersistentData().putInt("handtick", 0);
            }
            if (clientPlayer.collidedHorizontally) {
                clientPlayer.setVelocity(playerIn.getMotion().x, 0.2F, playerIn.getMotion().z);
            }
        } else {
            clientPlayer.getPersistentData().putBoolean("handinfusion", false);
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }

    public static final int LegInfusionID = 5;
    public static void LegInfusion(PlayerEntity playerIn, int chakraAmount, int primaryModifier, int secondaryModifier) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
        Vec3d playerMotion = clientPlayer.getMotion();
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
            if (playercap.returnChakraControl() >= 5) {
                playerIn.getPersistentData().putBoolean("onwaterblock", false);
                if (k == Blocks.WATER.getDefaultState() || k == Fluids.FLOWING_WATER.getDefaultState().getBlockState()) {
                    clientPlayer.setMotion(playerMotion.x, playerMotion.y + 0.2D, playerMotion.z);
                    if (playerMotion.y > 0.6D) {
                        clientPlayer.setMotion(playerMotion.x, 0.6D, playerMotion.y);
                    }
                } else if (n == Blocks.WATER.getDefaultState() || n == Fluids.FLOWING_WATER.getDefaultState().getBlockState()) {
                    clientPlayer.setMotion(playerMotion.x, playerMotion.y + 0.1D, playerMotion.z);
                    if (playerMotion.y > 0.2D) {
                        clientPlayer.setMotion(playerMotion.x, 0.2D, playerMotion.z);
                    }
                } else if ((j == Blocks.WATER.getDefaultState() || j == Fluids.FLOWING_WATER.getDefaultState().getBlockState()) && playerMotion.y < 0.0D) {
                    clientPlayer.setMotion(playerMotion.x, 0.0D, playerMotion.z);
                    clientPlayer.onGround = true;

                    playerIn.getPersistentData().putBoolean("onwaterblock", true);
                }
            }
            if (playercap.returnChakraControl() >= 7.5F) {
                if (Minecraft.getInstance().gameSettings.keyBindJump.isPressed() && clientPlayer.isAirBorne && playerMotion.y < 0.07 && !playerIn.onGround && !playerIn.getPersistentData().getBoolean("doublejump")) {
                    //clientPlayer.setVelocity(playerMotion.x, (playerMotion.y + 0.8D), playerMotion.z);
                    //playerMotion.y > 0.07 || playerMotion.y < -0.07
                    float yaw = playerIn.rotationYaw;
                    float pitch = playerIn.rotationPitch;
                    float velocity = 1.0F;
                    double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                    clientPlayer.setVelocity(motionX, motionY + 0.5F, motionZ);
                    Particles.addParticles(clientPlayer, ParticleTypes.LARGE_SMOKE, 5);
                    playerIn.fallDistance = 0.0F;
                    playerIn.getPersistentData().putBoolean("doublejump", true);
                }
                if (!clientPlayer.isAirBorne || clientPlayer.onGround) {
                    playerIn.getPersistentData().putBoolean("doublejump", false);
                }
            }
        } else {
            clientPlayer.getPersistentData().putBoolean("leginfusion", false);
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }


    public static final int BodyInfusionID = 7;
    public static void BodyInfusion(PlayerEntity playerIn, int chakraAmount, int primaryModifier, int secondaryModifier) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
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
            clientPlayer.getPersistentData().putBoolean("bodyinfusion", false);
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int FistRockJutsuID = 20;
    public static void FistRockJutsu(PlayerEntity playerIn, int chakraAmount) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
        if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playerIn.getPersistentData().putInt("fistrocktick", playerIn.getPersistentData().getInt("fistrocktick") + 1);
            if (playerIn.getPersistentData().getInt("fistrocktick") >= 20) {
                clientPlayer.getPersistentData().putBoolean("fistrock", true);
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                playerIn.getPersistentData().putInt("fistrocktick", 0);
            }
        } else {
            clientPlayer.getPersistentData().putBoolean("fistrock", false);
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int MoltenFistJutsuID = 18;
    public static void MoltenFistJutsu(PlayerEntity playerIn, int chakraAmount) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        ClientPlayerEntity clientPlayer = Minecraft.getInstance().player;
        if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playerIn.getPersistentData().putInt("moltenfisttick", playerIn.getPersistentData().getInt("moltenfisttick") + 1);
            if (playerIn.getPersistentData().getInt("moltenfisttick") >= 20) {
                //clientPlayer.getPersistentData().putBoolean("moltenfist", true);
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                playerIn.getPersistentData().putInt("moltenfisttick", 0);
            }
        } else {
            //clientPlayer.getPersistentData().putBoolean("moltenfist", false);
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
}
