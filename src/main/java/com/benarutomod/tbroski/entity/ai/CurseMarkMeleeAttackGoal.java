package com.benarutomod.tbroski.entity.ai;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketCurseMarkMeleeAttacked;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Random;

public class CurseMarkMeleeAttackGoal extends MeleeAttackGoal {

    private final int chance;
    private final float damage;

    public CurseMarkMeleeAttackGoal(CreatureEntity creature, double speedIn, boolean useLongMemory, int chance, float damage) {
        super(creature, speedIn, useLongMemory);
        this.chance = chance;
        this.damage = damage;
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
        double d0 = this.getAttackReachSqr(enemy);
        if (distToEnemySqr <= d0 && this.attackTick <= 0) {
            this.attackTick = 20;
            this.attacker.swingArm(Hand.MAIN_HAND);
            if (enemy instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) enemy;
                LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

                if (new Random().nextInt(chance) == 0) {
                    player.addPotionEffect(new EffectInstance(Effects.WITHER, 200, 0));
                    player_cap.setPlayerCurseMark(1);
                    player.sendStatusMessage(new TranslationTextComponent("cursemark." + Main.MODID + ".uponmessage"), true);

                    if (!player.world.isRemote) NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketCurseMarkMeleeAttacked());
                }
            }
            enemy.attackEntityFrom(DamageSource.causeMobDamage(this.attacker), damage);
        }
    }
}
