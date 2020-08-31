package com.benarutomod.tbroski.api.enums;

public enum Nature {
    NULL("null", true), FIRE("fire", true), WATER("water", true), EARTH("earth", true), WIND("wind", true), LIGHTNING("lightning", true),
    MAGNET("magnet", false), WOOD("wood", false), LAVA("lava", false), ICE("ice", false), SCORCH("scorch", false), STORM("storm", false), BOIL("boil", false), EXPLOSION("explosion", false),;

    private final String name;
    private final boolean basic;

    Nature(String name, boolean basic) {
        this.name = name;
        this.basic = basic;
    }

    public String getName() {
        return name;
    }

    public boolean isBasic() {
        return basic;
    }
}
