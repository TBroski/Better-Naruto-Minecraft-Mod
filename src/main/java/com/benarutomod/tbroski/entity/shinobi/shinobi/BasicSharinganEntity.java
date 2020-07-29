package com.benarutomod.tbroski.entity.shinobi.shinobi;

import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.common.BeNMDojutsu;
import com.benarutomod.tbroski.entity.ai.DojutsuAttackGoal;
import com.benarutomod.tbroski.entity.ai.DojutsuConnectionGoal;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.entity.projectile.KunaiEntity;
import com.benarutomod.tbroski.entity.shinobi.AbstractShinobiEntity;
import com.benarutomod.tbroski.entity.shinobi.ISharinganEntity;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BasicSharinganEntity extends AbstractShinobiEntity implements ISharinganEntity {

    private static final DataParameter<String> LEFT_EYE = EntityDataManager.createKey(BasicSharinganEntity.class, DataSerializers.STRING);
    private static final DataParameter<String> RIGHT_EYE = EntityDataManager.createKey(BasicSharinganEntity.class, DataSerializers.STRING);

    public BasicSharinganEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LEFT_EYE, "sharingan");
        this.dataManager.register(RIGHT_EYE, "sharingan");
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        this.world.setBlockState(this.getPosition(), BlockInit.DOJUTSU_SKULL.get().getDefaultState());
        TileEntity tileEntity = this.world.getTileEntity(this.getPosition());
        if (tileEntity instanceof DojutsuSkullTileEntity) {
            DojutsuSkullTileEntity dojutsuSkullTileEntity = (DojutsuSkullTileEntity) tileEntity;
            dojutsuSkullTileEntity.setLivingEntity(this);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 15.0F));
        this.goalSelector.addGoal(2, new DojutsuConnectionGoal<>(this, true,15.0F));
        this.goalSelector.addGoal(3, new DojutsuAttackGoal<>(this, 2400));
        this.goalSelector.addGoal(3, new RangedAttackGoal(this, this.getSpeed(), 100, 5F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public BeNMClan getClan() {
        return ClanInit.UCHIHA;
    }

    @Override
    public double getSpeed() {
        return 0.4D;
    }

    @Override
    public int deathBeNMPoints() {
        return 2;
    }

    @Override
    public BeNMDojutsu rightDojustsu() {
        return DojutsuHelper.getDojutsuFromString(this.dataManager.get(RIGHT_EYE));
    }
    @Override
    public BeNMDojutsu leftDojustsu() {
        return DojutsuHelper.getDojutsuFromString(this.dataManager.get(LEFT_EYE));
    }
    @Override
    public void setRightDojustsu(BeNMDojutsu rightEye) {
        this.dataManager.set(RIGHT_EYE, rightEye.getString());
    }
    @Override
    public void setLeftDojustsu(BeNMDojutsu leftEye) {
        this.dataManager.set(LEFT_EYE, leftEye.getString());
    }

    @Override
    public float ticksBeforeConnection() {
        return 60F;
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
        livingEntityIn.addPotionEffect(new EffectInstance(Effects.NAUSEA, 20));
    }

    @Override
    public int eyeSlot() {
        return 3;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        KunaiEntity kunai = new KunaiEntity(this.world, this);
        double d0 = target.getPosY() + (double) target.getEyeHeight() - 1.1;
        double d1 = target.getPosX() - this.getPosX();
        double d3 = target.getPosZ() - this.getPosZ();
        kunai.shoot(d1, d0 - kunai.getPosY() + (double) MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F, d3, 2.0F, 6.0F);
        this.world.addEntity(kunai);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putString("lefteye", this.dataManager.get(LEFT_EYE));
        compound.putString("righteye", this.dataManager.get(RIGHT_EYE));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(LEFT_EYE, compound.getString("lefteye"));
        this.dataManager.set(RIGHT_EYE, compound.getString("righteye"));
    }
}
