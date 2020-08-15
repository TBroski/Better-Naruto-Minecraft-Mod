package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.Main;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import java.util.Iterator;

public class AdvancementHelper {

    public static boolean grantAdvancement(ServerPlayerEntity player, String advancement) {
        Advancement adv = player.server.getAdvancementManager().getAdvancement(new ResourceLocation(Main.MODID + ":" + advancement));
        AdvancementProgress ap = player.getAdvancements().getProgress(adv);
        if (!ap.isDone()) {
            Iterator iterator = ap.getRemaningCriteria().iterator();
            while (iterator.hasNext()) {
                String criterion = (String) iterator.next();
                player.getAdvancements().grantCriterion(adv, criterion);
            }
            return true;
        }
        return false;
    }
}
