package com.benarutomod.tbroski.init.jutsu.nature.kekkai;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.entity.clones.ExplosionCloneEntity;
import com.benarutomod.tbroski.init.EntityInit;
import com.benarutomod.tbroski.init.ItemInit;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ExplosionNatureJutsuInit {

    public static void registerExplosionJutsu(BeNMRegistry.JutsuRegistry jutsuRegistry, IBeNMPlugin pluginIn) {
        jutsuRegistry.register(new BeNMJutsu(pluginIn, "explosion_clone", BeNMJutsu.Type.EXPLOSION_NATURE, 10, 120F, 208, 0, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote) {
                ExplosionCloneEntity entity = new ExplosionCloneEntity(EntityInit.EXPLOSION_CLONE.get(), playerIn.world);
                entity.setPositionAndUpdate(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
                entity.setOwnerID(playerIn.getEntityId());
                playerIn.world.addEntity(entity);
            }
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "landmine_fist", BeNMJutsu.Type.EXPLOSION_NATURE, 17, 1F, 208, 16, true, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            // Marker
        }).addAttackEventListener((attacker, target) -> {
            target.setMotion(target.getMotion().x * 1.6, target.getMotion().y + 0.2F, target.getMotion().z * 1.6);
        }));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "clay_c1", BeNMJutsu.Type.EXPLOSION_NATURE, 6, 100F, 208, 32, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote && playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == Items.CLAY_BALL) {
                playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).shrink(1);
                playerIn.addItemStackToInventory(new ItemStack(ItemInit.CLAY_BALL_C1.get()));
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == Items.CLAY_BALL));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "clay_c2", BeNMJutsu.Type.EXPLOSION_NATURE, 7, 120F, 208, 48, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote && playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == Items.CLAY_BALL) {
                playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).shrink(1);
                playerIn.addItemStackToInventory(new ItemStack(ItemInit.CLAY_BALL_C2.get()));
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == Items.CLAY_BALL));

        jutsuRegistry.register(new BeNMJutsu(pluginIn, "clay_c3", BeNMJutsu.Type.EXPLOSION_NATURE, 8, 140F, 208, 64, false, (playerIn, taijutsuModifier0, taijutsuModifier1, playerCapability) -> {
            if (!playerIn.world.isRemote && playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == Items.CLAY_BALL) {
                playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).shrink(1);
                playerIn.addItemStackToInventory(new ItemStack(ItemInit.CLAY_BALL_C3.get()));
            }
        }).setExtraJutsuChecks((playerIn, taijutsuModifier0, taijutsuModifier1) -> playerIn.getItemStackFromSlot(EquipmentSlotType.MAINHAND).getItem() == Items.CLAY_BALL));
    }
}
