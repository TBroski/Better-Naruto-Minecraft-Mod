package com.benarutomod.tbroski.items.food;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketAdvancement;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraSync;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class SpikedRationsPillItemBase extends Item {
    public SpikedRationsPillItemBase() {
        super(new Properties().group(Main.TAB).maxStackSize(16).food((new Food.Builder().hunger(2).saturation(0.1F).setAlwaysEdible()).build()));
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entityLiving;
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = capabilities.orElse(new PlayerCapability());

            player_cap.addChakra(35);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketChakraSync(player_cap.returnChakra()));
            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("spiked_rations_pill"));
        }
        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }
}
