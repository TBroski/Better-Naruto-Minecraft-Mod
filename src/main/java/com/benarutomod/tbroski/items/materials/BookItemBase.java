package com.benarutomod.tbroski.items.materials;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.renderer.EtherealItemRenderer;
import com.benarutomod.tbroski.entity.EtherealItemEntity;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class BookItemBase extends Item {
    public BookItemBase() {
        super(new Item.Properties().group(Main.TAB).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!playerIn.isCrouching()) {
            EtherealItemEntity yata_mirror = new EtherealItemEntity(EntityInit.ETHEREAL_ITEM.get(), worldIn);
            yata_mirror.setPosition(playerIn.getPosX() - 5, playerIn.getPosY(), playerIn.getPosZ() + 3);
            yata_mirror.setItem(new ItemStack(ItemInit.YATA_MIRROR.get()));
            worldIn.addEntity(yata_mirror);
            EtherealItemEntity totska_blade = new EtherealItemEntity(EntityInit.ETHEREAL_ITEM.get(), worldIn);
            totska_blade.setPosition(playerIn.getPosX() - 5, playerIn.getPosY(), playerIn.getPosZ() - 3);
            totska_blade.setItem(new ItemStack(ItemInit.TOTSUKA_BlADE.get()));
            worldIn.addEntity(totska_blade);
        }
        else {
            LazyOptional<IPlayerHandler> player_cap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
            int rand = playerIn.getRNG().nextInt(3);
            if (rand == 0) {
                playerc.setSusanooOffHand(new ItemStack(ItemInit.YATA_MIRROR.get()));
                playerc.setSusanooMainHand(new ItemStack(ItemInit.TOTSUKA_BlADE.get()));
            }
            else if (rand == 1) {
                playerc.setSusanooOffHand(new ItemStack(Items.GOLDEN_SWORD));
                playerc.setSusanooMainHand(new ItemStack(Items.IRON_SWORD));
            }
            else {
                playerc.setSusanooOffHand(new ItemStack(Items.SHIELD));
                playerc.setSusanooMainHand(new ItemStack(Items.SHIELD));
            }
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
