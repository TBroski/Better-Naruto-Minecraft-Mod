package com.benarutomod.tbroski.entity.shinobi;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

public abstract class AbstractShinobiEntity extends MonsterEntity implements IRangedAttackMob {

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
        }
    }
}
