package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.common.jutsu.TransformationJutsu;
import com.benarutomod.tbroski.entity.clones.BasicCloneEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import com.benarutomod.tbroski.util.helpers.RayTraceHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.*;
import net.minecraftforge.common.util.LazyOptional;

public class ERankJutsuInit {

    public static void registerERankJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "clone", BeNMJutsu.Type.E_RANK, 6, 140F, 0, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            setGenin(playerIn);
            if (!playerIn.world.isRemote) {
                BasicCloneEntity entity = new BasicCloneEntity(EntityInit.BASIC_CLONE.get(), playerIn.world);
                entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                entity.setOwnerID(playerIn.getEntityId());
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "body_replacement", BeNMJutsu.Type.E_RANK, 3, 30F, 0, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            setGenin(playerIn);
            EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
            BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 4F);
            if (entityRayTraceResult != null) {
                BlockPos pos = entityRayTraceResult.getEntity().getPosition();
                entityRayTraceResult.getEntity().setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
            }
            else if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos())) {
                BlockPos pos = blockRayTraceResult.getPos();
                BlockState block = playerIn.getEntityWorld().getBlockState(pos);
                playerIn.getEntityWorld().removeBlock(pos, false);
                playerIn.getEntityWorld().setBlockState(playerIn.getPosition(), block);
                playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            EntityRayTraceResult entityRayTraceResult = RayTraceHelper.rayTraceEntities(playerIn, 6F);
            BlockRayTraceResult blockRayTraceResult = RayTraceHelper.rayTraceBlocks(playerIn, 4F);
            if ((blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) || entityRayTraceResult != null)) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "invisibility", BeNMJutsu.Type.E_RANK, 2, 0.5F, 0, 0, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            setGenin(playerIn);
            playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 40, 0));
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "transformation", BeNMJutsu.Type.E_RANK, 9, 75F, 0, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            setGenin(playerIn);
            TransformationJutsu.TransormationJutsu(playerIn, 25);
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            Vec3d vec3d = playerIn.getEyePosition(1.0F);
            Vec3d vec3d1 = playerIn.getLook(1.0F);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 4, vec3d1.y * 4, vec3d1.z * 4);
            AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(4)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult rayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 4);
            if (rayTraceResult != null) {
                return true;
            }
            return false;
        }));
    }

    private static void setGenin(PlayerEntity player) {
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        if (playerc.returnShinobiLevel() < 1) {
            playerc.setShinobiLevel(1);
            if (!player.world.isRemote)
                AdvancementHelper.grantAdvancement((ServerPlayerEntity) player, Main.MODID + ":shinobi/genin");
            //NetworkLoader.INSTANCE.sendToServer(new PacketShinobiLevel(playerc.returnShinobiLevel(), false));
        }
    }
}
