package com.benarutomod.tbroski.event;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;

import java.util.ArrayList;
import java.util.Random;

public class GlobalEvents {

    public static void playerRaid(TickEvent.PlayerTickEvent event)
    {
        LazyOptional<IPlayerHandler> playerc = event.player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());
        if (!event.player.abilities.isCreativeMode) { //player_cap.returnShinobiLevel() >= 2

            event.player.getPersistentData().putInt("akatsukitimer", event.player.getPersistentData().getInt("akatsukitimer") + 1);
            if (event.player.getPersistentData().getInt("akatsukitimer") >= 24000 + event.player.getPersistentData().getInt("randomakatsukitimerint")) { //24000 + event.player.getPersistentData().getInt("randomakatsukitimerint")
                event.player.getPersistentData().putInt("akatsukitimer", 0);
                event.player.getPersistentData().putInt("randomakatsukitimerint", new Random().nextInt(24000));
                double d0 = event.player.getPosX() + (new Random().nextDouble() - 0.5D) * 96.0D;
                double d1 = event.player.getPosY() + (double)(new Random().nextInt(96) - 32);
                double d2 = event.player.getPosZ() + (new Random().nextDouble() - 0.5D) * 96.0D;
                int i = new Random().nextInt(10);
                boolean flag = false;
                while (i >= 0)
                {
                    ArrayList<LivingEntity> akatsukiMembers = new ArrayList<>();
                    akatsukiMembers.add(new ZombieEntity(EntityType.ZOMBIE, event.player.world));
                    akatsukiMembers.add(new SkeletonEntity(EntityType.SKELETON, event.player.world));
                    i -= 1;
                    LivingEntity spawningAkatsuki = akatsukiMembers.get(new Random().nextInt(2));
                    flag = spawningAkatsuki.attemptTeleport(d0, d1, d2, true);
                    if (flag) {
                        event.player.world.playSound(event.player, event.player.getPosX(), event.player.getPosY(), event.player.getPosZ(), SoundInit.PAIN_DISC_LAZY.get(), SoundCategory.MUSIC, 0.5F, 0.4F);
                        event.player.world.addEntity(spawningAkatsuki);
                    }
                }
                if (flag)
                {
                    event.player.sendStatusMessage(new TranslationTextComponent("event." + Main.MODID + ".akatsuki.uponmessage"), true);
                }
            }
        }
    }
}
