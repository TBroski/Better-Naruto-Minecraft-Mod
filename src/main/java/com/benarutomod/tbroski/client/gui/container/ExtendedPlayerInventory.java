package com.benarutomod.tbroski.client.gui.container;

import com.benarutomod.tbroski.Main;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.List;

public class ExtendedPlayerInventory extends PlayerInventory {

    public final NonNullList<ItemStack> backArray = NonNullList.withSize(1, ItemStack.EMPTY);
    public final NonNullList<ItemStack> backInventory = NonNullList.withSize(1, ItemStack.EMPTY);
    private final List<NonNullList<ItemStack>> allInventories = ImmutableList.of(this.mainInventory, this.armorInventory, this.offHandInventory, this.backInventory);

    public ExtendedPlayerInventory(PlayerEntity player)
    {
        super(player);
    }

    public NonNullList<ItemStack> getBackItems()
    {
        return backInventory;
    }

    public void copyStone(ExtendedPlayerInventory inventory)
    {
        this.backInventory.set(0, inventory.backInventory.get(0));
    }

    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        NonNullList<ItemStack> targetInventory = null;
        for(NonNullList<ItemStack> inventory : this.allInventories)
        {
            if(index < inventory.size())
            {
                targetInventory = inventory;
                break;
            }
            index -= inventory.size();
        }
        return targetInventory != null && !targetInventory.get(index).isEmpty() ? ItemStackHelper.getAndSplit(targetInventory, index, count) : ItemStack.EMPTY;
    }

    @Override
    public void deleteStack(ItemStack stack)
    {
        for(NonNullList<ItemStack> inventory : this.allInventories)
        {
            for(int i = 0; i < inventory.size(); ++i)
            {
                if(inventory.get(i) == stack)
                {
                    inventory.set(i, ItemStack.EMPTY);
                    break;
                }
            }
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        NonNullList<ItemStack> targetInventory = null;
        for(NonNullList<ItemStack> inventory : this.allInventories)
        {
            if(index < inventory.size())
            {
                targetInventory = inventory;
                break;
            }
            index -= inventory.size();
        }

        if(targetInventory != null && !targetInventory.get(index).isEmpty())
        {
            ItemStack stack = targetInventory.get(index);
            targetInventory.set(index, ItemStack.EMPTY);
            return stack;
        }
        else
        {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        NonNullList<ItemStack> targetInventory = null;
        for(NonNullList<ItemStack> inventory : this.allInventories)
        {
            if(index < inventory.size())
            {
                targetInventory = inventory;
                break;
            }
            index -= inventory.size();
        }
        if(targetInventory != null)
        {
            targetInventory.set(index, stack);
        }
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        List<ItemStack> list = null;
        for(NonNullList<ItemStack> inventory : this.allInventories)
        {
            if(index < inventory.size())
            {
                list = inventory;
                break;
            }
            index -= inventory.size();
        }
        return list == null ? ItemStack.EMPTY : list.get(index);
    }

    @Override
    public ListNBT write(ListNBT list)
    {
        list = super.write(list);
        for(int i = 0; i < this.backInventory.size(); i++)
        {
            if(!this.backInventory.get(i).isEmpty())
            {
                CompoundNBT compound = new CompoundNBT();
                compound.putByte("Slot", (byte) (i + 200));
                this.backInventory.get(i).write(compound);
                list.add(compound);
            }
        }
        return list;
    }

    @Override
    public void read(ListNBT list)
    {
        super.read(list);
        for(int i = 0; i < list.size(); ++i)
        {
            CompoundNBT compound = list.getCompound(i);
            int slot = compound.getByte("Slot") & 255;
            ItemStack stack = ItemStack.read(compound);
            if(!stack.isEmpty())
            {
                if(slot >= 200 && slot < this.backInventory.size() + 200)
                {
                    this.backInventory.set(slot - 200, stack);
                }
            }
        }
    }

    @Override
    public int getSizeInventory()
    {
        return super.getSizeInventory() + this.backInventory.size();
    }

    @Override
    public boolean isEmpty()
    {
        for(ItemStack stack : this.backInventory)
        {
            if(!stack.isEmpty())
            {
                return false;
            }
        }
        return super.isEmpty();
    }

    @Override
    public boolean hasItemStack(ItemStack targetStack)
    {
        for(NonNullList<ItemStack> inventory : this.allInventories)
        {
            Iterator iterator = inventory.iterator();
            while(true)
            {
                if(!iterator.hasNext())
                {
                    return false;
                }
                ItemStack stack = (ItemStack) iterator.next();
                if(!stack.isEmpty() && stack.isItemEqual(targetStack))
                {
                    break;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void clear()
    {
        for(List<ItemStack> list : this.allInventories)
        {
            list.clear();
        }
    }

    @Override
    public void dropAllItems()
    {
        super.dropAllItems();
    }


    public static class ExtendedPlayerContainer extends PlayerContainer {

        public static final ResourceLocation EMPTY_BACK_SLOT = new ResourceLocation(Main.MODID, "items/empty_back_slot");


        public ExtendedPlayerContainer(PlayerInventory playerInventory, boolean localWorld, PlayerEntity playerIn)
        {
            super(playerInventory, localWorld, playerIn);
            this.addSlot(new Slot(playerInventory, 41, 77, 44)
            {
                @Nullable
                @Override
                @OnlyIn(Dist.CLIENT)
                public Pair<ResourceLocation, ResourceLocation> func_225517_c_()
                {
                    return Pair.of(AtlasTexture.LOCATION_BLOCKS_TEXTURE, EMPTY_ARMOR_SLOT_CHESTPLATE);
                }

                @Override
                public boolean isItemValid(ItemStack stack)
                {
                    return stack.getItem() instanceof BackItem;
                }
            });
        }

        @Override
        public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
        {
            ItemStack copy = ItemStack.EMPTY;
            Slot slot = this.inventorySlots.get(index);
            if(slot != null && slot.getHasStack())
            {
                ItemStack slotStack = slot.getStack();
                copy = slotStack.copy();
                EquipmentSlotType equipmentslottype = MobEntity.getSlotForItemStack(copy);
                if(index != 46 && copy.getItem() instanceof BackItem)
                {
                    if(!this.inventorySlots.get(46).getHasStack())
                    {
                        if(!this.mergeItemStack(slotStack, 46, 47, false))
                        {
                            return ItemStack.EMPTY;
                        }
                    }
                }
                else if(index == 0)
                {
                    if(!this.mergeItemStack(slotStack, 9, 45, true))
                    {
                        return ItemStack.EMPTY;
                    }

                    slot.onSlotChange(slotStack, copy);
                }
                else if(index < 5)
                {
                    if(!this.mergeItemStack(slotStack, 9, 45, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index < 9)
                {
                    if(!this.mergeItemStack(slotStack, 9, 45, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(equipmentslottype.getSlotType() == EquipmentSlotType.Group.ARMOR && !this.inventorySlots.get(8 - equipmentslottype.getIndex()).getHasStack())
                {
                    int i = 8 - equipmentslottype.getIndex();
                    if(!this.mergeItemStack(slotStack, i, i + 1, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(equipmentslottype == EquipmentSlotType.OFFHAND && !this.inventorySlots.get(45).getHasStack())
                {
                    if(!this.mergeItemStack(slotStack, 45, 46, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index == 46)
                {
                    if(!this.mergeItemStack(slotStack, 9, 45, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index < 36)
                {
                    if(!this.mergeItemStack(slotStack, 36, 45, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(index < 45)
                {
                    if(!this.mergeItemStack(slotStack, 9, 36, false))
                    {
                        return ItemStack.EMPTY;
                    }
                }
                else if(!this.mergeItemStack(slotStack, 9, 45, false))
                {
                    return ItemStack.EMPTY;
                }

                if(slotStack.isEmpty())
                {
                    slot.putStack(ItemStack.EMPTY);
                }
                else
                {
                    slot.onSlotChanged();
                }

                if(slotStack.getCount() == copy.getCount())
                {
                    return ItemStack.EMPTY;
                }

                ItemStack itemstack2 = slot.onTake(playerIn, slotStack);
                if(index == 0)
                {
                    playerIn.dropItem(itemstack2, false);
                }
            }

            return copy;
        }
    }



    public interface BackItem {
    }
}
