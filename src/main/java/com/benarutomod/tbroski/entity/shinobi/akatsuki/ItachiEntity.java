package com.benarutomod.tbroski.entity.shinobi.akatsuki;

import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.entity.ai.DojutsuAttackGoal;
import com.benarutomod.tbroski.entity.ai.DojutsuConnectionGoal;
import com.benarutomod.tbroski.entity.projectile.KunaiEntity;
import com.benarutomod.tbroski.api.entity.AbstractAkatsukiEntity;
import com.benarutomod.tbroski.api.interfaces.ISharinganEntity;
import com.benarutomod.tbroski.init.*;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.Random;

public class ItachiEntity extends AbstractAkatsukiEntity implements ISharinganEntity {

    public ItachiEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        this.setCustomName(new StringTextComponent("Itachi"));
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 15.0F));
        this.goalSelector.addGoal(2, new DojutsuConnectionGoal<>(this, true, 15.0F));
        this.goalSelector.addGoal(3, new DojutsuAttackGoal<>(this, 1200));
        this.goalSelector.addGoal(4, new RangedAttackGoal(this, this.getSpeed(), 20, 20F));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public ItemStack getMainHandItemStack() {
        return ItemInit.KUNAI.get().getDefaultInstance();
    }

    @Override
    public BeNMClan getClan() {
        return ClanInit.UCHIHA;
    }

    @Override
    public double getSpeed() {
        return 0.6D;
    }

    @Override
    public int deathBeNMPoints() {
        return 7;
    }

    @Override
    public float ticksBeforeConnection() {
        return 15F;
    }

    @Override
    public boolean canUseGenjutsuOnPlayer(PlayerEntity playerIn) {
        ItemStack itemstack = playerIn.inventory.armorInventory.get(3);
        if (itemstack.getItem() == Blocks.CARVED_PUMPKIN.asItem()) {
            return false;
        } else {
            Vec3d vec3d = playerIn.getLook(1.0F).normalize();
            Vec3d vec3d1 = new Vec3d(this.getPosX() - playerIn.getPosX(), this.getPosYEye() - playerIn.getPosYEye(), this.getPosZ() - playerIn.getPosZ());
            double d0 = vec3d1.length();
            vec3d1 = vec3d1.normalize();
            double d1 = vec3d.dotProduct(vec3d1);
            return d1 > 1.0D - 0.025D / d0 ? playerIn.canEntityBeSeen(this) : false;
        }
    }

    @Override
    public void attackEntityWithGenjutsuAttack(LivingEntity livingEntityIn) {
        int randomGenjutsu = new Random().nextInt(2);
        if (randomGenjutsu == 1) {
            livingEntityIn.addPotionEffect(new EffectInstance(EffectInit.TSUKUYOMI.get(), 300));
        }
        else if (livingEntityIn.world.getBlockState(livingEntityIn.getPosition()) != Blocks.AIR.getDefaultState()){
            boolean flag = livingEntityIn.world.setBlockState(livingEntityIn.getPosition(), BlockInit.AMATERASU.get().getDefaultState());
            TileEntity tileEntity = livingEntityIn.world.getTileEntity(livingEntityIn.getPosition());
            if (tileEntity != null) {
                tileEntity.getTileData().putInt("amaterasuconnection", livingEntityIn.getEntityId());
            }
            if (!flag) {
                livingEntityIn.getPersistentData().putBoolean("amaterasufire", true);
                livingEntityIn.getPersistentData().putInt("amaterasuconnection", this.getEntityId());
                livingEntityIn.getPersistentData().putInt("amaterasuconnectionx", 0);
                livingEntityIn.getPersistentData().putInt("amaterasuconnectiony", -1);
                livingEntityIn.getPersistentData().putInt("amaterasuconnectionz", 0);
            }
        }
    }

    @Override
    public int eyeSlot() {
        return 5;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        KunaiEntity kunai = new KunaiEntity(this.world, this);
        double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
        double d1 = target.getPosX() - this.getPosX();
        double d3 = target.getPosZ() - this.getPosZ();
        kunai.shoot(d1, d0 - kunai.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 2.5F, 2.0F);
        this.world.addEntity(kunai);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(11D);
    }

    @Override
    protected BeNMDojutsu registeredLeftEye() {
        return DojutsuInit.MANGEKYOU_SHARINGAN;
    }

    @Override
    protected BeNMDojutsu registeredRightEye() {
        return DojutsuInit.MANGEKYOU_SHARINGAN;
    }
}
