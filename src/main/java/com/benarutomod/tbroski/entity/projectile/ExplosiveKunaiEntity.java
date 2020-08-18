package com.benarutomod.tbroski.entity.projectile;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class ExplosiveKunaiEntity extends ProjectileItemEntity {

    public ExplosiveKunaiEntity(EntityType<? extends ExplosiveKunaiEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ExplosiveKunaiEntity(World worldIn, LivingEntity throwerIn) {
        super(EntityInit.EXPLOSIVE_KUNAI.get(), throwerIn, worldIn);
    }

    @OnlyIn(Dist.CLIENT)
    public ExplosiveKunaiEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.EXPLOSIVE_KUNAI.get(), x, y, z, worldIn);
    }

    protected Item getDefaultItem() {
        return ItemInit.EXPLOSIVE_KUNAI.get();
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            System.out.println(Config.SERVER.entityWorldDamage.get());
            if (result.getType() == RayTraceResult.Type.ENTITY) {
                Entity entity = ((EntityRayTraceResult)result).getEntity();
                if (this.getThrower() instanceof PlayerEntity || Config.SERVER.entityWorldDamage.get()) {
                    world.createExplosion(entity, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 2F, Explosion.Mode.BREAK);
                }
                else {
                    entity.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 0.7F, 0.4F);
                    entity.addVelocity(entity.getMotion().getX() * 1.4F, entity.getMotion().getY() + 0.3D, entity.getMotion().getZ() * 1.4F);
                }
                entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)5);
            }
            else if (result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos block = ((BlockRayTraceResult)result).getPos();
                if (this.getThrower() instanceof PlayerEntity || Config.SERVER.entityWorldDamage.get()) {
                    world.createExplosion(this, block.getX(), block.getY(), block.getZ(), 2F, Explosion.Mode.BREAK);
                }
                else {
                    world.playSound(null, block, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.7F, 0.4F);
                    Particles.addParticles(block, Minecraft.getInstance().world, ParticleTypes.EXPLOSION, 10);
                }
            }
            this.world.setEntityState(this, (byte)3);
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
