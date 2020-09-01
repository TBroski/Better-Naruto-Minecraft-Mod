package com.benarutomod.tbroski.util.helpers;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

import java.util.Iterator;

public class AdvancementHelper {

    public static boolean grantAdvancement(ServerPlayerEntity player, String advancement) {
        boolean flag = false;
        for (Advancement advance : player.server.getAdvancementManager().getAllAdvancements()) {
            if(advance.getId().toString().equalsIgnoreCase(advancement)) {
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("Advancement: " + advancement + ". Has not been found.");
            return false;
        }

        Advancement adv = player.server.getAdvancementManager().getAdvancement(new ResourceLocation(advancement));
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
