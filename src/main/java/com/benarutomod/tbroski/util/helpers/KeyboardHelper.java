package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.gui.player.ShinobiStats;
import com.benarutomod.tbroski.api.enums.Nature;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.chakrastyles.*;
import com.benarutomod.tbroski.common.jutsu.JutsuCaller;
import com.benarutomod.tbroski.init.KeybindInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.*;
import com.benarutomod.tbroski.networking.packets.chakra.PacketChakraAddition;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsuCaller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.settings.KeyBinding;
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
        if (player != null) {
            LazyOptional<IPlayerHandler> playerCapability = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerCapability.orElse(new PlayerCapability());
            if (KeybindInit.HAND_INFUSION.isPressed()) {
                if (player.getHeldItem(Hand.MAIN_HAND).getItem() == Items.PAPER && !player_cap.hasChakraBoolean()) {
                    NetworkLoader.INSTANCE.sendToServer(new PacketAdvancement(Main.MODID + ":chakra/chakra"));
                    player_cap.setChakraBoolean(true);
                    NetworkLoader.INSTANCE.sendToServer(new PacketChakraAddition());
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

/*            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND1.getKeyBinding().getKey().getKeyCode()))
                System.out.println("1");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND2.getKeyBinding().getKey().getKeyCode()))
                System.out.println("2");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND3.getKeyBinding().getKey().getKeyCode()))
                System.out.println("3");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND4.getKeyBinding().getKey().getKeyCode()))
                System.out.println("4");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND5.getKeyBinding().getKey().getKeyCode()))
                System.out.println("5");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND6.getKeyBinding().getKey().getKeyCode()))
                System.out.println("6");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND7.getKeyBinding().getKey().getKeyCode()))
                System.out.println("7");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND8.getKeyBinding().getKey().getKeyCode()))
                System.out.println("8");
            if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND9.getKeyBinding().getKey().getKeyCode()))
                System.out.println("9");*/

            if (isTabDown()) {
                if (playerc.returnHandInfusionToggled()) {
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND1.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind1());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 1));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND2.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind2());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 2));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND3.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind3());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 3));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND4.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind4());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 4));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND5.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind5());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 5));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND6.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind6());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 6));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND7.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind7());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 7));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND8.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind8());
                        NetworkLoader.INSTANCE.sendToServer(new PacketJutsuCaller("", 8));
                    }
                    if (InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), KeybindInit.KEYBIND9.getKeyBinding().getKey().getKeyCode())) {
                        JutsuCaller.JutsuCaller(player, playerc.returnKeybind9());
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
