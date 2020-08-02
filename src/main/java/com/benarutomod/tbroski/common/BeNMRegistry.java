package com.benarutomod.tbroski.common;

import java.util.ArrayList;

public abstract class BeNMRegistry<T> {

    public static BeNMRegistry.JutsuRegistry JUTSUS = new BeNMRegistry.JutsuRegistry();

    public abstract ArrayList<T> getValues();
    public abstract void register(T registryObject);

    public static class JutsuRegistry extends BeNMRegistry<BeNMJutsu> {

        private ArrayList<BeNMJutsu> JUTSUS = new ArrayList<>();

        @Override
        public ArrayList<BeNMJutsu> getValues() {
            return JUTSUS;
        }

        @Override
        public void register(BeNMJutsu registryObject) {
            JUTSUS.add(registryObject);
        }
    }
}
