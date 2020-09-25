package com.benarutomod.tbroski.capabilities;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;

import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.*;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsuNBTSync;
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
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(6, playercap.hasChakraBoolean(), false,true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(1, playercap.hasFireNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(2, playercap.hasWaterNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(3, playercap.hasEarthNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(4, playercap.hasWindNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(5, playercap.hasLightningNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(7, playercap.hasMagnetNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(8, playercap.hasWoodNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(9, playercap.hasLavaNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(10, playercap.hasIceNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(11, playercap.hasBoilNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(12, playercap.hasScorchNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(13, playercap.hasStormNature(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketNature(14, playercap.hasExplosionNature(), false, true));
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
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketSetJutsuBoolean(jutsu.getName(), jutsu.hasJutsu(jutsu, playercap), true));
                String nbtName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
                player.getPersistentData().putBoolean(nbtName, false);
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PacketJutsuNBTSync(player.getEntityId(), nbtName, false));
            }
            NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PacketToggleInfusionBoolean(1, true, playercap.returnHandInfusionToggled(), player.getEntityId()));
            NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PacketToggleInfusionBoolean(2, true, playercap.returnBodyInfusionToggled(), player.getEntityId()));
            NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PacketToggleInfusionBoolean(3, true, playercap.returnLegInfusionToggled(), player.getEntityId()));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBeNMPointsSync(playercap.returnBeNMPoints(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerEntityAffiliationSync(playercap.returnPlayerEntityAffiliation(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketEyeSlotSync(playercap.returnplayerEyeSlot(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketToggleMessageBoolean(playercap.returnToggleJutsuMessage(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketToggleScrollBoolean(playercap.returnToggleScrollRenderer(), true, player.getEntityId()));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketShinobiLevel(playercap.returnShinobiLevel(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketTaijutsu(playercap.returnTaijutsu(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerDojutsuSync(playercap.returnPlayerLeftDojutsu().getString(), true, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerDojutsuSync(playercap.returnPlayerRightDojutsu().getString(), false, true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerBodyModeSync(playercap.returnPlayerBodyMode().getName(), player.getEntityId(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerHasBodyModeSync(1, playercap.returnPlayerCurseMark(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketPlayerHasBodyModeSync(2,  playercap.returnPlayerToadSageMode(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketBijuuSync(player.getEntityId(), playercap.returnPlayerBijuu()));
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new PacketClanSync(playercap.returnPlayerClan().getString(), true));
            NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PacketSusanooItemsSync(player.getEntityId(), playercap.getSusanooMainHand(), playercap.getSusanooOffHand(), true)); //playercap.getSusanooMainHand(), playercap.getSusanooOffHand()
            NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new PacketTruthSeekingOrbsSync(player.getEntityId(), playercap.getTruthSeekingOrbAmount())); //playercap.getSusanooMainHand(), playercap.getSusanooOffHand()
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
        chakra.setPlayerBijuu(oldchakra.returnPlayerBijuu());
        chakra.setPlayerBijuuLevel(oldchakra.returnPlayerBijuuLevel());
        chakra.setPlayerLeftDojutsu(oldchakra.returnPlayerLeftDojutsu());
        chakra.setPlayerRightDojutsu(oldchakra.returnPlayerRightDojutsu());
        chakra.setShinobiLevel(oldchakra.returnShinobiLevel());
        chakra.setToggleJutsuMessage(oldchakra.returnToggleJutsuMessage());
        chakra.setToggleScrollRenderer(oldchakra.returnToggleScrollRenderer());
        chakra.setPlayerBodyMode(oldchakra.returnPlayerBodyMode());
        chakra.setPlayerClan(oldchakra.returnPlayerClan());
        chakra.setSusanooMainHand(oldchakra.getSusanooMainHand());
        chakra.setSusanooOffHand(oldchakra.getSusanooOffHand());

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
        chakra.setWoodNature(oldchakra.hasWoodNature());
        chakra.setLavaNature(oldchakra.hasLavaNature());
        chakra.setIceNature(oldchakra.hasIceNature());
        chakra.setBoilNature(oldchakra.hasBoilNature());
        chakra.setScorchNature(oldchakra.hasScorchNature());
        chakra.setStormNature(oldchakra.hasStormNature());
        chakra.setExplosionNature(oldchakra.hasExplosionNature());

        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            chakra.setJutsuBoolean(jutsu, oldchakra.hasJutsuBoolean(jutsu));
        }

        chakra.setPlayerToadSageMode(oldchakra.returnPlayerToadSageMode());
        chakra.setPlayerCurseMark(oldchakra.returnPlayerCurseMark());
    }
}