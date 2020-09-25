package com.benarutomod.tbroski.entity;

import com.benarutomod.tbroski.entity.ai.ExplodeGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketClayEntitySync;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.UUID;

public class ClayEntity extends MonsterEntity {

    private UUID owner;
    private Entity entityToRepresent;
    private int explosionAmount;
    private int ticksExistedRunTime;

    public ClayEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ClayEntity(World worldIn, EntityType morphedEntity, UUID owner, int explosionLevel) {
        super(EntityInit.CLAY_ENTITY.get(), worldIn);
        this.setCustomNameVisible(false);
        this.entityToRepresent = morphedEntity.create(worldIn);
        this.owner = owner;
        this.explosionAmount = explosionLevel;
        this.registerGoals();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 0.8D));
        this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
/*        if (this.getEntityToRepresent() instanceof MobEntity) {
            Iterator<PrioritizedGoal> goalIterator = ((MobEntity) this.getEntityToRepresent()).goalSelector.goals.iterator();
            while (goalIterator.hasNext()) {
                PrioritizedGoal goal = goalIterator.next();
                if (goal.getGoal() instanceof SwimGoal) {
                    this.goalSelector.addGoal(goal.getPriority(), new SwimGoal(this));
                }
                else if (goal.getGoal() instanceof CreeperSwellGoal) {
                    this.goalSelector.addGoal(goal.getPriority(), new ExplodeGoal(this));
                }
            }
            Iterator<PrioritizedGoal> targetIterator = ((MobEntity) this.getEntityToRepresent()).targetSelector.goals.iterator();
            while (targetIterator.hasNext()) {
                PrioritizedGoal target = targetIterator.next();
                this.targetSelector.addGoal(target.getPriority(), target.getGoal());
            }
        }*/
    }

    public Entity getEntityToRepresent() {
        return entityToRepresent;
    }

    public void setEntityToRepresent(Entity entityToRepresent) {
        this.entityToRepresent = entityToRepresent;
    }

    public int getExplosionLevel() {
        return explosionAmount;
    }

    public UUID getOwnerId() {
        return owner;
    }

    public void setExplosionLevel(int explosionLevel) {
        this.explosionAmount = explosionLevel;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.getOwnerId() != null) {
            compound.put("owner", NBTUtil.writeUniqueId(this.getOwnerId()));
        }
        compound.putInt("explosion_amount", this.getExplosionLevel());
        if (this.getEntityToRepresent().getType().getRegistryName().toString() != null) {
            compound.putString("representing_entity", this.getEntityToRepresent().getType().getRegistryName().toString());
        }
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("owner", 10)) {
            this.owner = NBTUtil.readUniqueId(compound.getCompound("owner"));
        }
        if (compound.contains("representing_entity")) {
            for (EntityType type : ForgeRegistries.ENTITIES.getValues()) {
                if (type.getRegistryName().toString().equalsIgnoreCase(compound.getString("representing_entity"))) {
                    this.entityToRepresent = type.create(this.getEntityWorld());
                    break;
                }
            }
        }
        this.explosionAmount = compound.getInt("explosion_amount");
        this.registerGoals();
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (!this.world.isRemote()) {
            this.sendPacket();
        }
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote && this.ticksExistedRunTime <= 10) {
            this.ticksExistedRunTime++;
            if (this.ticksExistedRunTime <= 10) {
                this.sendPacket();
            }
        }
        if (!this.world.isRemote && this.ticksExisted % 200 == 0) {
            for (PrioritizedGoal prioritizedGoal : this.targetSelector.goals) {
                System.out.println(prioritizedGoal.getGoal());
                System.out.println(prioritizedGoal.isRunning());
            }
            for (PrioritizedGoal prioritizedGoal : this.goalSelector.goals) {
                System.out.println(prioritizedGoal.getGoal());
                System.out.println(prioritizedGoal.isRunning());
            }
        }
    }

    @Override
    protected boolean isDespawnPeaceful() {
        return false;
    }

    private void sendPacket() {
        NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this), new PacketClayEntitySync(this.getEntityId(), this.getEntityToRepresent().getType().getRegistryName().toString(), this.getExplosionLevel()));
    }
}
