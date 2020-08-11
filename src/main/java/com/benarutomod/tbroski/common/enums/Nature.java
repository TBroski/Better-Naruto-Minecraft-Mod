package com.benarutomod.tbroski.common.enums;

public enum Nature {
    NULL("null"), FIRE("fire"), WATER("water"), EARTH("earth"), WIND("wind"), LIGHTNING("lightning"),
    MAGNET("magnet", WIND, EARTH);

    private final String name;
    private final Nature[] requirements;

    Nature(String name, Nature... requirements) {
        this.name = name;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public Nature[] getRequirements() {
        return requirements;
    }
}
