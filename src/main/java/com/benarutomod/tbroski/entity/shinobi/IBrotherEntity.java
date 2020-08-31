package com.benarutomod.tbroski.entity.shinobi;

import java.util.Random;

public interface IBrotherEntity {

    default String calculateBrotherName(String playerName, Random random) {
        String[] surNames = {"ruto", "kami", "suke"};
        if (playerName == null)
            return null;
        String output = "";
        for (int i = playerName.length() - 1; i >= 0; i--) {
            if (random.nextInt(5) > 0) {
                output = output + playerName.charAt(i);
            }
        }
        if (random.nextInt( 4) == 0) {
            output = output + surNames[random.nextInt(surNames.length)];
        }
        output = output.toUpperCase().substring(0, 1) + output.toLowerCase().substring(1);
        return output;
    }
}
