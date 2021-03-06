package com.benarutomod.tbroski.event;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.api.internal.BeNMClan;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.common.jutsu.EffectsJutsu;
import com.benarutomod.tbroski.entity.clones.AbstractCloneEntity;
import com.benarutomod.tbroski.api.entity.AbstractShinobiEntity;
import com.benarutomod.tbroski.init.*;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketClanSync;
import com.benarutomod.tbroski.networking.packets.PacketNature;
import com.benarutomod.tbroski.networking.packets.PacketPlayerDojutsuSync;
import com.benarutomod.tbroski.networking.packets.chakra.*;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Random;

public class PlayerEvents {

    public static void regenerateChakra(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        if (player.isAlive()) {
            player.getPersistentData().putInt("regentick", player.getPersistentData().getInt("regentick") + 1);
            LazyOptional<IPlayerHandler> capabilities = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());
            if (player.getPersistentData().getInt("regentick") >= 60 && playercap.returnmaxChakra() != 0) {
                float chakra = playercap.returnChakra();
                float maxChakra = playercap.returnmaxChakra();
                float regenChakra = playercap.returnregenChakra();
                playercap.addChakra(regenChakra);
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketChakraSync(chakra));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketRegenChakraSync(playercap.returnregenChakra(), true));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketMaxChakraSync(maxChakra, player.getPersistentData().getInt("restrictedchakra"), true));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketColorChakraSync(playercap.returncolorChakra()));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketChakraControlSync(playercap.returnChakraControl(), true));
                player.getPersistentData().putInt("regentick", 0);
            }
        }
    }

    public static void checkNatures(TickEvent.PlayerTickEvent event) {
        LazyOptional<IPlayerHandler> capabilities = event.player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playercap = capabilities.orElse(new PlayerCapability());

        if ((playercap.hasWindNature() && playercap.hasEarthNature()) && !playercap.hasMagnetNature()) {
            playercap.setMagnetNature(true);
        }
        if ((playercap.hasWaterNature() && playercap.hasEarthNature()) && !playercap.hasWoodNature()) {
            playercap.setWoodNature(true);
        }
        if ((playercap.hasLightningNature() && playercap.hasEarthNature()) && !playercap.hasExplosionNature()) {
            playercap.setExplosionNature(true);
        }
        if ((playercap.hasFireNature() && playercap.hasEarthNature()) && !playercap.hasLavaNature()) {
            playercap.setLavaNature(true);
        }
        if ((playercap.hasWindNature() && playercap.hasWaterNature()) && !playercap.hasIceNature()) {
            playercap.setIceNature(true);
        }
        if ((playercap.hasWindNature() && playercap.hasFireNature()) && !playercap.hasScorchNature()) {
            playercap.setScorchNature(true);
        }
        if ((playercap.hasWaterNature() && playercap.hasFireNature()) && !playercap.hasBoilNature()) {
            playercap.setBoilNature(true);
        }
        if ((playercap.hasWaterNature() && playercap.hasLightningNature()) && !playercap.hasStormNature()) {
            playercap.setStormNature(true);
        }
    }

    public static void PlayerJoinedWorld(EntityJoinWorldEvent event) {
        PlayerEntity player = (PlayerEntity) event.getEntity();
        LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

        player.getPersistentData().putInt("randomakatsukitimerint", new Random().nextInt(24000));
        //player.sendMessage(new StringTextComponent("You are interested in pests, an Aburame?"));
        //player.sendMessage(new StringTextComponent("You gloat over food, an Akimichi?"));
        if (!player_cap.joinWorld()) {
            player_cap.setjoinWorld(true);

            player.addItemStackToInventory(new ItemStack(ItemInit.KUNAI.get()));

            ArrayList<BeNMClan> clans = new ArrayList<>();
            for (BeNMClan clan : BeNMRegistry.CLANS.getValues()) {
                if (clan != ClanInit.NULL) {
                    clans.add(clan);
                }
            }
            double totalWeight = 0.0D;
            for (BeNMClan clan : clans)
            {
                totalWeight += clan.getWeight();
            }

            int randomIndex = -1;
            double random = Math.random() * totalWeight;
            for (int i = 0; i < clans.size(); ++i)
            {
                random -= clans.get(i).getWeight();
                if (random <= 0.0D)
                {
                    randomIndex = i;
                    break;
                }
            }
            BeNMClan randomClan = clans.get(randomIndex);
            player_cap.setPlayerClan(randomClan);
            NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketClanSync(randomClan.getString(), true));

            if (player_cap.returnPlayerClan().getClanDojutsu() != DojutsuInit.NULL) {
                AdvancementHelper.grantAdvancement((ServerPlayerEntity) player, Main.MODID + ":dojutsu/dojutsu");
                if (player_cap.returnPlayerClan().getClanDojutsu().getAdvancement() != null)
                    AdvancementHelper.grantAdvancement((ServerPlayerEntity) player, player_cap.returnPlayerClan().getClanDojutsu().getAdvancement());
                player_cap.setPlayerLeftDojutsu(player_cap.returnPlayerClan().getClanDojutsu());
                player_cap.setPlayerRightDojutsu(player_cap.returnPlayerClan().getClanDojutsu());
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketPlayerDojutsuSync(player_cap.returnPlayerLeftDojutsu().getString(), true, true));
                NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketPlayerDojutsuSync(player_cap.returnPlayerRightDojutsu().getString(), false, true));
            }

            player.sendMessage(new StringTextComponent(player_cap.returnPlayerClan().getClanMessage()));
        }
    }


    public static void clayRightClick(PlayerInteractEvent.RightClickItem event) {
        Item item = event.getItemStack().getItem();
        PlayerEntity playerEntity = event.getPlayer();
        if (playerEntity.isCrouching() && item == Items.CLAY_BALL || item == ItemInit.CLAY_BALL_C1.get() || item == ItemInit.CLAY_BALL_C2.get() || item == ItemInit.CLAY_BALL_C3.get()) {
            Vec3d vec3d = playerEntity.getEyePosition(1.0F);
            Vec3d vec3d1 = playerEntity.getLook(1.0F);
            Vec3d vec3d2 = vec3d.add(vec3d1.x * 4, vec3d1.y * 4, vec3d1.z * 4);
            AxisAlignedBB axisalignedbb = playerEntity.getBoundingBox().expand(vec3d1.scale(4)).grow(1.0D, 1.0D, 1.0D);
            EntityRayTraceResult entityRayTraceResult = ProjectileHelper.rayTraceEntities(playerEntity, vec3d, vec3d2, axisalignedbb, $ -> !playerEntity.isSpectator() && playerEntity.canBeCollidedWith(), 4);

            if (entityRayTraceResult != null && !event.getWorld().isRemote) {
                if (entityRayTraceResult.getEntity() instanceof LivingEntity && !(entityRayTraceResult.getEntity() instanceof PlayerEntity) && !(entityRayTraceResult.getEntity() instanceof EnderDragonEntity) && !(entityRayTraceResult.getEntity() instanceof WitherEntity) && !(entityRayTraceResult.getEntity() instanceof AbstractShinobiEntity) && !(entityRayTraceResult.getEntity() instanceof AbstractCloneEntity) && entityRayTraceResult.getEntity().isNonBoss()) { //&& !entityRayTraceResult.getEntity().isNonBoss()
                    if (!playerEntity.abilities.isCreativeMode)
                        event.getItemStack().shrink(1);
                    Item itemToGive = ItemInit.MOLDED_CLAY.get();
                    if (item == ItemInit.CLAY_BALL_C1.get())
                        itemToGive = ItemInit.MOLDED_CLAY_C1.get();
                    if (item == ItemInit.CLAY_BALL_C2.get())
                        itemToGive = ItemInit.MOLDED_CLAY_C2.get();
                    if (item == ItemInit.CLAY_BALL_C3.get())
                        itemToGive = ItemInit.MOLDED_CLAY_C3.get();
                    ItemStack moldedClay = new ItemStack(itemToGive);
                    CompoundNBT nbt = new CompoundNBT();

                    nbt.putString("affiliatedmob", entityRayTraceResult.getEntity().getEntityString());
                    moldedClay.setTag(nbt);
                    moldedClay.setDisplayName(new StringTextComponent(entityRayTraceResult.getEntity().getDisplayName().getString() + " molded Clay"));
                    playerEntity.addItemStackToInventory(moldedClay);
                }
            }
        }
    }


    public static void checkPlayerDojutsuTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

        if (player.getPersistentData().getInt("restrictedchakra") >= 0) {
            player.getPersistentData().putInt("restrictedchakra", player.getPersistentData().getInt("restrictedchakra") - 1);
        }
        if (player.getPersistentData().getInt("restrictedchakra") >= 1) {
            float newChakra = player_cap.returnmaxChakra() * 0.75F;
            if (player_cap.returnChakra() >= newChakra) {
                player_cap.setChakra(newChakra);
            }
        }

        if (player_cap.returnPlayerBodyMode().getPlayerEffects().size() > 0 && player_cap.returnBodyInfusionToggled()) {
            for (Effect effect : player_cap.returnPlayerBodyMode().getPlayerEffects()) {
                player.addPotionEffect(new EffectInstance(effect, 40, 0));
            }
        }

        if (player_cap.returnPlayerBodyMode().allowsPlayerFlight() && player_cap.returnBodyInfusionToggled()) {
            player.abilities.allowFlying = true;
        }
        else if (!player.abilities.isCreativeMode) {
            player.abilities.allowFlying = false;
            player.abilities.isFlying = false;
        }
    }

    public static void checkPlayerDojutsuDamage(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

            if (player_cap.returnBodyInfusionToggled() && event.getSource() != DamageInit.DODGED && (player_cap.returnPlayerLeftDojutsu().canDodgeDamage() || player_cap.returnPlayerRightDojutsu().canDodgeDamage())) {
                if (new Random().nextInt(6) == 0) {
                    event.setCanceled(true);
                    player.attackEntityFrom(DamageInit.DODGED, Math.min(event.getAmount() - Config.COMMON.sharinganDodgedDamage.get(), 0.0F));
                    player.sendStatusMessage(new StringTextComponent(new TranslationTextComponent("event." + Main.MODID + ".livingdamage.dodgedmessage").getString() + Config.COMMON.sharinganDodgedDamage.get()), true);
                }
            }
        }
        if (event.getSource().getTrueSource() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
            LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());
            if (event.getSource().damageType.equalsIgnoreCase("player")) {
                if (player_cap.returnBodyInfusionToggled()) {
                    if (player_cap.returnPlayerBodyMode().getAttackingEffect() != null) {
                        if (new Random().nextInt(5) == 0) {
                            event.getEntityLiving().addPotionEffect(new EffectInstance(player_cap.returnPlayerBodyMode().getAttackingEffect(), 40, 1));
                        }
                    }
                    if (player_cap.returnPlayerLeftDojutsu().doesRestrictChakra() || player_cap.returnPlayerRightDojutsu().doesRestrictChakra()) {
                        if (event.getEntityLiving() instanceof PlayerEntity) {
                            event.getEntityLiving().getPersistentData().putInt("restrictedchakra", (((int) player_cap.returnChakraControl()) * 25) + 25);
                        }
                        else {
                            event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, (((int) player_cap.returnChakraControl()) * 25) + 25, 1));
                        }
                    }
                }
            }
        }
    }

    public static void checkPlayerDojutsuDeath(LivingDeathEvent event) {
        if (!event.getEntity().world.isRemote && event.getSource().getTrueSource() instanceof PlayerEntity && event.getEntity() instanceof AbstractShinobiEntity) {
            PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
            AbstractShinobiEntity entity = (AbstractShinobiEntity) event.getEntity();

            LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());

            if (entity.getClan() == ClanInit.UCHIHA) {
                if (player_cap.returnPlayerLeftDojutsu() == DojutsuInit.SHARINGAN) {
                    player_cap.setPlayerLeftDojutsu(DojutsuInit.MANGEKYOU_SHARINGAN);
                    AdvancementHelper.grantAdvancement((ServerPlayerEntity) player, Main.MODID + ":dojutsu/mangekyou_sharingan");
                    NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketPlayerDojutsuSync(player_cap.returnPlayerLeftDojutsu().getString(), true, true));
                    player.sendStatusMessage(new TranslationTextComponent("event." + Main.MODID + ".livingdeath.mangekyousharinganmessage"), true);
                }
                if (player_cap.returnPlayerRightDojutsu() == DojutsuInit.SHARINGAN) {
                    player_cap.setPlayerRightDojutsu(DojutsuInit.MANGEKYOU_SHARINGAN);
                    AdvancementHelper.grantAdvancement((ServerPlayerEntity) player, Main.MODID + ":dojutsu/mangekyou_sharingan");
                    NetworkLoader.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new PacketPlayerDojutsuSync(player_cap.returnPlayerRightDojutsu().getString(), false, true));
                    player.sendStatusMessage(new TranslationTextComponent("event." + Main.MODID + ".livingdeath.mangekyousharinganmessage"), true);
                }
            }
        }
    }

    public static void checkInfusion(TickEvent.PlayerTickEvent event) {
        LazyOptional<IPlayerHandler> playerCapability = event.player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler player_cap = playerCapability.orElse(new PlayerCapability());

        int taijutsuModifier0 = 0;
        int taijutsuModifier1 = 0;
        if (player_cap.returnTaijutsu() >= 15) {
            taijutsuModifier0 = 3; taijutsuModifier1 = 2;
        }
        else if (player_cap.returnTaijutsu() >= 10) {
            taijutsuModifier0 = 2; taijutsuModifier1 = 1;
        }
        else if (player_cap.returnTaijutsu() >= 5) {
            taijutsuModifier0 = 1; taijutsuModifier1 = 0;
        }

        if (player_cap.returnHandInfusionToggled()) {
            EffectsJutsu.HandInfusion(event.player, 3, taijutsuModifier0, taijutsuModifier1);
        }
        if (player_cap.returnBodyInfusionToggled()) {
            EffectsJutsu.BodyInfusion(event.player, 4, taijutsuModifier0, taijutsuModifier1);
        }
        if (player_cap.returnLegInfusionToggled()) {
            EffectsJutsu.LegInfusion(event.player, 3, taijutsuModifier0, taijutsuModifier1);
        }
    }
}
