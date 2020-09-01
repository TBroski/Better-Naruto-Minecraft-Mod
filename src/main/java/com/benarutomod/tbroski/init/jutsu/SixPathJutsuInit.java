package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.entity.jutsu.AbstractJutsuEntity;
import com.benarutomod.tbroski.api.entity.jutsu.AbstractNinjutsuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.sixpath.MiniRocketProjectileEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraftforge.common.util.LazyOptional;

public class SixPathJutsuInit {

    public static void registerSixPathJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "deva_path", BeNMJutsu.Type.SIX_PATH_TECHNIQUE, 0, 150F, 464, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    if (playerIn.isCrouching()) {
                        float yaw = playerIn.rotationYaw;
                        float pitch = playerIn.rotationPitch;
                        float velocity = 1.0F;
                        double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                        double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                        double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                        entityRayTraceResult.getEntity().setMotion(-motionX, -motionY + 0.3F, -motionZ);
                    } else {
                        float yaw = playerIn.rotationYaw;
                        float pitch = playerIn.rotationPitch + 0.5F;
                        float velocity = 1.5F;
                        double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                        double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                        double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                        entityRayTraceResult.getEntity().setMotion(motionX, motionY + 0.3F, motionZ);
                    }
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            if (!playerIn.world.isRemote) {
                if (RayTraceHelper.rayTraceEntities(playerIn, 6F) != null) {
                    return true;
                }
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "asura_path", BeNMJutsu.Type.SIX_PATH_TECHNIQUE, 0, 30F, 464, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            MiniRocketProjectileEntity entity = new MiniRocketProjectileEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.MINI_ROCKET.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.95F, 1.0F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "preta_path", BeNMJutsu.Type.SIX_PATH_TECHNIQUE, 0, 0.5F, 464, 32, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            // Marker
        }).addDamageEventListener((amount, source, defender) -> {
            if (source.getImmediateSource() instanceof AbstractNinjutsuEntity) {
                LazyOptional<IPlayerHandler> playerc = defender.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());
                for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                    if (jutsu.getName().equalsIgnoreCase(((AbstractJutsuEntity) source.getImmediateSource()).getAffiliatedJutsuName())) {
                        player_cap.addChakra(jutsu.getChakraCost());
                        return true;
                    }
                }
            }
            return false;
        }));
    }
}
