package com.benarutomod.tbroski.capabilities.player;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerProvider implements ICapabilitySerializable<INBT> {
    @CapabilityInject(IPlayerHandler.class)
    public static final Capability<IPlayerHandler> CAPABILITY_PLAYER = null;

    private LazyOptional<IPlayerHandler> instance = LazyOptional.of(CAPABILITY_PLAYER::getDefaultInstance); //getDefaultInstance

    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == CAPABILITY_PLAYER ? instance.cast() : LazyOptional.empty();
    }


    @Override
    public void deserializeNBT(INBT nbt) {
        CAPABILITY_PLAYER.getStorage().readNBT(CAPABILITY_PLAYER, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty")),null, nbt);

    }

    @Override
    public INBT serializeNBT() {
        return CAPABILITY_PLAYER.getStorage().writeNBT(CAPABILITY_PLAYER, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty")),null);
    }
}