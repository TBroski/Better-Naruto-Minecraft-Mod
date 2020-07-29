package com.benarutomod.tbroski.tileentity;

import com.benarutomod.tbroski.init.TileEntityInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.registries.ForgeRegistries;

public class DojutsuSkullTileEntity extends TileEntity {

    private LivingEntity livingEntity;

    public DojutsuSkullTileEntity() {
        super(TileEntityInit.DOJUTSU_SKULL.get());
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }
    public void setLivingEntity(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.pos, 4, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        if (this.livingEntity != null) {
            compound.putString("entitytype", this.livingEntity.getType().getRegistryName().toString());
        }
        return compound;
    }
    @Override
    public void read(CompoundNBT compound) {
        super.read(compound);
        if (compound.contains("entitytype")) {
            for (EntityType type : ForgeRegistries.ENTITIES.getValues()) {
                if (type.getRegistryName().toString().equalsIgnoreCase(compound.getString("entitytype"))) {
                    Entity entity = type.create(this.world);
                    if (entity instanceof LivingEntity) {
                        entity.getPersistentData().merge(this.getTileData());
                        this.setLivingEntity((LivingEntity) entity);
                        this.markDirty();
                    }
                }
            }
        }
    }
}
