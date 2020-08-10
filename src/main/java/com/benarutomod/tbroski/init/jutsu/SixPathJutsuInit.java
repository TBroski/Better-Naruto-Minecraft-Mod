package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.sixpath.MiniRocketProjectileEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;

public class SixPathJutsuInit {

    public static void registerSixPathJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "deva_path", BeNMJutsu.Type.SIX_PATH_TECHNIQUE, 0, 150F, 208, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            Vec3d vec3d = playerIn.getEyePosition(1.0F);
            Vec3d vec3d1 = playerIn.getLook(1.0F);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 20, vec3d1.y * 20, vec3d1.z * 20);
            AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(20)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 40);
            if (entityRayTraceResult != null) {
                if (playerIn.isCrouching()) {
                    float yaw = playerIn.rotationYaw;
                    float pitch = playerIn.rotationPitch;
                    float velocity = 1.0F;
                    double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                    entityRayTraceResult.getEntity().setVelocity(-motionX, -motionY + 0.3F, -motionZ);
                }
                else {
                    float yaw = playerIn.rotationYaw;
                    float pitch = playerIn.rotationPitch + 0.5F;
                    float velocity = 1.5F;
                    double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
                    double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
                    entityRayTraceResult.getEntity().setVelocity(motionX, motionY + 0.3F, motionZ);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(true);
        }, (playerCapability, has) -> {
        }, (playerCapability) -> true).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            Vec3d vec3d = playerIn.getEyePosition(1.0F);
            Vec3d vec3d1 = playerIn.getLook(1.0F);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 20, vec3d1.y * 20, vec3d1.z * 20);
            AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(20)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 40);
            if (entityRayTraceResult != null) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "asura_path", BeNMJutsu.Type.SIX_PATH_TECHNIQUE, 0, 30F, 208, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            MiniRocketProjectileEntity entity = new MiniRocketProjectileEntity(playerIn.world, playerIn);
            entity.setItem(new ItemStack(ItemInit.MINI_ROCKET.get()));
            entity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.95F, 1.0F);
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(true);
        }, (playerCapability, has) -> {
        }, (playerCapability) -> true));
    }
}
