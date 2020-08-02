package com.benarutomod.tbroski.common;

import java.util.ArrayList;

public abstract class BeNMRegistry<T> {

    public static BeNMRegistry.JutsuRegistry JUTSUS = new BeNMRegistry.JutsuRegistry();
    public static BeNMRegistry.DojutsuRegistry DOJUTSUS = new BeNMRegistry.DojutsuRegistry();
    public static BeNMRegistry.ClanRegistry CLANS = new BeNMRegistry.ClanRegistry();

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

    public static class DojutsuRegistry extends BeNMRegistry<BeNMDojutsu> {

        private ArrayList<BeNMDojutsu> DOJUTSUS = new ArrayList<>();

        @Override
        public ArrayList<BeNMDojutsu> getValues() {
            return DOJUTSUS;
        }

        @Override
        public void register(BeNMDojutsu registryObject) {
            DOJUTSUS.add(registryObject);
        }
    }

    public static class ClanRegistry extends BeNMRegistry<BeNMClan> {

        private ArrayList<BeNMClan> CLANS = new ArrayList<>();

        @Override
        public ArrayList<BeNMClan> getValues() {
            return CLANS;
        }

        @Override
        public void register(BeNMClan registryObject) {
            CLANS.add(registryObject);
        }
    }
}
