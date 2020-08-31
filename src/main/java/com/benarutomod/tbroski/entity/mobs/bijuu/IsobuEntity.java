package com.benarutomod.tbroski.entity.mobs.bijuu;

import com.benarutomod.tbroski.api.enums.Nature;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.world.BossInfo;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.awt.*;

public class IsobuEntity extends AbstractBijuuEntity {

    public IsobuEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathPriority(PathNodeType.WATER, 0.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, this.getSpeed(), 5));
    }

    @Override
    public double getSpeed() {
        return 1.2;
    }

    @Override
    public BijuuColor getChakraColor() {
        return new BijuuColor(new Color(111, 129, 168), BossInfo.Color.BLUE);
    }

    @Override
    public Nature[] getBijuuNatures() {
        return new Nature[] {Nature.WATER};
    }

    @Override
    public BijuuAttributes[] getBijuuAttributes() {
        return new BijuuAttributes[] {BijuuAttributes.WATER_BREATHING};
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.WATER;
    }

    @Override
    public boolean isNotColliding(IWorldReader worldIn) {
        return worldIn.func_226668_i_(this);
    }

    @Override
    public boolean isPushedByWater() {
        return false;
    }

    @Override
    public int getTalkInterval() {
        return 120;
    }
}
