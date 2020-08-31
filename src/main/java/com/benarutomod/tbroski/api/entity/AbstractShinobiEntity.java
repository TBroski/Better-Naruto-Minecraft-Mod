package com.benarutomod.tbroski.api.entity;

import com.benarutomod.tbroski.api.internal.dojutsu.BeNMDojutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.interfaces.IDojutsuEntity;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.ItachiEntity;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class AbstractShinobiEntity extends MonsterEntity implements IRangedAttackMob {

    public static final DataParameter<String> LEFT_EYE = EntityDataManager.createKey(AbstractShinobiEntity.class, DataSerializers.STRING);
    public static final DataParameter<String> RIGHT_EYE = EntityDataManager.createKey(AbstractShinobiEntity.class, DataSerializers.STRING);

    public abstract BeNMClan getClan();
    public abstract double getSpeed();
    public abstract int deathBeNMPoints();
    protected AbstractShinobiEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(ItemInit.KUNAI.get()));
        this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(ItemInit.HEADBAND_HELMET.get()));
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (cause.getTrueSource() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getTrueSource();
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

            playercap.addBeNMPoints(this.deathBeNMPoints());
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
            player.sendMessage(new StringTextComponent("+" + this.deathBeNMPoints() + " BeNM Points! " + "Total: " + playercap.returnBeNMPoints()));
            if (playercap.returnPlayerBijuuLevel() < 1 && this.deathBeNMPoints() >= 4) {
                playercap.setPlayerBijuuLevel(1);
            }
            else if (playercap.returnPlayerBijuuLevel() < 2 && this.deathBeNMPoints() >= 8) {
                playercap.setPlayerBijuuLevel(2);
            }
            else if (playercap.returnPlayerBijuuLevel() < 3 && this.deathBeNMPoints() >= 12) {
                playercap.setPlayerBijuuLevel(3);
            }
        }
        if (this instanceof IDojutsuEntity) {
            this.world.setBlockState(this.getPosition(), BlockInit.DOJUTSU_SKULL.get().getDefaultState());
            TileEntity tileEntity = this.world.getTileEntity(this.getPosition());
            if (tileEntity instanceof DojutsuSkullTileEntity) {
                DojutsuSkullTileEntity dojutsuSkullTileEntity = (DojutsuSkullTileEntity) tileEntity;
                dojutsuSkullTileEntity.setLivingEntity(this);
            }
        }
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(LEFT_EYE, this.registeredLeftEye().getString());
        this.dataManager.register(RIGHT_EYE, this.registeredRightEye().getString());
    }

    public BeNMDojutsu getRightDojustsu() {
        return DojutsuHelper.getDojutsuFromString(this.dataManager.get(RIGHT_EYE));
    }
    public BeNMDojutsu getLeftDojustsu() {
        return DojutsuHelper.getDojutsuFromString(this.dataManager.get(LEFT_EYE));
    }
    public void setRightDojustsu(BeNMDojutsu rightEye) {
        this.dataManager.set(RIGHT_EYE, rightEye.getString());
    }
    public void setLeftDojustsu(BeNMDojutsu leftEye) {
        this.dataManager.set(LEFT_EYE, leftEye.getString());
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

    protected BeNMDojutsu registeredLeftEye() {
        return DojutsuInit.NULL;
    }

    protected BeNMDojutsu registeredRightEye() {
        return DojutsuInit.NULL;
    }
}
