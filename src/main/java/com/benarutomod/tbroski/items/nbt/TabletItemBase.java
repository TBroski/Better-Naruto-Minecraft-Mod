package com.benarutomod.tbroski.items.nbt;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketAddLearntClan;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
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

public class TabletItemBase extends Item {

    public TabletItemBase() {
        super(new Item.Properties().group(Main.TAB).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote) {
            String clanName = itemStack.getTag().getString("clan");
            if (itemStack.hasTag() && clanName != null) {
                LazyOptional<IPlayerHandler> player_cap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler playerHandler = player_cap.orElse(new PlayerCapability());
                playerHandler.addLearntClan(ClanHelper.getClanFromString(clanName));
                playerIn.sendMessage(new StringTextComponent("Learnt clan " + clanName.substring(0,1).toUpperCase() + clanName.substring(1).toLowerCase()));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new PacketAddLearntClan(clanName));
                AdvancementHelper.grantAdvancement((ServerPlayerEntity) playerIn, Main.MODID + ":tablet");
            }
        }
        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }
        return ActionResult.resultSuccess(itemStack);
    }
}
