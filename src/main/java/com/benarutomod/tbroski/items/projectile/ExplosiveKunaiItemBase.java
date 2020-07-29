package com.benarutomod.tbroski.items.projectile;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.projectile.ExplosiveKunaiEntity;
import com.google.common.collect.Multimap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ExplosiveKunaiItemBase extends Item {

    private final float attackDamage = 3F;
    private final float attackSpeed = -2F;

    public ExplosiveKunaiItemBase() {
        super(new Item.Properties().group(Main.TAB)
                .maxStackSize(16));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        worldIn.playSound(playerIn, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            ExplosiveKunaiEntity kunaientity = new ExplosiveKunaiEntity(worldIn, playerIn);
            kunaientity.setItem(itemstack);
            kunaientity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.25F, 1.0F);
            worldIn.addEntity(kunaientity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemstack.shrink(1);
        }

        return ActionResult.resultSuccess(itemstack);
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(EquipmentSlotType equipmentSlot) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot);
        if (equipmentSlot == EquipmentSlotType.MAINHAND) {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackDamage, AttributeModifier.Operation.ADDITION));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double) this.attackSpeed, AttributeModifier.Operation.ADDITION));
        }
        return multimap;
    }
}
