package com.benarutomod.tbroski.items.food;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.init.EffectInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketAdvancement;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class DilutedRationsPillItemBase extends Item {
    public DilutedRationsPillItemBase() {
        super(new Properties().group(Main.TAB).maxStackSize(16).food((new Food.Builder().hunger(2).saturation(0.1F).setAlwaysEdible()).build()));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entityLiving;

            entityLiving.addPotionEffect(new EffectInstance(EffectInit.CHAKRA_REG.get(), 50));
            AdvancementHelper.grantAdvancement(player, Main.MODID + ":food/diluted_rations_pill");
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
