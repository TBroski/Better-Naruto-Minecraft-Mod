package com.benarutomod.tbroski.entity.mobs.bijuu;

import com.benarutomod.tbroski.api.enums.Nature;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import java.awt.*;

public class MatatabiEntity extends AbstractBijuuEntity {

    public MatatabiEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public double getSpeed() {
        return 0.02;
    }

    @Override
    public BijuuColor getChakraColor() {
        return new BijuuColor(Color.BLUE, BossInfo.Color.BLUE);
    }

    @Override
    public Nature[] getBijuuNatures() {
        Nature[] natures = new Nature[1];
        natures[0] = Nature.FIRE;
        return natures;
    }
}
