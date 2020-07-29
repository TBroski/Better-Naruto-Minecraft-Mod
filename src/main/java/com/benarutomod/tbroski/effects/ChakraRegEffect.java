package com.benarutomod.tbroski.effects;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraSync;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class ChakraRegEffect extends Effect {


    public ChakraRegEffect() {
        super(EffectType.BENEFICIAL, 0x7CD7DE);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        super.performEffect(entityLivingBaseIn, amplifier);
        if (entityLivingBaseIn instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) entityLivingBaseIn;
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            if (playercap.returnChakra() <= playercap.returnmaxChakra()) {
                playercap.addChakra(1);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketChakraSync(playercap.returnChakra()));
            }
        }
    }
}
