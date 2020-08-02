package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.renderer.layers.models.bodymode.ModelCurseMarkWings;
import com.benarutomod.tbroski.common.BeNMBody;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class BodyInit {

    public static final ArrayList<BeNMBody> BODIES = new ArrayList<>();

    public static final BeNMBody NULL = new BeNMBody("null");

    public static final BeNMBody TOAD_SAGE_MODE = new BeNMBody("toad_sage").setPlayerEffect(EffectInit.SAGE_CHAKRA_REG.get()).setDojutsuModelOnRender(1, 1, new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/toadsageeyes.png"));
    public static final BeNMBody CURSE_MARK_MODE = new BeNMBody("curse_mark").setAttackingEffect(Effects.WITHER).setModelOnRender(new ModelCurseMarkWings(), new ResourceLocation(Main.MODID, "textures/entity/layer/bodymode/cursemarkwings.png"));

    public static void register() {
        BODIES.add(NULL);
        BODIES.add(TOAD_SAGE_MODE);
        BODIES.add(CURSE_MARK_MODE);
    }
}
