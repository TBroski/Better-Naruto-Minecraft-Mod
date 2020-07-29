package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.*;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class BodyReplacementJutsu {


    public static final int BasicBodyReplacementJutsuID = 2;
    public static void BasicBodyReplacementJutsu(PlayerEntity playerIn, int chakraAmount)
    {
        if (!playerIn.getEntityWorld().isRemote) {
            LazyOptional<IPlayerHandler> capabilities = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            if (playercap.returnChakra() > (chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01))) {
                RayTraceResult rayTraceResult = Minecraft.getInstance().objectMouseOver;
                Vec3d vec3d = playerIn.getEyePosition(1.0F);
                Vec3d vec3d1 = playerIn.getLook(1.0F);
                Vec3d vec3d2 = vec3d.add(vec3d1.x * 10, vec3d1.y * 10, vec3d1.z * 10);
                AxisAlignedBB axisalignedbb = playerIn.getBoundingBox().expand(vec3d1.scale(10)).grow(1.0D, 1.0D, 1.0D);
                EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerIn, vec3d, vec3d2, axisalignedbb, $ -> !playerIn.isSpectator() && playerIn.canBeCollidedWith(), 10);
                if (rayTraceResult.getType() == RayTraceResult.Type.BLOCK) {
                    playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                    BlockPos pos = ((BlockRayTraceResult) rayTraceResult).getPos();
                    BlockState block = playerIn.getEntityWorld().getBlockState(pos);
                    playerIn.getEntityWorld().removeBlock(pos, false);
                    playerIn.getEntityWorld().setBlockState(playerIn.getPosition(), block);
                    playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                    if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Body Replacement Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
                } else if (entityRayTraceResult != null) {
                    playercap.addChakra((float) (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)));
                    BlockPos pos = entityRayTraceResult.getEntity().getPosition();
                    entityRayTraceResult.getEntity().setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                    playerIn.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                    if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent("Body Replacement Jutsu! " + (-chakraAmount * ((100 - playercap.returnChakraControl()) * 0.01)) + " Chakra"));
                }
            }
            else {
                playerIn.sendMessage(new StringTextComponent("Not Enough Chakra"));
            }
        }
    }
}
