package com.benarutomod.tbroski.util.helpers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.*;

public class RayTraceHelper {

    public static EntityRayTraceResult rayTraceEntities(PlayerEntity playerIn, float distance) {
        distance = distance * 5;
        Vec3d vec3d = playerIn.getEyePosition(1.0F);
        Vec3d vec3d1 = playerIn.getLook(1.0F);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * distance, vec3d1.y * distance, vec3d1.z * distance);
        AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(distance)).grow(1.0D, 1.0D, 1.0D);
        EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), distance * 2);
        return entityRayTraceResult;
    }

    public static BlockRayTraceResult rayTraceBlocks(PlayerEntity playerIn, float distance) {
        distance = distance * 5;
        Vec3d vec3d = playerIn.getEyePosition(1.0F);
        Vec3d vec3d1 = playerIn.getLook(1.0F);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * distance, vec3d1.y * distance, vec3d1.z * distance);
        BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
        return blockRayTraceResult;
    }
}
