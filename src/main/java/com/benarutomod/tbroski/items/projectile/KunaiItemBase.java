package com.benarutomod.tbroski.items.projectile;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.projectile.KunaiEntity;
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

public class KunaiItemBase extends Item {

    private final float attackDamage = 3F;
    private final float attackSpeed = -2F;

    public KunaiItemBase() {
        super(new Item.Properties().group(Main.TAB)
                .maxStackSize(16));
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        //System.out.println("RClicked");
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.BLOCK_ANVIL_FALL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            KunaiEntity kunaientity = new KunaiEntity(worldIn, playerIn);
            kunaientity.setItem(itemstack);
            kunaientity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw + 20, 0.0F, 1.75F, 1.0F);
            worldIn.addEntity(kunaientity);
            KunaiEntity kunaientity2 = new KunaiEntity(worldIn, playerIn);
            kunaientity2.setItem(itemstack);
            kunaientity2.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw + 10, 0.0F, 1.75F, 1.0F);
            worldIn.addEntity(kunaientity2);
            KunaiEntity kunaientity3 = new KunaiEntity(worldIn, playerIn);
            kunaientity3.setItem(itemstack);
            kunaientity3.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.75F, 1.0F);
            worldIn.addEntity(kunaientity3);
            KunaiEntity kunaientity4 = new KunaiEntity(worldIn, playerIn);
            kunaientity4.setItem(itemstack);
            kunaientity4.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw - 10, 0.0F, 1.75F, 1.0F);
            worldIn.addEntity(kunaientity4);
            KunaiEntity kunaientity5 = new KunaiEntity(worldIn, playerIn);
            kunaientity5.setItem(itemstack);
            kunaientity5.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw - 20, 0.0F, 1.75F, 1.0F);
            worldIn.addEntity(kunaientity5);
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
