package com.benarutomod.tbroski.effects;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.IBeNMBiome;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraSync;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class SageChakraEffect extends Effect {

    public SageChakraEffect() {
        super(EffectType.BENEFICIAL, 0x7CD7DE);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {

        World world = entityLivingBaseIn.getEntityWorld();
        Biome biome = world.getBiome(entityLivingBaseIn.getPosition());
        if (entityLivingBaseIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entityLivingBaseIn;
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            if (playercap.returnChakra() <= playercap.returnmaxChakra()) {
                if (biome instanceof IBeNMBiome) {
                    playercap.addChakra(((IBeNMBiome) biome).getTranquility()  + (amplifier * 0.3F));
                    NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketChakraSync(playercap.returnChakra()));
                } else {
                    int creatureSpawns = biome.getSpawns(EntityClassification.CREATURE).size() + biome.getSpawns(EntityClassification.WATER_CREATURE).size();
                    int ambientSpawns = biome.getSpawns(EntityClassification.AMBIENT).size();
                    int monsterSpawns = biome.getSpawns(EntityClassification.MONSTER).size();
                    int miscSpawns = biome.getSpawns(EntityClassification.MISC).size();

                    float chakraAmount = (creatureSpawns * 0.3F) + ((ambientSpawns + miscSpawns) * 0.3F) - (monsterSpawns * 0.1F);
                    playercap.addChakra(chakraAmount + (amplifier * 0.3F));
                    NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketChakraSync(playercap.returnChakra()));
                }
            }
        }
    }
}
