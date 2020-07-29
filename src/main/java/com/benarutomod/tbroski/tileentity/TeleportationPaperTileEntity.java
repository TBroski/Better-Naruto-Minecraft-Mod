package com.benarutomod.tbroski.tileentity;

import com.benarutomod.tbroski.init.TileEntityInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;
import java.util.Random;

public class TeleportationPaperTileEntity extends TileEntity implements ITickableTileEntity {
    Random random = new Random();
    int teletimer;
    boolean iftimer;
    @Nullable
    LivingEntity placer;

    public TeleportationPaperTileEntity() {
        super(TileEntityInit.TELEPORTATION_PAPER.get());
    }

    @Override
    public void tick() {
        //System.out.println("Tick");
        if (this.iftimer) {
            this.teletimer += 1;
           // System.out.println("Tick Inside " + this.iftimer);
            if (this.teletimer >= 400) {
               // System.out.println("FIRED  " + this.teletimer);
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F), false );
                placer.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                world.destroyBlock(this.pos, false);
                this.teletimer = 0;
                this.iftimer = false;
            }
            if (this.teletimer == 300)
            {
                if (this.placer instanceof ServerPlayerEntity && !world.isRemote) {
                    placer.sendMessage(new StringTextComponent("5 Seconds remaining on your Transportation Sealed Paper"));
                }
            }
        }
    }

    public void setPlacer(@Nullable LivingEntity placer)
    {
        this.placer = placer;
    }

    public void setTeletimer (int timer)
    {
        this.teletimer = timer;
    }
    public void setIftimer (boolean timer)
    {
        this.iftimer = timer;
    }
}
