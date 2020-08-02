package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.FlyingStonesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.MudMoatEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.PhoenixFlowerEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningBallEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.RagingWavesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterSharkBulletEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterShurikenEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.GalePalmEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindExplosionEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Iterator;
import java.util.List;

public class ShootingJutsu {

    public static final int WaterShurikenJutsuID = 9;
    public static void WaterShurikenJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            WaterShurikenEntity entity = new WaterShurikenEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WATER_SHURIKEN_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.75F, 0.5F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Water Shuriken Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int FlyingStonesJutsuID = 10;
    public static void FlyingStonesJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            FlyingStonesEntity entity = new FlyingStonesEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.FLYING_STONES_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.1F, 1.2F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Flying Stones Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int LightningBallJutsuID = 11;
    public static void LightningBallJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            LightningBallEntity entity = new LightningBallEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.LIGHTNING_BALL_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Lightning Ball Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int GalePalmJutsuID = 12;
    public static void GalePalmJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            GalePalmEntity entity = new GalePalmEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.GALE_PALM_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2.0F, 0.1F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Gale Palm Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }


    public static final int PhoenixFlowerJutsuID = 13;
    public static void PhoenixFlowerJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            PhoenixFlowerEntity entity = new PhoenixFlowerEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.PHOENIX_FLOWER_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Phoenix Flower Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int RagingWavesJutsuID = 14;
    public static void RagingWavesJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            RagingWavesEntity entity = new RagingWavesEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.RAGING_WAVES_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Raging Waves Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int MudMoatJutsuID = 15;
    public static void MudMoatJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            MudMoatEntity entity = new MudMoatEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.MUD_MOAT_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Mud Moat Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int StunGunJutsuID = 16;
    public static void StunGunJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            List<LivingEntity> entities = playerIn.world.getEntitiesWithinAABB(LivingEntity.class, playerIn.getBoundingBox().grow(6));
            Iterator iterator = entities.iterator();
            while (iterator.hasNext())
            {
                LivingEntity entity = (LivingEntity) iterator.next();
                if (entity instanceof PlayerEntity) {
                    PlayerEntity playerEntity = (PlayerEntity) entity;
                    if (playerEntity.getUniqueID() != playerIn.getUniqueID()) {
                        entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1F);
                        playerEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 50, 1));
                    }
                }
                else {
                    entity.attackEntityFrom(DamageSource.LIGHTNING_BOLT, 1F);
                    entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 50, 1));
                }
            }
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Stun Gun Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int WindExplosionJutsuID = 17;
    public static void WindExplosionJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            WindExplosionEntity entity = new WindExplosionEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_EXPLOSION_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.0F, 1.0F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Wind Explosion Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int WaterSharkBulletJutsuID = 19;
    public static void WaterSharkBulletJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            WaterSharkBulletEntity entity = new WaterSharkBulletEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WATER_SHARK_BULLET_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1F, 1.0F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Water Shark Bullet Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int LightningArrowJutsuID = 21;
    public static void LightningArrowJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            LightningArrowEntity entity = new LightningArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.LIGHTNING_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Lightning Arrow Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
    public static final int WindArrowJutsuID = 22;
    public static void WindArrowJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            WindArrowEntity entity = new WindArrowEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.WIND_ARROW_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Wind Arrow Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
}
