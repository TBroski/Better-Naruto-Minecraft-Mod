package com.benarutomod.tbroski.util.helpers;

import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.BeNMRegistry;

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
