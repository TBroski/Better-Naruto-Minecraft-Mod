package com.benarutomod.tbroski.effects;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketTruthSeekingOrbsSync;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class SixPathSageEffect extends Effect {

    public SixPathSageEffect() {
        super(EffectType.BENEFICIAL, 0x000000);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        if (!entityLivingBaseIn.world.isRemote && entityLivingBaseIn.ticksExisted % 300 == 0 && entityLivingBaseIn.getRNG().nextBoolean() && entityLivingBaseIn instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entityLivingBaseIn;
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

            playercap.setTruthSeekingOrbAmount(playercap.getTruthSeekingOrbAmount() + 1);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketTruthSeekingOrbsSync(player.getEntityId(), playercap.getTruthSeekingOrbAmount()));
        }
    }
}
