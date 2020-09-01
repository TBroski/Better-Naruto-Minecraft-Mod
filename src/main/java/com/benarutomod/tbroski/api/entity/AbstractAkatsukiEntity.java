package com.benarutomod.tbroski.api.entity;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

public abstract class AbstractAkatsukiEntity extends AbstractShinobiEntity {

    public abstract ItemStack getMainHandItemStack();
    protected AbstractAkatsukiEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        CompoundNBT nbt = new CompoundNBT();
        ItemStack headband = new ItemStack(ItemInit.HEADBAND_HELMET.get());
        headband.setTag(nbt);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, this.getMainHandItemStack());
        this.setItemStackToSlot(EquipmentSlotType.HEAD, headband);
        this.setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack(ItemInit.AKATSUKI_CHESTPLATE.get()));
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (cause.getTrueSource() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getTrueSource();
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

            if (playercap.returnShinobiLevel() < 3) {
                playercap.setShinobiLevel(3);
                AdvancementHelper.grantAdvancement(player, Main.MODID + ":shinobi/jonin");
            }
        }
    }
}
