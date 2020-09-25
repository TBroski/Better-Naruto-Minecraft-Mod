package com.benarutomod.tbroski.entity.shinobi.shinobi;

import com.benarutomod.tbroski.api.entity.AbstractShinobiEntity;
import com.benarutomod.tbroski.api.interfaces.ISharinganEntity;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.entity.shinobi.IBrotherEntity;
import com.benarutomod.tbroski.init.ClanInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.EffectInit;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Map;

public class BrotherSharinganEntity extends AbstractShinobiEntity implements ISharinganEntity, IBrotherEntity {

    private static final DataParameter<Integer> PLAYER_ID = EntityDataManager.createKey(BrotherSharinganEntity.class, DataSerializers.VARINT);

    public BrotherSharinganEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(PLAYER_ID, 0);
    }

    public void setBrotherId(int id){
        this.dataManager.set(PLAYER_ID, id);
    }

    public int getBrotherId(){
        return this.dataManager.get(PLAYER_ID);
    }


    @Override
    public BeNMClan getClan() {
        return ClanInit.UCHIHA;
    }

    @Override
    public double getSpeed() {
        return 0.15;
    }

    @Override
    public int deathBeNMPoints() {
        return 10;
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {

    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(7D);
    }

    @Override
    protected BeNMDojutsu registeredLeftEye() {
        return DojutsuInit.MANGEKYOU_SHARINGAN;
    }

    @Override
    protected BeNMDojutsu registeredRightEye() {
        return DojutsuInit.MANGEKYOU_SHARINGAN;
    }

    @Override
    public float ticksBeforeConnection() {
        return 0;
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
        livingEntityIn.addPotionEffect(new EffectInstance(EffectInit.TSUKUYOMI.get(), 300));
    }

    @Override
    public int eyeSlot() {
        return 5;
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("playerid", this.getBrotherId());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.setBrotherId(compound.getInt("playerid"));
    }

    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getLocationSkin() {
        LivingEntity owner = (LivingEntity) this.world.getEntityByID(this.getBrotherId());
        if (owner instanceof PlayerEntity) {
            GameProfile gameProfile = ((PlayerEntity) owner).getGameProfile();
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Minecraft.getInstance().getSkinManager().loadSkinFromCache(gameProfile);
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                return Minecraft.getInstance().getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
            return DefaultPlayerSkin.getDefaultSkin(PlayerEntity.getUUID(gameProfile));
        }
        return DefaultPlayerSkin.getDefaultSkinLegacy(); //DefaultPlayerSkin.getDefaultSkinLegacy();
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (!this.world.isRemote) {
            PlayerEntity brother = ((ServerWorld) this.world).getRandomPlayer();
            this.setCustomName(new StringTextComponent(calculateBrotherName(brother.getDisplayName().getString() + " " + this.getClan().getString().toUpperCase().substring(0, 1) + this.getClan().getString().toLowerCase().substring(1), this.getRNG())));
            this.setCustomNameVisible(true);
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }
}
