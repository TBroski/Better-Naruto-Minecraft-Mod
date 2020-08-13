package com.benarutomod.tbroski.api;

import com.benarutomod.tbroski.api.internal.BeNMBody;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.internal.BeNMDojutsu;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;

import java.util.ArrayList;

public abstract class BeNMRegistry<T> {

    public static BeNMRegistry.JutsuRegistry JUTSUS = new BeNMRegistry.JutsuRegistry();
    public static BeNMRegistry.DojutsuRegistry DOJUTSUS = new BeNMRegistry.DojutsuRegistry();
    public static BeNMRegistry.ClanRegistry CLANS = new BeNMRegistry.ClanRegistry();
    public static BeNMRegistry.BodyModeRegistry BODY_MODES = new BeNMRegistry.BodyModeRegistry();

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

    public static class BodyModeRegistry extends BeNMRegistry<BeNMBody> {

        private ArrayList<BeNMBody> BODY_MODES = new ArrayList<>();

        @Override
        public ArrayList<BeNMBody> getValues() {
            return BODY_MODES;
        }

        @Override
        public void register(BeNMBody registryObject) {
            BODY_MODES.add(registryObject);
        }
    }
}
