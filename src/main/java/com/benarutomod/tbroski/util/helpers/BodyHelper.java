package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.common.BeNMBody;
import com.benarutomod.tbroski.common.BeNMRegistry;
import com.benarutomod.tbroski.init.BodyInit;

public class BodyHelper {

    public static BeNMBody getBodyFromString(String clan) {
        for (BeNMBody benmBody : BeNMRegistry.BODY_MODES.getValues()) {
            if (benmBody.getName().equalsIgnoreCase(clan)) {
                return benmBody;
            }
        }
        return null;
    }
}
