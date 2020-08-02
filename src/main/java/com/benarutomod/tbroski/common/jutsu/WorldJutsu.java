package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class WorldJutsu {

    public static final int MatterRepulsionJutsuID = 26;
    public static void MatterRepulsionJutsu(PlayerEntity playerIn, int chakraAmount, int velocity) //velocity, default should be around 1.0F
    {
        LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
        Vec3d vec = playerIn.getPositionVector();
        Vec3d vec3 = new Vec3d(vec.x,vec.y + playerIn.getEyeHeight(),vec.z);
        Vec3d vec3a = playerIn.getLook(1.0F);
        Vec3d vec3b = vec3.add(vec3a.getX() * 64, vec3a.getY() *  64, vec3a.getZ() *  64);
        BlockRayTraceResult blockRayTraceResult = playerIn.world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE,  RayTraceContext.FluidMode.NONE, playerIn));

        if (blockRayTraceResult != null && !playerIn.world.isAirBlock(blockRayTraceResult.getPos()) && playerIn.world.getBlockState(blockRayTraceResult.getPos()).getBlockHardness(playerIn.world, blockRayTraceResult.getPos()) <= 0.5F && playercap.returnChakra() >= (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
            playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
            BlockPos pos = blockRayTraceResult.getPos();
            FallingBlockEntity blockEntity = new FallingBlockEntity(playerIn.world, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, playerIn.world.getBlockState(pos));
            float yaw = playerIn.rotationYaw;
            float pitch = 0;
            double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
            double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * velocity;
            double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * velocity;
            blockEntity.setVelocity(motionX, motionY + 0.6D, motionZ);
            playerIn.world.addEntity(blockEntity);
            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Matter Repulsion Technique! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
        }
        else {
            playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
        }
    }
}
