package com.benarutomod.tbroski.entity;

import com.benarutomod.tbroski.api.internal.dojutsu.BeNMSharingan;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.SusanooPickUpItemScreen;
import com.benarutomod.tbroski.init.DojutsuInit;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketSpawnEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public class EtherealItemEntity extends ItemEntity {

    public EtherealItemEntity(EntityType<? extends ItemEntity> p_i50217_1_, World p_i50217_2_) {
        super(p_i50217_1_, p_i50217_2_);
    }

    public EtherealItemEntity(World worldIn, double x, double y, double z) {
        super(EntityInit.ETHEREAL_ITEM.get(), worldIn);
        this.setPosition(x, y, z);
        this.rotationYaw = this.rand.nextFloat() * 360.0F;
        this.setMotion(this.rand.nextDouble() * 0.2D - 0.1D, 0.2D, this.rand.nextDouble() * 0.2D - 0.1D);
    }

    public EtherealItemEntity(World worldIn, double x, double y, double z, ItemStack stack) {
        super(EntityInit.ETHEREAL_ITEM.get(), worldIn);
        this.setPosition(x, y, z);
        this.rotationYaw = this.rand.nextFloat() * 360.0F;
        this.setMotion(this.rand.nextDouble() * 0.2D - 0.1D, 0.2D, this.rand.nextDouble() * 0.2D - 0.1D);
        this.setItem(stack);
        this.lifespan = (stack.getItem() == null ? 6000 : stack.getEntityLifespan(worldIn));
    }

    @Override
    public void onCollideWithPlayer(PlayerEntity player) {
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());
        if ((playerc.returnBodyInfusionToggled()) && ((playerc.returnPlayerLeftDojutsu() instanceof BeNMSharingan && playerc.returnPlayerLeftDojutsu() != DojutsuInit.SHARINGAN) || ((playerc.returnPlayerRightDojutsu() instanceof BeNMSharingan && playerc.returnPlayerRightDojutsu() != DojutsuInit.SHARINGAN)))) {
            if (player.world.isRemote) {
                Minecraft.getInstance().displayGuiScreen(new SusanooPickUpItemScreen(player, this.getItem()));
            }
            this.remove();
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this), new PacketSpawnEntity(this.getEntityId(), this.getUniqueID(), this.getPosX(), this.getPosY(), this.getPosZ(), this.rotationYaw, this.rotationPitch, this.getType(), 0, this.getMotion()));
        return new SSpawnObjectPacket(this);
    }
}
