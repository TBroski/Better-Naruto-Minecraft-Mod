package com.benarutomod.tbroski.common.jutsu;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class JutsuCaller {

    public static void JutsuCaller(ServerPlayerEntity playerIn, String jutsuName)
    {
        if (jutsuName != null) {
            IPlayerHandler playercap = playerIn.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (("jutsu." + jutsu.getCorrelatedPlugin().getPluginId() + "." + jutsu.getName()).equalsIgnoreCase(jutsuName)) {
                    if (jutsu.isToggle()) {
                        String nbtName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
                        if (!playerIn.getPersistentData().getBoolean(nbtName)) {
                            playerIn.getPersistentData().putBoolean(nbtName, true);
                            if (!playercap.returnToggleJutsuMessage()) playerIn.sendMessage(new StringTextComponent(new TranslationTextComponent("jutsu." + jutsu.getCorrelatedPlugin().getPluginId() + "." + jutsu.getName()).getString() + "!"));
                        } else {
                            jutsu.throwCancelEvent(playerIn);
                            playerIn.getPersistentData().putBoolean(nbtName, false);
                        }
                    }
                    else {
                        int taijutsuModifier0 = 0;
                        int taijutsuModifier1 = 0;
                        if (playercap.returnTaijutsu() >= 15) {
                            taijutsuModifier0 = 3; taijutsuModifier1 = 2;
                        }
                        else if (playercap.returnTaijutsu() >= 10) {
                            taijutsuModifier0 = 2; taijutsuModifier1 = 1;
                        }
                        else if (playercap.returnTaijutsu() >= 5) {
                            taijutsuModifier0 = 1; taijutsuModifier1 = 0;
                        }
                        jutsu.act(playerIn, taijutsuModifier0, taijutsuModifier1);                    }
                }
            }
        }
    }
}
