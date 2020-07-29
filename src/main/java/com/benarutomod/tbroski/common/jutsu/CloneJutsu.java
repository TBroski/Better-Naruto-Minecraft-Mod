package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.clones.BasicCloneEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraSync;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;


public class CloneJutsu {

    public static final int BasicCloneJutsuID = 1;
    public static void BasicCloneJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        if (!playerIn.getEntityWorld().isRemote)
        {
            LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
                playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerIn), new PacketChakraSync(playercap.returnChakra()));
                BasicCloneEntity entity = new BasicCloneEntity(EntityInit.BASIC_CLONE.get(), playerIn.world);
                entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                entity.setOwnerID(playerIn.getEntityId());
                playerIn.world.addEntity(entity);
                if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Basic Clone Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
            }
            else {
                playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
            }
        }
    }
}
