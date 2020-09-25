package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.entity.projectile.jutsu.ice.IcePetalEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.ice.OneHornedWhiteWhaleEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import com.benarutomod.tbroski.util.helpers.StaticFeatureHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;

public class IceNatureJutsuInit {

    public static void registerIceJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "ice_rock_dome", BeNMJutsu.Type.ICE_NATURE, 8, 120F, 160, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                StaticFeatureHelper.placeDome(playerIn, playerIn.getPosition(), Blocks.PACKED_ICE.getDefaultState());
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "ice_prison", BeNMJutsu.Type.ICE_NATURE, 15, 200F, 160, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
                if (entityRayTraceResult != null) {
                    BlockPos setPos = entityRayTraceResult.getEntity().getPosition();
                    entityRayTraceResult.getEntity().setPosition(setPos.getX(), setPos.getY(), setPos.getZ());
                    StaticFeatureHelper.placePrison(playerIn, setPos, Blocks.PACKED_ICE.getDefaultState());
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            if (!playerIn.world.isRemote)
                return RayTraceHelper.rayTraceEntities(playerIn, 6F) != null;
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "one_horned_white_whale", BeNMJutsu.Type.ICE_NATURE, 6, 130F, 160, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                OneHornedWhiteWhaleEntity entity = new OneHornedWhiteWhaleEntity(playerIn, playerIn.world);
                entity.setItem(new ItemStack(ItemInit.ONE_HORNED_WHITE_WHALE_JUTSU.get()));
                entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 0.8F, 1.0F);
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "ten_thousand_ice_petals", BeNMJutsu.Type.ICE_NATURE, 18, 2F, 160, 48, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                for (int i = 0; i < 1 + taijutsuModifier0; i++) {
                    IcePetalEntity entity = new IcePetalEntity(playerIn, playerIn.world);
                    entity.setItem(new ItemStack(ItemInit.TEN_THOUSAND_ICE_PETALS_JUTSU.get()));
                    entity.shoot(playerIn, playerIn.rotationPitch + playerIn.getRNG().nextInt(i + 1), playerIn.rotationYaw + playerIn.getRNG().nextInt(i + 1), 0.0F, 0.8F + (playerIn.getRNG().nextInt(i + 1) / 10), 5.0F);
                    playerIn.world.addEntity(entity);
                }
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "demonic_mirroring_ice_crystals", BeNMJutsu.Type.ICE_NATURE, 12, 120F, 160, 64, false, ((playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
            if (entityRayTraceResult != null) {
                entityRayTraceResult.getEntity().attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 5F);
                StaticFeatureHelper.placeDome(playerIn, entityRayTraceResult.getEntity().getPosition(), Blocks.PACKED_ICE.getDefaultState());
            }
        })).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceEntities(playerIn, 6F) != null));
    }
}
