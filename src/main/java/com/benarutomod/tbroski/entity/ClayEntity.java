package com.benarutomod.tbroski.entity;

import com.benarutomod.tbroski.init.EntityInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.PrioritizedGoal;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Iterator;
import java.util.UUID;

public class ClayEntity extends CreatureEntity {

    private UUID owner;
    private Entity entityToRepresent;
    private int explosionAmount;


    public ClayEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ClayEntity(World worldIn, EntityType morphedEntity, UUID owner, int explosionAmount) {
        super(EntityInit.CLAY_ENTITY.get(), worldIn);
        this.entityToRepresent = morphedEntity.create(worldIn);
        this.owner = owner;
        this.explosionAmount = explosionAmount;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        if (this.getEntityToRepresent() instanceof MobEntity) {
            Iterator iterator = ((MobEntity) this.getEntityToRepresent()).goalSelector.getRunningGoals().iterator();
            while (iterator.hasNext()) {
                PrioritizedGoal goal = (PrioritizedGoal) iterator.next();
                this.goalSelector.addGoal(goal.getPriority(), goal);
                System.out.println(goal.getPriority());
                System.out.println(goal);
            }
        }
    }


    @Override
    public void livingTick() {
        super.livingTick();
        System.out.println("CaLlEd");
    }

    public Entity getEntityToRepresent() {
        return entityToRepresent;
    }

    public int getExplosionAmount() {
        return explosionAmount;
    }

    public UUID getOwnerId() {
        return owner;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.getOwnerId() != null) {
            compound.put("owner", NBTUtil.writeUniqueId(this.getOwnerId()));
        }
        compound.putInt("explosion_amount", this.getExplosionAmount());
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
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2.0D);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
    }
}
