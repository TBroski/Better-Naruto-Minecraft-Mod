package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.client.gui.player.ShinobiStats;
import com.benarutomod.tbroski.api.enums.Nature;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.chakrastyles.*;
import com.benarutomod.tbroski.init.KeybindInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.*;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraAddition;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsuCaller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

import java.util.Random;

public class KeyboardHelper {

    private Random rand = new Random();

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
            LazyOptional<IPlayerHandler> playerCapability = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerCapability.orElse(new PlayerCapability());
            if (KeybindInit.HAND_INFUSION.isPressed()) {
                if (player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.PAPER && !player_cap.hasChakraBoolean()) {
                    int randChakraNum = rand.nextInt(6);
                    NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("chakra"));
                    player_cap.setChakraBoolean(true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketChakraAddition());
                    if (player_cap.returnPlayerClan().getClanNature() == Nature.NULL) {
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
                        if (player_cap.returnPlayerClan().getClanNature() == Nature.FIRE) {
                            mcinstance.displayGuiScreen(new FirePaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(1, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(1, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("firenature"));
                        } else if (player_cap.returnPlayerClan().getClanNature() == Nature.LIGHTNING) {
                            mcinstance.displayGuiScreen(new LightningPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(5, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(5, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("lightningnature"));
                        } else if (player_cap.returnPlayerClan().getClanNature() == Nature.WIND) {
                            mcinstance.displayGuiScreen(new WindPaperGui());
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(4, true, false));
                            NetworkLoader.INSTANCE.sendToServer(new PacketNature(4, true, true));
                            NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement("windnature"));
                        } else if (player_cap.returnPlayerClan().getClanNature() == Nature.EARTH) {
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
                else if (!player_cap.returnHandInfusionToggled()) {
                    player_cap.setHandInfusionToggled(true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketBackItem());
                    NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(1, false, true, player.getEntityId()));
                } else {
                    player_cap.setHandInfusionToggled(false);
                    NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(1, false, false, player.getEntityId()));
                }
            }
            if (KeybindInit.SHINOBI_STATS.isPressed()) {
                Minecraft.getInstance().displayGuiScreen(new ShinobiStats());
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
                if (playerc.returnHandInfusionToggled()) {
                    if (KeybindInit.KEYBIND1.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 1));
                    }
                    if (KeybindInit.KEYBIND2.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 2));
                    }
                    if (KeybindInit.KEYBIND3.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 3));
                    }
                    if (KeybindInit.KEYBIND4.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 4));
                    }
                    if (KeybindInit.KEYBIND5.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 5));
                    }
                    if (KeybindInit.KEYBIND6.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 6));
                    }
                    if (KeybindInit.KEYBIND7.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 7));
                    }
                    if (KeybindInit.KEYBIND8.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 8));
                    }
                    if (KeybindInit.KEYBIND9.isKeyDown()) {
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 9));
                    }
                }
            }
            if (KeybindInit.LEG_INFUSION.isKeyDown()) {
                if (!playerc.returnLegInfusionToggled()) {
                    playerc.setLegInfusionToggled(true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(3, false, true, player.getEntityId()));
                } else {
                    playerc.setLegInfusionToggled(false);
                    NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(3, false, false, player.getEntityId()));
                }
            }
            if (KeybindInit.BODY_INFUSION.isKeyDown())
            {
                if (!playerc.returnBodyInfusionToggled()) {
                    playerc.setBodyInfusionToggled(true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(2, false, true, player.getEntityId()));
                } else {
                    playerc.setBodyInfusionToggled(false);
                    NetworkLoader.INSTANCE.sendToServer(new PacketToggleInfusionBoolean(2, false, false, player.getEntityId()));
                }
            }
        }
    }
}
