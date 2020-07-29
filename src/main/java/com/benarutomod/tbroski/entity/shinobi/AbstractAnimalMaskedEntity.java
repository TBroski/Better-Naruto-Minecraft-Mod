package com.benarutomod.tbroski.entity.shinobi;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.world.World;

public class AbstractAnimalMaskedEntity extends MobEntity {
    protected AbstractAnimalMaskedEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }
}
