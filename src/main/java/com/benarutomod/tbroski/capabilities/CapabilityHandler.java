package com.benarutomod.tbroski.capabilities;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.jutsu.*;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;

import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.*;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketSetJutsuBoolean;
import com.benarutomod.tbroski.networking.packets.settings.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber
public class CapabilityHandler {

    public static final ResourceLocation CAPABILITY_PLAYER = new ResourceLocation(Main.MODID, "player");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(CAPABILITY_PLAYER, new PlayerProvider());
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {

        if (!event.getWorld().isRemote && event.getEntity() instanceof PlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(6, playercap.hasChakraBoolean(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(1, playercap.hasFireNature(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(2, playercap.hasWaterNature(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(3, playercap.hasEarthNature(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(4, playercap.hasWindNature(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(5, playercap.hasLightningNature(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(7, playercap.hasMagnetNature(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(1, playercap.returnKeybind1(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(2, playercap.returnKeybind2(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(3, playercap.returnKeybind3(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(4, playercap.returnKeybind4(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(5, playercap.returnKeybind5(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(6, playercap.returnKeybind6(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(7, playercap.returnKeybind7(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(8, playercap.returnKeybind8(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketKeybindSet(9, playercap.returnKeybind9(), true));
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketSetJutsuBoolean(jutsu.getCorrelatedPlugin() + "." + jutsu.getName(), jutsu.hasJutsu(playercap), true));
            }
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerEntityAffiliationSync(playercap.returnPlayerEntityAffiliation(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketEyeSlotSync(playercap.returnplayerEyeSlot(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketToggleMessageBoolean(playercap.returnToggleJutsuMessage(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketToggleScrollBoolean(playercap.returnToggleScrollRenderer(), true, player.getEntityId()));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketShinobiLevel(playercap.returnShinobiLevel(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketTaijutsu(playercap.returnTaijutsu(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerDojutsuSync(playercap.returnPlayerLeftDojutsu().getString(), true, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerDojutsuSync(playercap.returnPlayerRightDojutsu().getString(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerBodyModeSync(playercap.returnPlayerBodyMode().getString(), player.getEntityId(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerHasBodyModeSync(1, playercap.returnPlayerCurseMark(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerHasBodyModeSync(2,  playercap.returnPlayerToadSageMode(), true));
        }
    }

        @SubscribeEvent
        public void onPlayerClone(PlayerEvent.Clone event) {
            PlayerEntity player = event.getPlayer();
            LazyOptional<IPlayerHandler> chakra_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler chakra = chakra_cap.orElse(new PlayerCapability());
            LazyOptional<IPlayerHandler> oldchakra_cap = event.getOriginal().getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler oldchakra = oldchakra_cap.orElse(new PlayerCapability());

            chakra.setChakra(oldchakra.returnChakra());
            chakra.setmaxChakra(oldchakra.returnmaxChakra());
            chakra.setcolorChakra(oldchakra.returncolorChakra());
            chakra.setChakraControl(oldchakra.returnChakraControl());
            chakra.setplayerEyeSlot(oldchakra.returnplayerEyeSlot());
            chakra.setjoinWorld(oldchakra.joinWorld());
            chakra.setTaijutsu(oldchakra.returnTaijutsu());
            chakra.setBeNMPoints(oldchakra.returnBeNMPoints());
            chakra.setregenChakra(oldchakra.returnregenChakra());
            chakra.setPlayerEntityAffiliation(oldchakra.returnPlayerEntityAffiliation());
            chakra.setPlayerLeftDojutsu(oldchakra.returnPlayerLeftDojutsu());
            chakra.setPlayerRightDojutsu(oldchakra.returnPlayerRightDojutsu());
            chakra.setShinobiLevel(oldchakra.returnShinobiLevel());
            chakra.setToggleJutsuMessage(oldchakra.returnToggleJutsuMessage());
            chakra.setToggleScrollRenderer(oldchakra.returnToggleScrollRenderer());
            chakra.setPlayerBodyMode(oldchakra.returnPlayerBodyMode());

            chakra.setKeybind1(oldchakra.returnKeybind1());
            chakra.setKeybind2(oldchakra.returnKeybind2());
            chakra.setKeybind3(oldchakra.returnKeybind3());
            chakra.setKeybind4(oldchakra.returnKeybind4());
            chakra.setKeybind5(oldchakra.returnKeybind5());
            chakra.setKeybind6(oldchakra.returnKeybind6());
            chakra.setKeybind7(oldchakra.returnKeybind7());
            chakra.setKeybind8(oldchakra.returnKeybind8());
            chakra.setKeybind9(oldchakra.returnKeybind9());

            chakra.setChakraBoolean(oldchakra.hasChakraBoolean());
            chakra.setFireNature(oldchakra.hasFireNature());
            chakra.setWaterNature(oldchakra.hasWaterNature());
            chakra.setWindNature(oldchakra.hasWindNature());
            chakra.setEarthNature(oldchakra.hasEarthNature());
            chakra.setLightningNature(oldchakra.hasLightningNature());
            chakra.setMagnetNature(oldchakra.hasMagnetNature());

            chakra.setCloneJutsuBoolean(oldchakra.hasCloneJutsuBoolean());
            chakra.setSummoningBoolean(oldchakra.hasSummoningBoolean());
            chakra.setBodyReplacementBoolean(oldchakra.hasBodyReplacementBoolean());
            chakra.setInvisibilityBoolean(oldchakra.hasInvisibilityBoolean());
            chakra.setTransformationBoolean(oldchakra.hasTransformationBoolean());

            chakra.setFireballJutsuBoolean(oldchakra.hasFireballJutsuBoolean());
            chakra.setPhoenixFlowerJutsuBoolean(oldchakra.hasPhoenixFlowerJutsuBoolean());
            chakra.setMoltenFistJutsuBoolean(oldchakra.hasMoltenFistJutsuBoolean());

            chakra.setLightningBallJutsuBoolean(oldchakra.hasLightningBallJutsuBoolean());
            chakra.setStunGunJutsuBoolean(oldchakra.hasStunGunJutsuBoolean());
            chakra.setLightningArrowJutsuBoolean(oldchakra.hasLightningArrowJutsuBoolean());

            chakra.setGalePalmJutsuBoolean(oldchakra.hasGalePalmJutsuBoolean());
            chakra.setWindExplosionJutsuBoolean(oldchakra.hasWindExplosionJutsuBoolean());
            chakra.setWindArrowJutsuBoolean(oldchakra.hasWindArrowJutsuBoolean());

            chakra.setWaterShurikenJutsuBoolean(oldchakra.hasWaterShurikenJutsuBoolean());
            chakra.setRagingWavesJutsuBoolean(oldchakra.hasRagingWavesJutsuBoolean());
            chakra.setWaterSharkBulletJutsuBoolean(oldchakra.hasWaterSharkBulletJutsuBoolean());

            chakra.setFlyingStonesJutsuBoolean(oldchakra.hasFlyingStonesJutsuBoolean());
            chakra.setMudMoatJutsuBoolean(oldchakra.hasMudMoatJutsuBoolean());
            chakra.setFistRockJutsuBoolean(oldchakra.hasFistRockJutsuBoolean());

            chakra.setMatterRepulsionJutsuBoolean(oldchakra.hasMatterRepulsionJutsuBoolean());
            chakra.setSelfLevitationJutsuBoolean(oldchakra.hasSelfLevitationJutsuBoolean());

            chakra.setAmaterasuJutsuBoolean(oldchakra.hasAmaterasuJutsuBoolean());
            chakra.setTsukuyomiJutsuBoolean(oldchakra.hasTsukuyomiJutsuBoolean());

            chakra.setPlayerToadSageMode(oldchakra.returnPlayerToadSageMode());
            chakra.setPlayerCurseMark(oldchakra.returnPlayerCurseMark());
        }
}