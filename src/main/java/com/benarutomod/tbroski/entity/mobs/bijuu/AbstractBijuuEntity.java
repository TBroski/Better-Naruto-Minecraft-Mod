package com.benarutomod.tbroski.entity.mobs.bijuu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.enums.Nature;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import java.awt.*;

public abstract class AbstractBijuuEntity extends CreatureEntity {

    private final ServerBossInfo bossInfo = (ServerBossInfo) new ServerBossInfo(this.getDisplayName(), this.getChakraColor().getBossColor(), BossInfo.Overlay.PROGRESS).setCreateFog(true).setDarkenSky(true);

    public abstract BijuuColor getChakraColor();
    public abstract Nature[] getBijuuNatures();

    protected AbstractBijuuEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
        for (Nature nature : this.getBijuuNatures()) {
            if (nature.getRequirements() != null) {
                System.out.println("Bijuu gives non-basic nature, errors will occure! To give non-basic natures, use multiple basic natures respective to the wanted nature.");
            }
        }
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
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
            player.sendMessage(new StringTextComponent("+20 BeNM Points! " + "Total: " + playercap.returnBeNMPoints()));
        }
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
