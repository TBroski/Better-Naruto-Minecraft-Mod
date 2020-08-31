package com.benarutomod.tbroski.api.entity.jutsu.nature;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.entity.jutsu.AbstractNinjutsuEntity;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBlueFireSync;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class AbstractFireJutsuEntity extends AbstractNinjutsuEntity {

    protected boolean isBlueFire;

    public AbstractFireJutsuEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public AbstractFireJutsuEntity(EntityType<? extends ProjectileItemEntity> type, double x, double y, double z, World worldIn) {
        super(type, x, y, z, worldIn);
    }

    public AbstractFireJutsuEntity(EntityType<? extends ProjectileItemEntity> type, LivingEntity livingEntityIn, World worldIn, boolean isBlueFire) {
        super(type, livingEntityIn, worldIn);
        this.isBlueFire = isBlueFire;
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (result.getType() == RayTraceResult.Type.ENTITY && !this.world.isRemote && this.getThrower() instanceof PlayerEntity) {
            PlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
            if (playerc.returnBodyInfusionToggled() && this.isBlueFire()) {
                Entity entity = ((EntityRayTraceResult) result).getEntity();
                entity.attackEntityFrom(DamageSource.IN_FIRE, 4F);
            }
        }
    }

    public boolean isBlueFire() {
        return this.isBlueFire;
    }

    public void setBlueFire(boolean blueFire) {
        isBlueFire = blueFire;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isRemote) {
            NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this), new PacketBlueFireSync(this.getEntityId(), this.isBlueFire()));
        }
    }
}
