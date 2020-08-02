package com.benarutomod.tbroski.client.renderer.layers.models.bodymode;

import net.minecraft.entity.LivingEntity;

public interface IFaceBodyMode {

    default float rotLerpRad(float angleIn, float maxAngleIn, float mulIn) {
        float f = (maxAngleIn - angleIn) % ((float) Math.PI * 2F);
        if (f < -(float) Math.PI) {
            f += ((float) Math.PI * 2F);
        }

        if (f >= (float) Math.PI) {
            f -= ((float) Math.PI * 2F);
        }

        return angleIn + mulIn * f;
    }

    void setPartialTick(LivingEntity entityIn, float partialTick) ;
}
