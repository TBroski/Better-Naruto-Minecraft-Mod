package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.projectile.jutsu.sharingan.AmaterasuJutsuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class SharinganJutsu {

    public static final int AmaterasuJutsuID = 24;
    public static void AmaterasuJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            AmaterasuJutsuEntity entity = new AmaterasuJutsuEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(Items.AIR));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 2F, 0.5F);
            playerIn.world.addEntity(entity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Amaterasu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }

    public static final int TsukuyomiJutsuID = 25;
    public static void TsukuyomiJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        if (playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));

            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Tsukuyomi! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
}
