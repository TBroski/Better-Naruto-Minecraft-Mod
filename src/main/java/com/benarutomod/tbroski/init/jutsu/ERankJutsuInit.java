package com.benarutomod.tbroski.init.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.player.jutsu.AbstractJutsuScreen;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.IBeNMPlugin;
import com.benarutomod.tbroski.common.jutsu.TransformationJutsu;
import com.benarutomod.tbroski.entity.clones.BasicCloneEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketShinobiLevel;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.*;
import net.minecraftforge.common.util.LazyOptional;

public class ERankJutsuInit {

    public static void registerERankJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "clone", BeNMJutsu.Type.E_RANK, 6, 200F, 0, 16, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            BasicCloneEntity entity = new BasicCloneEntity(EntityInit.BASIC_CLONE.get(), playerIn.world);
            entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
            entity.setOwnerID(playerIn.getEntityId());
            playerIn.world.addEntity(entity);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                setGenin(Minecraft.getInstance().player);
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setCloneJutsuBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasCloneJutsuBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setCloneJutsuBoolean(has);
        }, (playerCapability) -> playerCapability.hasCloneJutsuBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "body_replacement", BeNMJutsu.Type.E_RANK, 3, 30F, 0, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            Vec3d vec3d = playerIn.getEyePosition(1.0F);
            Vec3d vec3d1 = playerIn.getLook(1.0F);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 20, vec3d1.y * 20, vec3d1.z * 20);
            AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(20)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 40);
            BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
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
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                setGenin(Minecraft.getInstance().player);
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setBodyReplacementBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasBodyReplacementBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setBodyReplacementBoolean(has);
        }, (playerCapability) -> playerCapability.hasBodyReplacementBoolean()).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
            Vec3d vec3d = playerIn.getEyePosition(1.0F);
            Vec3d vec3d1 = playerIn.getLook(1.0F);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 10, vec3d1.y * 10, vec3d1.z * 10);
            AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(10)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 10);
            BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));
            if ((blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) || entityRayTraceResult != null)) {
                return true;
            }
            return false;
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "invisibility", BeNMJutsu.Type.E_RANK, 2, 0.5F, 0, 0, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 40, 0));
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                setGenin(Minecraft.getInstance().player);
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setInvisibilityBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasInvisibilityBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setInvisibilityBoolean(has);
        }, (playerCapability) -> playerCapability.hasInvisibilityBoolean()));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "transformation", BeNMJutsu.Type.E_RANK, 9, 25F, 0, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            TransformationJutsu.TransormationJutsu(playerIn, 25);
        }, (buttonJutsu, playerCapability) -> {
            if (Minecraft.getInstance().currentScreen instanceof AbstractJutsuScreen) {
                setGenin(Minecraft.getInstance().player);
                boolean didBuy = buttonJutsu.doJutsuPress((AbstractJutsuScreen) Minecraft.getInstance().currentScreen);
                if (didBuy) {
                    playerCapability.setInvisibilityBoolean(true);
                    buttonJutsu.sendPackets(buttonJutsu.getJutsuName(), true);
                }
            }
        }, (buttonJutsu, playerCapability) -> {
            buttonJutsu.setHasJutsu(playerCapability.hasInvisibilityBoolean());
        }, (playerCapability, has) -> {
            playerCapability.setInvisibilityBoolean(has);
        }, (playerCapability) -> playerCapability.hasInvisibilityBoolean()).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> {
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

    public static void setGenin(PlayerEntity player)
    {
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        playerc.setShinobiLevel(1);
        NetworkLoader.INSTANCE.sendToServer(new PacketShinobiLevel(playerc.returnShinobiLevel(), false));
    }
}
