package com.benarutomod.tbroski.entity.mobs.bijuu;

import com.benarutomod.tbroski.api.enums.Nature;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import java.awt.*;

public class ShukakuEntity extends AbstractBijuuEntity {

    public ShukakuEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public double getSpeed() {
        return 0.20;
    }

    @Override
    public BijuuColor getChakraColor() {
        return new BijuuColor(new Color(193, 176, 148), BossInfo.Color.YELLOW);
    }

    @Override
    public Nature[] getBijuuNatures() {
        return new Nature[] {Nature.EARTH, Nature.WIND};
    }

    @Override
    public BijuuAttributes[] getBijuuAttributes() {
        return new BijuuAttributes[0];
    }
}
