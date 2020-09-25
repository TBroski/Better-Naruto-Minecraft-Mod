package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import static org.lwjgl.glfw.GLFW.*;

public class KeybindInit {
    private static final String CATEGORY = "key.categories." + Main.MODID;
    public static KeyBinding HAND_INFUSION;
    public static KeyBinding BODY_INFUSION;
    public static KeyBinding LEG_INFUSION;
    public static KeyBinding SHINOBI_STATS;
    public static KeyBinding KEYBIND1;
    public static KeyBinding KEYBIND2;
    public static KeyBinding KEYBIND3;
    public static KeyBinding KEYBIND4;
    public static KeyBinding KEYBIND5;
    public static KeyBinding KEYBIND6;
    public static KeyBinding KEYBIND7;
    public static KeyBinding KEYBIND8;
    public static KeyBinding KEYBIND9;


    public static void register()
    {
        HAND_INFUSION = new KeyBinding("key." + Main.MODID + ".handinfusion", GLFW_KEY_R, CATEGORY);
        BODY_INFUSION = new KeyBinding("key." + Main.MODID + ".bodyinfusion", GLFW_KEY_F, CATEGORY);
        LEG_INFUSION = new KeyBinding("key." + Main.MODID + ".leginfusion", GLFW_KEY_C, CATEGORY);
        SHINOBI_STATS = new KeyBinding("key." + Main.MODID + ".shinobistats", GLFW_KEY_P, CATEGORY);

        KEYBIND1 = new KeyBinding("key." + Main.MODID + ".keybind1", GLFW_KEY_1, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND1);
        KEYBIND2 = new KeyBinding("key." + Main.MODID + ".keybind2", GLFW_KEY_2, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND2);
        KEYBIND3 = new KeyBinding("key." + Main.MODID + ".keybind3", GLFW_KEY_3, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND3);
        KEYBIND4 = new KeyBinding("key." + Main.MODID + ".keybind4", GLFW_KEY_4, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND4);
        KEYBIND5 = new KeyBinding("key." + Main.MODID + ".keybind5", GLFW_KEY_5, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND5);
        KEYBIND6 = new KeyBinding("key." + Main.MODID + ".keybind6", GLFW_KEY_6, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND6);
        KEYBIND7 = new KeyBinding("key." + Main.MODID + ".keybind7", GLFW_KEY_7, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND7);
        KEYBIND8 = new KeyBinding("key." + Main.MODID + ".keybind8", GLFW_KEY_8, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND8);
        KEYBIND9 = new KeyBinding("key." + Main.MODID + ".keybind9", GLFW_KEY_9, CATEGORY);
        ClientRegistry.registerKeyBinding(KEYBIND9);

        ClientRegistry.registerKeyBinding(HAND_INFUSION);
        ClientRegistry.registerKeyBinding(BODY_INFUSION);
        ClientRegistry.registerKeyBinding(LEG_INFUSION);
        ClientRegistry.registerKeyBinding(SHINOBI_STATS);
    }
}
