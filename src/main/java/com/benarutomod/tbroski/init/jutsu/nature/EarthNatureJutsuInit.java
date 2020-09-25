package com.benarutomod.tbroski.init.jutsu.nature;

import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.FlyingStonesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.MudMoatEntity;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import com.benarutomod.tbroski.util.helpers.StaticFeatureHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockRayTraceResult;

public class EarthNatureJutsuInit {

    public static void registerEarthJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "flying_stones", BeNMJutsu.Type.EARTH_NATURE, 4, 55F, 64, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            FlyingStonesEntity entity = new FlyingStonesEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.FLYING_STONES_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.1F, 1.2F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "mud_moat", BeNMJutsu.Type.EARTH_NATURE, 6, 60F, 64, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            MudMoatEntity entity = new MudMoatEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.MUD_MOAT_JUTSU.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.3F, 1.0F);
            playerIn.world.addEntity(entity);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "fist_rock", BeNMJutsu.Type.EARTH_NATURE, 4, 0.5F, 64, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            // Marker
        }).addAttackEventListener((attacker, target) -> {
            target.attackEntityFrom(DamageSource.causePlayerDamage(attacker), 1F);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "mud_wall", BeNMJutsu.Type.EARTH_NATURE, 5, 100F, 64, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 6F);
                if (blockRayTraceResult != null) {
                    StaticFeatureHelper.placeWall(playerIn, blockRayTraceResult.getPos(), Blocks.DIRT.getDefaultState(), (int) playerCapability.returnChakraControl());
                }
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> RayTraceHelper.rayTraceBlocks(playerIn, 6F) != null));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "shield_of_sand", BeNMJutsu.Type.EARTH_NATURE, 10, 150F, 64, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                StaticFeatureHelper.placeDome(playerIn, playerIn.getPosition(), Blocks.SANDSTONE.getDefaultState());
            }
        }));
    }
}
