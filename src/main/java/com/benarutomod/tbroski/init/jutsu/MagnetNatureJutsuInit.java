package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.util.math.*;

public class MagnetNatureJutsuInit {

    public static void registerMagnetJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "matter_repulsion", BeNMJutsu.Type.MAGNET_NATURE, 2, 15F, 96, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            Vec3d vec = playerIn.getPositionVector();
            Vec3d vec3 = new Vec3d(vec.x,vec.y + playerIn.getEyeHeight(),vec.z);
            Vec3d vec3a = playerIn.getLook(1.0F);
            Vec3d vec3b = vec3.add(vec3a.getX() * 5, vec3a.getY() *  5, vec3a.getZ() *  5);
            BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
            if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) && playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlockHardness(playerIn.world, blockRayTraceResult.getPos()) <= 0.5F) {
                BlockPos pos = blockRayTraceResult.getPos();
                FallingBlockEntity blockEntity = new FallingBlockEntity(playerIn.world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, playerIn.world.getBlockState(pos));
                float yaw = playerIn.rotationYaw;
                float pitch = 0;
                float velocity = 1.0F;
                double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                blockEntity.setVelocity(motionX, motionY + 0.6D, motionZ);
                playerIn.world.addEntity(blockEntity);
            }
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setMatterRepulsionJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasMatterRepulsionJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setMatterRepulsionJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasMatterRepulsionJutsuBoolean()).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            Vec3d vec = playerIn.getPositionVector();
            Vec3d vec3 = new Vec3d(vec.x,vec.y + playerIn.getEyeHeight(),vec.z);
            Vec3d vec3a = playerIn.getLook(1.0F);
            Vec3d vec3b = vec3.add(vec3a.getX() * 5, vec3a.getY() *  5, vec3a.getZ() *  5);
            BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
            if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) && playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlockHardness(playerIn.world, blockRayTraceResult.getPos()) <= 0.5F) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "self_levitation", BeNMJutsu.Type.MAGNET_NATURE, 11, 175F, 96, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            playerIn.getPersistentData().putInt("sand_levitation", 100);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                boolean didBuy = buttonJutsu.doNormalPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setSelfLevitationJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasSelfLevitationJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setSelfLevitationJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasSelfLevitationJutsuBoolean()));
    }
}
