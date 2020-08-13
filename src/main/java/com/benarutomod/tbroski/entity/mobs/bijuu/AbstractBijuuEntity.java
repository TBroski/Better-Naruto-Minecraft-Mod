package com.benarutomod.tbroski.entity.mobs.bijuu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.enums.Nature;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.TailedBeastBombEntity;
import com.benarutomod.tbroski.event.GlobalEvents;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.PacketBijuuSync;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import java.awt.*;

public abstract class AbstractBijuuEntity extends CreatureEntity implements IRangedAttackMob {

    private final ServerBossInfo bossInfo = (ServerBossInfo) new ServerBossInfo(this.getDisplayName(), this.getChakraColor().getBossColor(), BossInfo.Overlay.PROGRESS).setCreateFog(true).setDarkenSky(true);

    public abstract double getSpeed();
    public abstract BijuuColor getChakraColor();
    public abstract Nature[] getBijuuNatures();

    protected AbstractBijuuEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        for (Nature nature : this.getBijuuNatures()) {
            if (!nature.isBasic()) {
                System.out.println("Bijuu gives non-basic nature " + nature.getName() +", errors will occure! To give non-basic natures, use multiple basic natures respective to the wanted nature.");
            }
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(0, new SwimGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, this.getSpeed(), 50, 35F));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, this.getSpeed()));
        this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractCloneEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        GlobalEvents.bijuuSpawned(this);
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);
        if (cause.getTrueSource() instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity player = (ServerPlayerEntity) cause.getTrueSource();
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

            playercap.addBeNMPoints(20);
            playercap.setPlayerBijuu(this.getType().getRegistryName().toString());
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBijuuSync(player.getEntityId(), this.getType().getRegistryName().toString()));
            player.sendMessage(new StringTextComponent("+20 BeNM Points! " + "Total: " + playercap.returnBeNMPoints()));
        }
    }

    @Override
    public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        TailedBeastBombEntity bomb = new TailedBeastBombEntity(this, this.world);
        double d0 = target.getPosY() + (double) target.getEyeHeight() + 0.1;
        double d1 = target.getPosX() - this.getPosX();
        double d3 = target.getPosZ() - this.getPosZ();
        bomb.shoot(d1, d0 - bomb.getPosY(), d3, 1.3F, 2.0F);
        this.world.addEntity(bomb);
    }

    @Override
    public void addTrackingPlayer(ServerPlayerEntity player) {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void removeTrackingPlayer(ServerPlayerEntity player) {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

    @Override
    public boolean isNonBoss() {
        return false;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500D);
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50D);
    }

    public static class BijuuColor {
        private final Color chakraColor;
        private final BossInfo.Color bossColor;
        public BijuuColor(Color chakraColor, BossInfo.Color bossColor) {
            this.chakraColor = chakraColor;
            this.bossColor = bossColor;
        }
        public Color getChakraColor() {
            return chakraColor;
        }
        public BossInfo.Color getBossColor() {
            return bossColor;
        }
    }
}
