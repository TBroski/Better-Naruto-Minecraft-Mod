package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.common.BeNMClan;
import com.benarutomod.tbroski.common.BeNMJutsu;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.common.jutsu.*;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.chakrastyles.*;
import com.benarutomod.tbroski.init.KeybindInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.*;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraAddition;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class KeyboardHelper {

    @OnlyIn(Dist.CLIENT)
    public static boolean isShiftDown() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)
                || InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isControlDown() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_CONTROL)
                || InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_CONTROL);
    }
    @OnlyIn(Dist.CLIENT)
    public static boolean isTabDown() {
        return InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_TAB);
    }

    @SubscribeEvent
    public void onClientTickEvent(final TickEvent.ClientTickEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        Minecraft mcinstance = Minecraft.getInstance();
        if (player != null) {
            this.checkInfusion();
            LazyOptional<IPlayerHandler> playerCapability = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerCapability.orElse(new PlayerCapability());
            if (KeybindInit.HAND_INFUSION.isPressed()) {
                int randChakraNum = new Random().nextInt(6);
                if (player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.PAPER && !player_cap.hasChakraBoolean()) { //!player_cap.hasChakraBoolean()
                    NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("chakra"));
                    player_cap.setChakraBoolean(true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketChakraAddition());
                    if (player_cap.returnPlayerClan().getClanNature() == BeNMClan.Nature.NULL) {
                        if (randChakraNum > 4) {
                            mcinstance.displayGuiScreen(new FirePaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(1, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(1, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("firenature"));
                        } else if (randChakraNum > 3) {
                            mcinstance.displayGuiScreen(new LightningPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(5, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(5, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("lightningnature"));
                        } else if (randChakraNum > 2) {
                            mcinstance.displayGuiScreen(new WindPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(4, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(4, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("windnature"));
                        } else if (randChakraNum > 1) {
                            mcinstance.displayGuiScreen(new EarthPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(3, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(3, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("earthnature"));
                        } else {
                            mcinstance.displayGuiScreen(new WaterPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(2, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(2, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("waternature"));
                        }
                    }
                    else {
                        if (player_cap.returnPlayerClan().getClanNature() == BeNMClan.Nature.FIRE) {
                            mcinstance.displayGuiScreen(new FirePaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(1, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(1, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("firenature"));
                        } else if (player_cap.returnPlayerClan().getClanNature() == BeNMClan.Nature.LIGHTNING) {
                            mcinstance.displayGuiScreen(new LightningPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(5, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(5, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("lightningnature"));
                        } else if (player_cap.returnPlayerClan().getClanNature() == BeNMClan.Nature.WIND) {
                            mcinstance.displayGuiScreen(new WindPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(4, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(4, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("windnature"));
                        } else if (player_cap.returnPlayerClan().getClanNature() == BeNMClan.Nature.EARTH) {
                            mcinstance.displayGuiScreen(new EarthPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(3, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(3, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("earthnature"));
                        } else {
                            mcinstance.displayGuiScreen(new WaterPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(2, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(2, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("waternature"));
                        }
                    }
                }
                else if (player.getPersistentData().getBoolean("handinfusion") == false) {
                    player.getPersistentData().putBoolean("handinfusion", true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketBackItem());
                } else {
                    player.getPersistentData().putBoolean("handinfusion", false);
                }
            }
            if (KeybindInit.BODY_INFUSION.isPressed())
            {
                if (player.getPersistentData().getBoolean("bodyinfusion") == false)
                {
                    player.getPersistentData().putBoolean("bodyinfusion", true);
                } else {
                    player.getPersistentData().putBoolean("bodyinfusion", false);
                }
            }
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (jutsu.isToggle()) {
                    String nbtName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
                    if (player.getPersistentData().getBoolean(nbtName)) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(jutsu.getName()));
                    }
                }
            }
/*            if (player.getPersistentData().getBoolean("invisibilitytechnigue") == true)
            {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(EffectsJutsu.InvisibilityTechniqueID, 1, 1));
            }
            if (player.getPersistentData().getBoolean("moltenfisttechnigue") == true)
            {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(EffectsJutsu.MoltenFistJutsuID, 1, 1));
            }
            if (player.getPersistentData().getBoolean("fistrocktechnigue") == true)
            {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu(EffectsJutsu.FistRockJutsuID, 1, 1));
            }*/
            if (player.getPersistentData().getBoolean("handinfusion") == true)
            {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu("handinfusion"));
            }
            if (player.getPersistentData().getBoolean("leginfusion") == true)
            {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu("leginfusion"));
            }
            if (player.getPersistentData().getBoolean("bodyinfusion") == true)
            {
                NetworkLoader.INSTANCE.sendToServer(new PacketJutsu("bodyinfusion"));
            }
            if (KeybindInit.SHINOBI_STATS.isPressed()) {
                NetworkLoader.INSTANCE.sendToServer(new PacketGUIOpen());
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

            if (isTabDown()) {
                if (player.getPersistentData().getBoolean("handinfusion") == true) {
                    if (KeybindInit.KEYBIND1.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind1());
                    }
                    if (KeybindInit.KEYBIND2.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind2());
                    }
                    if (KeybindInit.KEYBIND3.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind3());
                    }
                    if (KeybindInit.KEYBIND4.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind4());
                    }
                    if (KeybindInit.KEYBIND5.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind5());
                    }
                    if (KeybindInit.KEYBIND6.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind6());
                    }
                    if (KeybindInit.KEYBIND7.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind7());
                    }
                    if (KeybindInit.KEYBIND8.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind8());
                    }
                    if (KeybindInit.KEYBIND9.isKeyDown()) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind9());
                    }
                }
            }
            if (KeybindInit.LEG_INFUSION.isKeyDown()) {
                if (player.getPersistentData().getBoolean("leginfusion") == false) {
                    player.getPersistentData().putBoolean("leginfusion", true);
                    //player.sendMessage(new StringTextComponent("Leg Infused"));
                } else {
                    player.getPersistentData().putBoolean("leginfusion", false);
                }
            }
        }
    }

    private void checkInfusion()
    {
        ClientPlayerEntity player = Minecraft.getInstance().player;
        if (player != null) {
            LazyOptional<IPlayerHandler> playerCapability = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerCapability.orElse(new PlayerCapability());
            if (player.getPersistentData().getBoolean("handinfusion") == true) {
                player_cap.setHandInfusionToggled(true);
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(1, false, true, player.getEntityId()));
            } else {
                player_cap.setHandInfusionToggled(false);
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(1, false, false, player.getEntityId()));
            }
            if (player.getPersistentData().getBoolean("bodyinfusion") == true) {
                player_cap.setBodyInfusionToggled(true);
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(2, false, true, player.getEntityId()));
            } else {
                player_cap.setBodyInfusionToggled(false);
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(2, false, false, player.getEntityId()));
            }
            if (player.getPersistentData().getBoolean("leginfusion") == true) {
                player_cap.setLegInfusionToggled(true);
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(3, false, true, player.getEntityId()));
            } else {
                player_cap.setLegInfusionToggled(false);
                NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(3, false, false, player.getEntityId()));
            }
        }
    }
}
