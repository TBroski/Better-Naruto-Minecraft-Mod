package com.benarutomod.tbroski.networking;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.networking.packets.*;
import com.benarutomod.tbroski.networking.packets.chakra.*;
import com.benarutomod.tbroski.networking.packets.jutsu.*;
import com.benarutomod.tbroski.networking.packets.settings.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkLoader {

    public static SimpleChannel INSTANCE;
    private static int id = 1;

    public static int nextID() {
        return id++;
    }

    public static void registerMessages() {
        INSTANCE = NetworkRegistry.newSimpleChannel(new ResourceLocation(Main.MODID, "benarutomod"), () -> "1.0", s -> true, s -> true);

        INSTANCE.registerMessage(nextID(), PacketChakraSync.class, PacketChakraSync::encode, PacketChakraSync::decode, PacketChakraSync::handle);
        INSTANCE.registerMessage(nextID(), PacketRegenChakraSync.class, PacketRegenChakraSync::encode, PacketRegenChakraSync::decode, PacketRegenChakraSync::handle);
        INSTANCE.registerMessage(nextID(), PacketChakraAddition.class, PacketChakraAddition::encode, PacketChakraAddition::decode, PacketChakraAddition::handle);
        INSTANCE.registerMessage(nextID(), PacketChakraControlSync.class, PacketChakraControlSync::encode, PacketChakraControlSync::decode, PacketChakraControlSync::handle);
        INSTANCE.registerMessage(nextID(), PacketMaxChakraSync.class, PacketMaxChakraSync::encode, PacketMaxChakraSync::decode, PacketMaxChakraSync::handle);
        INSTANCE.registerMessage(nextID(), PacketColorChakraSync.class, PacketColorChakraSync::encode, PacketColorChakraSync::decode, PacketColorChakraSync::handle);
        INSTANCE.registerMessage(nextID(), PacketEyeSlotSync.class, PacketEyeSlotSync::encode, PacketEyeSlotSync::decode, PacketEyeSlotSync::handle);
        INSTANCE.registerMessage(nextID(), PacketPlayerEntityAffiliationSync.class, PacketPlayerEntityAffiliationSync::encode, PacketPlayerEntityAffiliationSync::decode, PacketPlayerEntityAffiliationSync::handle);
        INSTANCE.registerMessage(nextID(), PacketBeNMPointsSync.class, PacketBeNMPointsSync::encode, PacketBeNMPointsSync::decode, PacketBeNMPointsSync::handle);
        INSTANCE.registerMessage(nextID(), PacketJutsu.class, PacketJutsu::encode, PacketJutsu::decode, PacketJutsu::handle);
        INSTANCE.registerMessage(nextID(), PacketAdvancement.class, PacketAdvancement::encode, PacketAdvancement::decode, PacketAdvancement::handle);
        INSTANCE.registerMessage(nextID(), PacketSetJutsuBoolean.class, PacketSetJutsuBoolean::encode, PacketSetJutsuBoolean::decode, PacketSetJutsuBoolean::handle);
        INSTANCE.registerMessage(nextID(), PacketKeybindSet.class, PacketKeybindSet::encode, PacketKeybindSet::decode, PacketKeybindSet::handle);
        INSTANCE.registerMessage(nextID(), PacketShinobiLevel.class, PacketShinobiLevel::encode, PacketShinobiLevel::decode, PacketShinobiLevel::handle);
        INSTANCE.registerMessage(nextID(), PacketNature.class, PacketNature::encode, PacketNature::decode, PacketNature::handle);
        INSTANCE.registerMessage(nextID(), PacketGUIOpen.class, PacketGUIOpen::encode, PacketGUIOpen::decode, PacketGUIOpen::handle);
        INSTANCE.registerMessage(nextID(), PacketToggleMessageBoolean.class, PacketToggleMessageBoolean::encode, PacketToggleMessageBoolean::decode, PacketToggleMessageBoolean::handle);
        INSTANCE.registerMessage(nextID(), PacketTaijutsu.class, PacketTaijutsu::encode, PacketTaijutsu::decode, PacketTaijutsu::handle);
        INSTANCE.registerMessage(nextID(), PacketToggleScrollBoolean.class, PacketToggleScrollBoolean::encode, PacketToggleScrollBoolean::decode, PacketToggleScrollBoolean::handle);
        INSTANCE.registerMessage(nextID(), PacketToggleInfusionBoolean.class, PacketToggleInfusionBoolean::encode, PacketToggleInfusionBoolean::decode, PacketToggleInfusionBoolean::handle);
        INSTANCE.registerMessage(nextID(), PacketPlayerDojutsuSync.class, PacketPlayerDojutsuSync::encode, PacketPlayerDojutsuSync::decode, PacketPlayerDojutsuSync::handle);
        INSTANCE.registerMessage(nextID(), PacketEntityClaySync.class, PacketEntityClaySync::encode, PacketEntityClaySync::decode, PacketEntityClaySync::handle);
        INSTANCE.registerMessage(nextID(), PacketAmaterasuNBTSync.class, PacketAmaterasuNBTSync::encode, PacketAmaterasuNBTSync::decode, PacketAmaterasuNBTSync::handle);
        INSTANCE.registerMessage(nextID(), PacketBackItem.class, PacketBackItem::encode, PacketBackItem::decode, PacketBackItem::handle);
        INSTANCE.registerMessage(nextID(), PacketBackSlotSync.class, PacketBackSlotSync::encode, PacketBackSlotSync::decode, PacketBackSlotSync::handle);
        INSTANCE.registerMessage(nextID(), PacketCurseMarkMeleeAttacked.class, PacketCurseMarkMeleeAttacked::encode, PacketCurseMarkMeleeAttacked::decode, PacketCurseMarkMeleeAttacked::handle);
        INSTANCE.registerMessage(nextID(), PacketPlayerBodyModeSync.class, PacketPlayerBodyModeSync::encode, PacketPlayerBodyModeSync::decode, PacketPlayerBodyModeSync::handle);
        INSTANCE.registerMessage(nextID(), PacketPlayerHasBodyModeSync.class, PacketPlayerHasBodyModeSync::encode, PacketPlayerHasBodyModeSync::decode, PacketPlayerHasBodyModeSync::handle);
        INSTANCE.registerMessage(nextID(), PacketJutsuCancelEvent.class, PacketJutsuCancelEvent::encode, PacketJutsuCancelEvent::decode, PacketJutsuCancelEvent::handle);
        INSTANCE.registerMessage(nextID(), PacketJutsuNBTSync.class, PacketJutsuNBTSync::encode, PacketJutsuNBTSync::decode, PacketJutsuNBTSync::handle);
        INSTANCE.registerMessage(nextID(), PacketJutsuCaller.class, PacketJutsuCaller::encode, PacketJutsuCaller::decode, PacketJutsuCaller::handle);
        INSTANCE.registerMessage(nextID(), PacketBijuuSync.class, PacketBijuuSync::encode, PacketBijuuSync::decode, PacketBijuuSync::handle);
    }
}
