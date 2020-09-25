package com.benarutomod.tbroski.entity.npc;

import com.benarutomod.tbroski.api.enums.Nature;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.util.helpers.ClanHelper;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

public class ClanHelperEntity extends CreatureEntity {

    private BeNMClan clan;

    private static String[] frontName = {
            "Ha",
            "Naru",
            "Sasu",
            "Kiri",
            "Obi",
            "Rame",
    };
    private static String[] backName = {
            "ke",
            "to",
            "maru",
            "somo",
    };

    public ClanHelperEntity(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (clan != null)
            compound.putString("clan", this.clan.getString());
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.clan = ClanHelper.getClanFromString(compound.getString("clan"));
    }

    @Nullable
    @Override
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        if (this.clan == null) {
            this.clan = ClanHelper.getClansWithNPC().get(this.getRNG().nextInt(ClanHelper.getClansWithNPC().size()));
            this.setCustomName(new StringTextComponent(generateName(this.getRNG()) + " " + clan.getString().substring(0,1).toUpperCase() + clan.getString().substring(1).toLowerCase()));
            this.setCustomNameVisible(true);
        }
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        if (hand == Hand.MAIN_HAND && !this.world.isRemote) {
            String name = this.getDisplayName().getString();
            boolean flag = false;
            if (this.clan.getClanNature() == Nature.NULL) {
                flag = true;
            } else {
                IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
                switch (this.clan.getClanNature()) {
                    case EARTH:
                        if (playercap.hasEarthNature()) {
                            flag = true;
                        }
                        else {
                            player.sendMessage(new StringTextComponent(name + ": You need Earth Release."));
                        }
                        break;
                    case WIND:
                        if (playercap.hasWindNature()) {
                            flag = true;
                        }
                        else {
                            player.sendMessage(new StringTextComponent(name + ": You need Wind Release."));
                        }
                        break;
                    case WATER:
                        if (playercap.hasWaterNature()) {
                            flag = true;
                        }
                        else {
                            player.sendMessage(new StringTextComponent(name + ": You need Water Release."));
                        }
                        break;
                    case LIGHTNING:
                        if (playercap.hasLightningNature()) {
                            flag = true;
                        }
                        else {
                            player.sendMessage(new StringTextComponent(name + ": You need Lightning Release."));
                        }
                        break;
                    case FIRE:
                        if (playercap.hasFireNature()) {
                            flag = true;
                        }
                        else {
                          player.sendMessage(new StringTextComponent(name + ": You need Fire Release."));
                        }
                        break;
                }
            }
            if (flag) {
                if (this.clan.getStartingTaijutsu() > 5) {
                    player.sendMessage(new StringTextComponent(name + ": Our jutsu require a strong body."));
                }
                if (this.clan.getStartingGenjutsu() > 5) {
                    player.sendMessage(new StringTextComponent(name + ": Our jutsu require good eyes."));
                }
                player.sendMessage(new StringTextComponent(name + ": I'll leave this ancient tablet with you."));
                ItemStack itemStack = new ItemStack(ItemInit.TABLET.get());
                CompoundNBT nbt = new CompoundNBT();
                nbt.putString("clan", this.clan.getString());
                itemStack.setTag(nbt);
                itemStack.setDisplayName(new StringTextComponent(this.clan.getString().substring(0,1).toUpperCase() + this.clan.getString().substring(1).toLowerCase() + " Tablet"));
                player.addItemStackToInventory(itemStack);
                this.remove();
                return true;
            }
        }
        return false;
    }

    private static String generateName(Random random) {
        return frontName[random.nextInt(frontName.length)] + backName[random.nextInt(backName.length)];
    }
}
