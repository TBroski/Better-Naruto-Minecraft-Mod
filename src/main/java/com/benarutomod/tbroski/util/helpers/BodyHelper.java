package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.common.BeNMBody;
import com.benarutomod.tbroski.init.BodyInit;

public class BodyHelper {

    public static BeNMBody getBodyFromString(String clan) {
        for (BeNMBody benmBody : BodyInit.BODIES) {
            if (benmBody.getString().equalsIgnoreCase(clan)) {
                return benmBody;
            }
        }
        return null;
    }
}
