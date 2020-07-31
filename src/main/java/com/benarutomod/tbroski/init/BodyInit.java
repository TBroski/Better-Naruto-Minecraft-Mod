package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.common.BeNMBody;
import net.minecraft.potion.Effects;

import java.util.ArrayList;

public class BodyInit {

    public static final ArrayList<BeNMBody> BODIES = new ArrayList<>();

    public static final BeNMBody NULL = new BeNMBody("null");

    public static final BeNMBody TOAD_SAGE_MODE = new BeNMBody("toad_sage").setAttackingEffect(Effects.WITHER);
    public static final BeNMBody CURSE_MARK_MODE = new BeNMBody("curse_mark").setPlayerEffect(EffectInit.CHAKRA_REG.get());

    public static void register() {
        BODIES.add(NULL);
        BODIES.add(TOAD_SAGE_MODE);
        BODIES.add(CURSE_MARK_MODE);
    }
}
