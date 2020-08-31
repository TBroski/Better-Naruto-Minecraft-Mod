package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.registries.ForgeRegistries;

public class BijuuHelper {

    public static boolean hasAttribute(AbstractBijuuEntity.BijuuAttributes attributeIn, PlayerEntity playerIn) {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

        AbstractBijuuEntity bijuu = null;
        for (EntityType<?> entity : ForgeRegistries.ENTITIES.getValues()) {
            if (entity.getRegistryName().toString().equalsIgnoreCase(playercap.returnPlayerBijuu())) {
                bijuu = (AbstractBijuuEntity) entity.create(Minecraft.getInstance().world);
                break;
            }
        }
        if (bijuu != null) {
            for (AbstractBijuuEntity.BijuuAttributes attribute : bijuu.getBijuuAttributes()) {
                if (attribute == attributeIn) {
                    return true;
                }
            }
        }
        return false;
    }
}
