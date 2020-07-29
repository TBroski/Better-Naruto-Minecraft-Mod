package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, Main.MODID);
    //Sound Lazy
    public static final Lazy<SoundEvent> ITACHI_DISC_LAZY = Lazy.of(() -> new SoundEvent(new ResourceLocation(Main.MODID, "item.naruto_itachi_disc")));
    public static final Lazy<SoundEvent> PAIN_DISC_LAZY = Lazy.of(() -> new SoundEvent(new ResourceLocation(Main.MODID, "item.naruto_pain_disc")));



    //Sounds
    public static final RegistryObject<SoundEvent> NARUTO_ITACHI_DISC = SOUNDS.register("item.naruto_itachi_theme.disc", ITACHI_DISC_LAZY);
    public static final RegistryObject<SoundEvent> NARUTO_PAIN_DISC = SOUNDS.register("item.naruto_pain_theme.disc", PAIN_DISC_LAZY);
}
