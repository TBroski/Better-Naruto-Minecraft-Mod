package com.benarutomod.tbroski.items.materials;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Iterator;
import java.util.List;

public class RiceItemBase extends Item {
    public RiceItemBase() {
        super(new Item.Properties().group(Main.TAB));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
            List<ItemEntity> itemEntities = playerIn.world.getEntitiesWithinAABB(ItemEntity.class, playerIn.getBoundingBox().grow(10));
            Iterator iterator = itemEntities.iterator();
            while (iterator.hasNext()) {
                ItemEntity item = (ItemEntity) iterator.next();
                double distX = playerIn.getPosX() - item.getPosX();
                double distZ = playerIn.getPosZ() - item.getPosZ();
                double distY = item.getPosY() + 1.5D - item.getPosY();
                double dir = Math.atan2(distZ, distX);
                double speed = 1F / item.getDistance(playerIn) * 0.5;
                if (distY < 0) {
                    item.setMotion(item.getMotion().x, item.getMotion().y + speed, item.getMotion().z);
                }
                item.setMotion(Math.cos(dir) * speed, item.getMotion().y, item.getMotion().z);
                item.setMotion(item.getMotion().x, item.getMotion().y, Math.sin(dir) * speed);
            }
        return ActionResult.resultSuccess(itemstack);
    }
}
