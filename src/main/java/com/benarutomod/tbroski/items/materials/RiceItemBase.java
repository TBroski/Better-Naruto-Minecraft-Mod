package com.benarutomod.tbroski.items.materials;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.shinobi.shinobi.BrotherSharinganEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
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

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        //if (!context.getWorld().isRemote) {
/*            BrotherSharinganEntity brother = new BrotherSharinganEntity(EntityInit.BROTHER_SHARINGAN.get(), context.getWorld(), context.getPlayer());
            brother.setOwnerID(context.getPlayer().getEntityId());
            brother.setPosition(context.getPos().getX(), context.getPos().getY() + 1, context.getPos().getZ());
            context.getPlayer().world.addEntity(brother);*/
/*            LazyOptional<IPlayerHandler> player_cap = context.getPlayer().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                System.out.println(jutsu.getName() + " " + playerc.hasJutsuBoolean(jutsu));
            }*/
        if (!context.getPlayer().world.isRemote)
            AdvancementHelper.grantAdvancement((ServerPlayerEntity) context.getPlayer(), Main.MODID + ":shinobi/jonin");
        //}
        return super.onItemUse(context);
    }
}
