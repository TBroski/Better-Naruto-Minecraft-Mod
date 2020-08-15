package com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.MudMoatEntity;
import com.benarutomod.tbroski.api.entity.AbstractAkatsukiEntity;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.EntityInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class KakuzuEntity extends AbstractAkatsukiEntity {

    public KakuzuEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Kakuzu"));
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        Entity lightning_masked = new LightningStyleMaskedAnimalEntity(EntityInit.LIGHTNING_STYLE_MASKED_ANIMAL.get(), this.world);
        lightning_masked.setPositionAndUpdate(this.getPosX(), this.getPosY(), this.getPosZ());
        Entity fire_masked = new FireStyleMaskedAnimalEntity(EntityInit.FIRE_STYLE_MASKED_ANIMAL.get(), this.world);
        fire_masked.setPositionAndUpdate(this.getPosX(), this.getPosY(), this.getPosZ());
        Entity wind_masked = new WindStyleMaskedAnimalEntity(EntityInit.WIND_STYLE_MASKED_ANIMAL.get(), this.world);
        wind_masked.setPositionAndUpdate(this.getPosX(), this.getPosY(), this.getPosZ());
        Entity water_masked = new WaterStyleMaskedAnimalEntity(EntityInit.WATER_STYLE_MASKED_ANIMAL.get(), this.world);
        water_masked.setPositionAndUpdate(this.getPosX(), this.getPosY(), this.getPosZ());
        this.world.addEntity(lightning_masked);
        this.world.addEntity(fire_masked);
        this.world.addEntity(wind_masked);
        this.world.addEntity(water_masked);
        this.getPersistentData().putInt("lightning_masked_entity", lightning_masked.getEntityId());
        this.getPersistentData().putInt("fire_masked_entity", fire_masked.getEntityId());
        this.getPersistentData().putInt("wind_masked_entity", wind_masked.getEntityId());
        this.getPersistentData().putInt("water_masked_entity", water_masked.getEntityId());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 15.0F));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, this.getSpeed(), 20, 20F));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public ItemStack getMainHandItemStack() {
        return Items.AIR.getDefaultInstance();
    }

    @Override
    public BeNMClan getClan() {
        return ClanInit.NULL;
    }

    @Override
    public double getSpeed() {
        return 0.5D;
    }

    @Override
    public int deathBeNMPoints() {
        return 7;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        MudMoatEntity jutsu = new MudMoatEntity(this.world, this);
        double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
        double d1 = target.getPosX() - this.getPosX();
        double d3 = target.getPosZ() - this.getPosZ();
        jutsu.shoot(d1, d0 - jutsu.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 1.5F, 4.0F);
        this.world.addEntity(jutsu);
    }


    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(3D);
    }

    public static void cancelDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof KakuzuEntity) {
            Entity lightning_masked = event.getEntityLiving().getEntityWorld().getEntityByID(event.getEntityLiving().getPersistentData().getInt("lightning_masked_entity"));
            Entity fire_masked = event.getEntityLiving().getEntityWorld().getEntityByID(event.getEntityLiving().getPersistentData().getInt("fire_masked_entity"));
            Entity wind_masked = event.getEntityLiving().getEntityWorld().getEntityByID(event.getEntityLiving().getPersistentData().getInt("wind_masked_entity"));
            Entity water_masked = event.getEntityLiving().getEntityWorld().getEntityByID(event.getEntityLiving().getPersistentData().getInt("water_masked_entity"));
            if ((lightning_masked != null && lightning_masked.isAlive()) || (fire_masked != null && fire_masked.isAlive()) || (wind_masked != null && wind_masked.isAlive()) || (water_masked != null && water_masked.isAlive())) {
                event.setCanceled(true);
            }
        }
    }
}
