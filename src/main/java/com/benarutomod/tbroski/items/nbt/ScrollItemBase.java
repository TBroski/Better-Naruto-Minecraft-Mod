package com.benarutomod.tbroski.items.nbt;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketAdvancement;
import com.benarutomod.tbroski.networking.packets.settings.PacketPlayerEntityAffiliationSync;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class ScrollItemBase extends Item {
    public ScrollItemBase() {
        super(new Item.Properties().group(Main.TAB).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote)
        {
            LazyOptional<IPlayerHandler> player_cap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerHandler = player_cap.orElse(new PlayerCapability());

            if (itemStack.hasTag() && !itemStack.getTag().getString("entityaffiliation").equalsIgnoreCase(""))
            {
                playerHandler.setPlayerEntityAffiliation(itemStack.getTag().getString("entityaffiliation"));
                playerIn.sendMessage(new StringTextComponent("Added Contract"));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new PacketPlayerEntityAffiliationSync(playerHandler.returnPlayerEntityAffiliation(), true));
                AdvancementHelper.grantAdvancement((ServerPlayerEntity) playerIn, Main.MODID + ":scroll");
            }
        }
        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }
        return ActionResult.resultSuccess(itemStack);
    }
}
