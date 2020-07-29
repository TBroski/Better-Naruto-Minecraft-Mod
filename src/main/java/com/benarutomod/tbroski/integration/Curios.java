package com.benarutomod.tbroski.integration;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class Curios {


    public static ItemStack getBackStack(PlayerEntity player)
    {
        return ItemStack.EMPTY;
    }

    public static ICapabilityProvider createBackProvider()
    {
        return null;
    }
}
