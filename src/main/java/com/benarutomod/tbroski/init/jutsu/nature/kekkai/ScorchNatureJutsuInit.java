package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.entity.clones.ScorchCloneEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.scorch.BlazingPelletEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.scorch.ExtremelySteamingMurderEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.scorch.GreatFireballEntity;
import com.benarutomod.tbroski.init.EffectInit;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.EntityRayTraceResult;

public class ScorchNatureJutsuInit {

    public static void registerScorchJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "extremely_steaming_murder", BeNMJutsu.Type.SCORCH_NATURE, 14, 200F, 192, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                ExtremelySteamingMurderEntity entity = new ExtremelySteamingMurderEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.EXTREAMLY_STEAMING_MURDER_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "blazing_pellets", BeNMJutsu.Type.SCORCH_NATURE, 18, 2F, 192, 16, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                for (int i = 0; i < 1 + taijutsuModifier0; i++) {
                    BlazingPelletEntity entity = new BlazingPelletEntity(playerIn, playerIn.world);
                    entity.setItem(new ItemStack(ItemInit.BLAZING_PELLETS_JUTSU.get()));
                    entity.shoot(playerIn, playerIn.rotationPitch + playerIn.getRNG().nextInt(i + 1), playerIn.rotationYaw + playerIn.getRNG().nextInt(i + 1), 0.0F, 0.8F + (playerIn.getRNG().nextInt(i + 1) / 10), 5.0F);
                    playerIn.world.addEntity(entity);
                }
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "great_fireball", BeNMJutsu.Type.SCORCH_NATURE, 22, 200F, 192, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                GreatFireballEntity entity = new GreatFireballEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.GREAT_FIREBALL_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.55F, 4.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "scorch_clone", BeNMJutsu.Type.SCORCH_NATURE, 10, 120F, 192, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                ScorchCloneEntity entity = new ScorchCloneEntity(EntityInit.SCORCH_CLONE.get(), playerIn.world);
                entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                entity.setOwnerID(playerIn.getEntityId());
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "heated_gas", BeNMJutsu.Type.SCORCH_NATURE, 12, 120F, 192, 64, false, ((playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
            if (entityRayTraceResult != null && entityRayTraceResult.getEntity() instanceof LivingEntity) {
                ((LivingEntity) entityRayTraceResult.getEntity()).addPotionEffect(new EffectInstance(EffectInit.EXHAUSTION.get(), 200));
            }
        })).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceEntities(playerIn, 6F) != null));
    }
}
