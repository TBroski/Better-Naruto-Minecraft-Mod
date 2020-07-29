package com.benarutomod.tbroski.items.tools;

import com.benarutomod.tbroski.integration.Curios;
import com.benarutomod.tbroski.client.gui.container.ExtendedPlayerInventory;
import com.benarutomod.tbroski.event.ForgeEventSubscriber;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class BeNMSwordItemBase extends SwordItem implements ExtendedPlayerInventory.BackItem {

    public BeNMSwordItemBase(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builder) {
        super(tier, attackDamageIn, attackSpeedIn, builder);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack heldItem = playerIn.getHeldItem(handIn);
        if(playerIn.inventory instanceof ExtendedPlayerInventory)
        {
            ExtendedPlayerInventory inventory = (ExtendedPlayerInventory) playerIn.inventory;
            if(inventory.getBackItems().get(0).isEmpty())
            {
                playerIn.inventory.setInventorySlotContents(41, heldItem.copy());
                heldItem.setCount(0);
                playerIn.playSound(SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 1.0F, 1.0F);
                return new ActionResult<>(ActionResultType.SUCCESS, heldItem);
            }
        }
        return new ActionResult<>(ActionResultType.FAIL, heldItem);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {
        if(!ForgeEventSubscriber.isCuriosLoaded())
        {
            return null;
        }
        return Curios.createBackProvider();
    }
}
