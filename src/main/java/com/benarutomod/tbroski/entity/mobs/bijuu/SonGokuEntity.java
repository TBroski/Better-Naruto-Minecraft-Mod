package com.benarutomod.tbroski.entity.mobs.bijuu;

import com.benarutomod.tbroski.api.enums.Nature;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;

import java.awt.*;

public class SonGokuEntity extends AbstractBijuuEntity {

    public SonGokuEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        for (Nature nature : this.getBijuuNatures()) {
            System.out.println(nature.getName());
        }
    }

    @Override
    public double getSpeed() {
        return 0.20;
    }

    @Override
    public BijuuColor getChakraColor() {
        return new BijuuColor(Color.RED, BossInfo.Color.RED);
    }

    @Override
    public Nature[] getBijuuNatures() {
        return new Nature[] {Nature.FIRE, Nature.EARTH};
    }

    @Override
    public BijuuAttributes[] getBijuuAttributes() {
        return new BijuuAttributes[0];
    }
}
