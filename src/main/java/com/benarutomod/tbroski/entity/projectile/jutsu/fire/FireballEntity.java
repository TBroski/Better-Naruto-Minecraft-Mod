package com.benarutomod.tbroski.entity.projectile.jutsu.fire;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.api.entity.jutsu.nature.AbstractFireJutsuEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class FireballEntity extends AbstractFireJutsuEntity {

    public FireballEntity(EntityType<? extends FireballEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public FireballEntity(World worldIn, LivingEntity throwerIn, boolean isBlueFire) {
        super(EntityInit.FIREBALL_JUTSU.get(), throwerIn, worldIn, isBlueFire);
    }

    protected Item getDefaultItem() {
        return ItemInit.FIREBALL_JUTSU.get();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        super.onImpact(result);
        if (!this.world.isRemote) {
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                entity.setFire(3);
                entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)6);
            }
            else if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos pos = ((BlockRayTraceResult)result).getPos();
                if (Config.COMMON.playerWorldDamage.get() && !this.world.isRemote && world.getBlockState(pos).canEntityDestroy(world, pos, getThrower()))
                {
                    world.getBlockState(pos).isBurning(world, pos);
                    //this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
                }
            }

            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0F;
    }

    @Override
    public String getAffiliatedJutsuName() {
        return "fireball";
    }
}
