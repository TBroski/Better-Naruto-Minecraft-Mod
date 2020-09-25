package com.benarutomod.tbroski.api.item;

import com.benarutomod.tbroski.entity.EtherealItemEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class EtherealItem extends Item {

    private EtherealProperties properties;

    private boolean doesRenderOnFire;
    private float width;
    private float height;
    private Color color;

    public EtherealItem(Properties properties, EtherealProperties etherealProperties) {
        super(properties);
        this.properties = etherealProperties;
    }

    public EtherealItem setRenderFire(float width, float height, Color color) {
        this.doesRenderOnFire = true;
        this.color = color;
        this.width = width;
        this.height = height;
        return this;
    }

    public boolean doesRenderOnFire() {
        return this.doesRenderOnFire;
    }

    public Color getColor() {
        return color;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public EtherealProperties getProperties() {
        return properties;
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack droppedItem, PlayerEntity player) {
        if (!droppedItem.isEmpty()) {
            double d0 = player.getPosYEye() - (double) 0.3F;
            EtherealItemEntity itementity = new EtherealItemEntity(player.world, player.getPosX(), d0, player.getPosZ(), droppedItem);
            itementity.setPickupDelay(40);
            itementity.setThrowerId(player.getUniqueID());
            float f7 = 0.3F;
            float f8 = MathHelper.sin(player.rotationPitch * ((float) Math.PI / 180F));
            float f2 = MathHelper.cos(player.rotationPitch * ((float) Math.PI / 180F));
            float f3 = MathHelper.sin(player.rotationYaw * ((float) Math.PI / 180F));
            float f4 = MathHelper.cos(player.rotationYaw * ((float) Math.PI / 180F));
            float f5 = player.getRNG().nextFloat() * ((float) Math.PI * 2F);
            float f6 = 0.02F * player.getRNG().nextFloat();
            itementity.setMotion((double) (-f3 * f2 * 0.3F) + Math.cos((double) f5) * (double) f6, (double) (-f8 * 0.3F + 0.1F + (player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.1F), (double) (f4 * f2 * 0.3F) + Math.sin((double) f5) * (double) f6);
            player.world.addEntity(itementity);
        }
        return false;
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if (!(entity instanceof EtherealItemEntity)) {
            entity.remove();
            return true;
        }
/*        if (stack.getItem() instanceof EtherealItem && ((EtherealItem) stack.getItem()).doesRenderOnFire()) {
            entity.setFire(1);
        }*/
        return false;
    }


    public static class EtherealProperties {

        private boolean cancelsNinjutsu;
        private float damage;
        private float armor;
        private float range;

        public EtherealProperties setDamage(float damage) {
            this.damage = damage;
            return this;
        }

        public EtherealProperties setArmor(float armor) {
            this.armor = armor;
            return this;
        }

        public EtherealProperties setRange(float range) {
            this.range = range;
            return this;
        }

        public EtherealProperties setCancelsNinjutsu() {
            this.cancelsNinjutsu = true;
            return this;
        }

        public float getArmor() {
            return armor;
        }

        public float getDamage() {
            return damage;
        }

        public float getRange() {
            return range;
        }

        public boolean doesCancelNinjutsu() {
            return cancelsNinjutsu;
        }
    }
}
