package com.benarutomod.tbroski.items.materials;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class BookItemBase extends Item {
    public BookItemBase() {
        super(new Item.Properties().group(Main.TAB).maxStackSize(1));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn)
    {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if (!worldIn.isRemote)
        {
            LazyOptional<IPlayerHandler> player_cap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerHandler = player_cap.orElse(new PlayerCapability());

            if (playerHandler.returnPlayerLeftDojutsu() == DojutsuInit.RINNEGAN)
            {
                playerHandler.setPlayerLeftDojutsu(DojutsuInit.SHARINGAN);
            }
            else {
                playerHandler.setPlayerLeftDojutsu(DojutsuInit.RINNEGAN);
            }
            if (playerHandler.returnPlayerRightDojutsu() == DojutsuInit.RINNEGAN)
            {
                playerHandler.setPlayerRightDojutsu(DojutsuInit.SHARINGAN);
            }
            else {
                playerHandler.setPlayerRightDojutsu(DojutsuInit.RINNEGAN);
            }
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new PacketPlayerDojutsuSync(playerHandler.returnPlayerLeftDojutsu().getString(), true, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new PacketPlayerDojutsuSync(playerHandler.returnPlayerRightDojutsu().getString(), false, true));
            //SummoningJutsu.BasicSummoningJutsu(playerIn, 10);
        }
        return ActionResult.resultSuccess(itemstack);
    }
}
