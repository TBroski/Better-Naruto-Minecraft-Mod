package com.benarutomod.tbroski.entity.projectile.jutsu.sharingan;

import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningArrowEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class AmaterasuJutsuEntity extends AbstractSharinganProjectileEntity {

    public AmaterasuJutsuEntity(EntityType<? extends AmaterasuJutsuEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AmaterasuJutsuEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.AMATERASU_JUTSU.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public AmaterasuJutsuEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.AMATERASU_JUTSU.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return Items.AIR;
    }

    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY && !this.world.isRemote) {
            Entity entity = ((EntityRayTraceResult)result).getEntity();
            if (((EntityRayTraceResult)result).getEntity() instanceof LivingEntity)
            {
                entity.getPersistentData().putBoolean("amaterasufire", true);
                entity.getPersistentData().putInt("amaterasuconnection", this.getThrower().getEntityId());
                entity.getPersistentData().putInt("amaterasuconnectionx", 0);
                entity.getPersistentData().putInt("amaterasuconnectiony", -1);
                entity.getPersistentData().putInt("amaterasuconnectionz", 0);
            }
        }

        if (!this.world.isRemote) {
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    protected float getGravityVelocity() {
        return 0;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
