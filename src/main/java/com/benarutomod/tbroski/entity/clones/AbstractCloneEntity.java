package com.benarutomod.tbroski.entity.clones;

import com.benarutomod.tbroski.client.renderer.misc.Particles;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Map;

public abstract class AbstractCloneEntity extends CreatureEntity {

    private int tick;

    private static final DataParameter<Integer> PLAYER_ID = EntityDataManager.createKey(AbstractCloneEntity.class, DataSerializers.VARINT);

    public abstract int getPoofTimer();
    public abstract double getBaseHealth();
    protected AbstractCloneEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(PLAYER_ID, 0);
    }

    @Override
    public void livingTick() {
        super.livingTick();
        this.tick += 1;
        if (this.tick >= this.getPoofTimer())
        {
            Particles.addParticles(this, ParticleTypes.CLOUD, 20);
            this.tick = 0;
            this.remove();
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        Particles.addParticles(this, ParticleTypes.CLOUD, 20);
    }

    public void setOwnerID(int id){
        this.dataManager.set(PLAYER_ID, id);
    }


    public int getOwnerID(){
        return this.dataManager.get(PLAYER_ID);
    }


    @OnlyIn(Dist.CLIENT)
    public ResourceLocation getLocationSkin(){
        LivingEntity owner = (LivingEntity) this.world.getEntityByID(this.getOwnerID());
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

    @Override
    protected boolean canDropLoot() {
        return false;
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        LivingEntity owner = (LivingEntity) this.world.getEntityByID(this.getOwnerID());
        if (owner != null) {
            this.setCustomName(owner.getDisplayName());
            this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(owner.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem()));
            this.setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack(owner.getItemStackFromSlot(EquipmentSlotType.OFFHAND).getItem()));
            this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(owner.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem()));
            this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(owner.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem()));
            this.setItemStackToSlot(EquipmentSlotType.LEGS, new ItemStack(owner.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem()));
            this.setItemStackToSlot(EquipmentSlotType.FEET, new ItemStack(owner.getItemStackFromSlot(EquipmentSlotType.FEET).getItem()));
        }
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("playerid", this.dataManager.get(PLAYER_ID));
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(PLAYER_ID, compound.getInt("playerid"));
    }
}
