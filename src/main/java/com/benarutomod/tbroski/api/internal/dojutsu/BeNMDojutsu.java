package com.benarutomod.tbroski.api.internal.dojutsu;

import com.benarutomod.tbroski.Main;
import net.minecraft.util.ResourceLocation;

public class BeNMDojutsu {

    private final String dojutsu;
    private final Type type;
    private final int size;
    private ResourceLocation resourceLocation;

    private boolean seeChakra;
    private boolean damageDodgable;
    private boolean restrictsChakra;
    private boolean eightPaths;

    public BeNMDojutsu(String dojutsu, Type type, int size) {
        this.dojutsu = dojutsu;
        this.type = type;
        this.size = size;
        this.resourceLocation = new ResourceLocation(Main.MODID, "textures/entity/layer/dojutsu/" + dojutsu + ".png");
    }

    public String getString() {
        return this.dojutsu;
    }

    public ResourceLocation getResourceLocation() {
        return this.resourceLocation;
    }

    public Type getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public BeNMDojutsu setResourceLocation(ResourceLocation resourceLocation) {
        this.resourceLocation = resourceLocation;
        return this;
    }

    public BeNMDojutsu setCanSeeChakra() {
        this.seeChakra = true;
        return this;
    }

    public BeNMDojutsu setRestrictsChakra() {
        this.restrictsChakra = true;
        return this;
    }

    public BeNMDojutsu setCanDodgeDamage() {
        this.damageDodgable = true;
        return this;
    }

    public BeNMDojutsu setCanUseSixPathsTechnique() {
        this.eightPaths = true;
        return this;
    }


    public boolean canSeeChakra() {
        return this.seeChakra;
    }

    public boolean doesRestrictChakra() {
        return this.restrictsChakra;
    }

    public boolean canDodgeDamage() {
        return this.damageDodgable;
    }

    public boolean canUseEightPaths() {
        return this.eightPaths;
    }


    public enum Type {
        MISC, SHARINGAN, BYAKUGAN,
    }
}
