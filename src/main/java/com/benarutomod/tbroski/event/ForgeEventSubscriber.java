package com.benarutomod.tbroski.event;

import com.benarutomod.tbroski.Main;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.container.ExtendedPlayerInventory;
import com.benarutomod.tbroski.api.internal.jutsu.BeNMJutsu;
import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.entity.mobs.bijuu.AbstractBijuuEntity;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.KakuzuEntity;
import com.benarutomod.tbroski.init.DimensionInit;
import com.benarutomod.tbroski.init.EffectInit;
import com.benarutomod.tbroski.init.ItemInit;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketPlayerBodyModeSync;
import com.benarutomod.tbroski.networking.packets.PacketSusanooItemsSync;
import com.benarutomod.tbroski.networking.packets.PacketToggleInfusionBoolean;
import com.benarutomod.tbroski.networking.packets.PacketTruthSeekingOrbsSync;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketJutsuNBTSync;
import com.benarutomod.tbroski.networking.packets.settings.PacketBackSlotSync;
import com.benarutomod.tbroski.networking.packets.settings.PacketToggleScrollBoolean;
import com.benarutomod.tbroski.util.helpers.AdvancementHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.entity.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiContainerEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.network.PacketDistributor;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.benarutomod.tbroski.init.DimensionInit.TSUKUYOMI_DIMENSION;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

    public static final ResourceLocation EMPTY_BACK_SLOT = new ResourceLocation(Main.MODID, "items/empty_back_slot");

    private static boolean curiosLoaded = false;

    private static Field xPosField;
    private static Field yPosField;
    private static Field inventoryField;
    private static Field containerField;

    private Random rand = new Random();

    public static boolean isCuriosLoaded()
    {
        return curiosLoaded;
    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote) {
            if (event.getEntity() instanceof MobEntity) {
                EntityEvents.AddEntityAi(event);
            }
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEvents.PlayerJoinedWorld(event);

                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) event.getEntity();
                AdvancementHelper.grantAdvancement(serverPlayerEntity,  Main.MODID + ":shinobi_beginnings");
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        PlayerEvents.checkPlayerDojutsuTick(event);

        if (player.isAlive()) {
            IPlayerHandler playercap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER).orElseThrow(() -> new RuntimeException("CAPABILITY_PLAYER NOT FOUND!"));
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (jutsu.isToggle()) {
                    String nbtName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
                    if (player.getPersistentData().getBoolean(nbtName)) {
                        int taijutsuModifier0 = 0;
                        int taijutsuModifier1 = 0;
                        if (playercap.returnTaijutsu() >= 15) {
                            taijutsuModifier0 = 3;
                            taijutsuModifier1 = 2;
                        } else if (playercap.returnTaijutsu() >= 10) {
                            taijutsuModifier0 = 2;
                            taijutsuModifier1 = 1;
                        } else if (playercap.returnTaijutsu() >= 5) {
                            taijutsuModifier0 = 1;
                            taijutsuModifier1 = 0;
                        }
                        jutsu.act(player, taijutsuModifier0, taijutsuModifier1);
                    }
                }
            }

            PlayerEvents.checkNatures(event);
            PlayerEvents.checkInfusion(event);
            if (!player.world.isRemote) {
                GlobalEvents.playerRaid(event);
                PlayerEvents.regenerateChakra(event);
/*                if (playercap.returnBodyInfusionToggled() && playercap.hasMagnetNature()) {
                    List<ItemEntity> itemEntities = player.world.getEntitiesWithinAABB(ItemEntity.class, player.getBoundingBox().grow(5));
                    if (itemEntities.size() > 0) {
                        Iterator iterator = itemEntities.iterator();
                        while (iterator.hasNext()) {
                            ItemEntity item = (ItemEntity) iterator.next();
                            double distX = player.getPosX() - item.getPosX();
                            double distZ = player.getPosZ() - item.getPosZ();
                            double distY = item.getPosY() + 1.5D - item.getPosY();
                            double dir = Math.atan2(distZ, distX);
                            double speed = 0.2F / item.getDistance(player) * 0.5;
                            if (distY < 0) {
                                item.setMotion(item.getMotion().x, item.getMotion().y + speed, item.getMotion().z);
                            }
                            item.setMotion(Math.cos(dir) * speed, item.getMotion().y, item.getMotion().z);
                            item.setMotion(item.getMotion().x, item.getMotion().y, Math.sin(dir) * speed);
                        }
                    }
                }*/
            }
        }

        if(curiosLoaded)
            return;

        if(event.phase != TickEvent.Phase.START)
            return;

        if(!player.world.isRemote && player.inventory instanceof ExtendedPlayerInventory)
        {
            ExtendedPlayerInventory inventory = (ExtendedPlayerInventory) player.inventory;
            if(!inventory.backArray.get(0).equals(inventory.backInventory.get(0)))
            {
                //PacketHandler.instance.send(PacketDistributor.TRACKING_ENTITY.with(() -> player), new MessageUpdateBackpack(player.getEntityId(), !inventory.backpackInventory.get(0).isEmpty()));
                inventory.backArray.set( 0, inventory.backInventory.get(0));
            }
        }
    }

    @SubscribeEvent
    public void blockBreak(BlockEvent.BreakEvent event) {
        int rand_rice_drop = rand.nextInt(5);
        Block block = event.getState().getBlock();
        BlockPos pos = event.getPos();
        World world = event.getWorld().getWorld();
        if (block == Blocks.GRASS) {
            if (!world.isRemote) {
                if (rand_rice_drop == 1) {
                    world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ItemInit.RICE.get())));
                }
            }
        }
    }

    @SubscribeEvent
    public void onAttackTargetEvent(LivingSetAttackTargetEvent event) {
        EntityEvents.OnAttackTarget(event);
    }

    @SubscribeEvent
    public void onPlayerTracking(PlayerEvent.StartTracking event)
    {
        if (event.getTarget() instanceof PlayerEntity)
        {
            PlayerEntity target = (PlayerEntity) event.getTarget();
            if (!event.getEntity().world.isRemote) {
                int targetID = target.getEntityId();
                event.getTarget().getEntityId();
                LazyOptional<IPlayerHandler> capabilities = target.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
                IPlayerHandler targetcap = capabilities.orElse(new PlayerCapability());
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketToggleScrollBoolean(targetcap.returnToggleScrollRenderer(), true, targetID));
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketPlayerBodyModeSync(targetcap.returnPlayerBodyMode().getName(), targetID, true));
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketToggleInfusionBoolean(1, true, targetcap.returnHandInfusionToggled(), targetID));
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketToggleInfusionBoolean(2, true, targetcap.returnBodyInfusionToggled(), targetID));
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketToggleInfusionBoolean(3, true, targetcap.returnLegInfusionToggled(), targetID));
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketSusanooItemsSync(targetID, targetcap.getSusanooMainHand(), targetcap.getSusanooOffHand(), true));
                NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketTruthSeekingOrbsSync(targetID, targetcap.getTruthSeekingOrbAmount()));
                for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                    NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketJutsuNBTSync(targetID, jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName(), targetcap.hasJutsuBoolean(jutsu)));
                }
                if (target.inventory instanceof ExtendedPlayerInventory) NetworkLoader.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> (ServerPlayerEntity) event.getPlayer()), new PacketBackSlotSync(targetID, !Main.getBackpackStack(target).isEmpty(), Main.getBackpackStack(target).getItem().getRegistryName().toString()));
            }
        }
    }

/*    @SubscribeEvent
    public void renderPlayer(RenderPlayerEvent.Pre event) {
        PlayerEntity player = event.getPlayer();
        PlayerRenderer render = event.getRenderer();
        PlayerModel<AbstractClientPlayerEntity> model = render.getEntityModel();
        if (player.isSprinting() && !player.isCrouching()) {

            IVertexBuilder buffer = event.getBuffers().getBuffer(model.getRenderType(((AbstractClientPlayerEntity) player).getLocationSkin()));
            ModelRenderer newBody = new PlayerModel(0, false).bipedBody;
            ModelRenderer newLeftArm = new PlayerModel(0, false).bipedLeftArm;
            ModelRenderer newRightArm = new PlayerModel(0, false).bipedRightArm;

*//*            model.bipedLeftArm.copyModelAngles(newLeftArm);
            model.bipedRightArm.copyModelAngles(newRightArm);*//*
            newLeftArm.copyModelAngles(model.bipedLeftArm);
            newRightArm.copyModelAngles(model.bipedRightArm);
            newBody.copyModelAngles(model.bipedBody);
            model.bipedLeftArm.showModel = false;
            model.bipedRightArm.showModel = false;
            model.bipedBody.showModel = false;

            event.getMatrixStack().push();

            event.getMatrixStack().rotate(new Quaternion(180,0,0, true));
            event.getMatrixStack().rotate(new Quaternion(0, player.rotationYaw,0, true));
            event.getMatrixStack().translate(0, -1.35, -0.2);
            newBody.rotateAngleX = 0.42F;
            newLeftArm.rotateAngleX = 1.0F;
            newRightArm.rotateAngleX = 1.0F;

            newBody.render(event.getMatrixStack(), buffer, event.getLight(), OverlayTexture.NO_OVERLAY);
            newLeftArm.render(event.getMatrixStack(), buffer, event.getLight(), OverlayTexture.NO_OVERLAY);
            newRightArm.render(event.getMatrixStack(), buffer, event.getLight(), OverlayTexture.NO_OVERLAY);

            event.getMatrixStack().pop();
        }
    }*/

    @SubscribeEvent
    public void renderName(PlayerEvent.NameFormat event) {

        String name = event.getPlayer().getGameProfile().getName();
        String respected = "[Mod Creator] TBroski";
        String tbroski = "TBroski";
        System.out.println(name.equals(tbroski));
        if(name.equals(tbroski))
        {
            System.out.println("ChangedRespected");
            event.setDisplayname(respected);
        }
    }

    @SubscribeEvent
    public void livingDeath(LivingDeathEvent event) {
        PlayerEvents.checkPlayerDojutsuDeath(event);
        if (event.getEntity() instanceof PlayerEntity) {
            for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
                if (event.getEntity().getPersistentData().getBoolean(jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName())) {
                    if (jutsu.throwDeathEvent((PlayerEntity) event.getEntity())) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void livingDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        KakuzuEntity.cancelDamage(event);
        PlayerEvents.checkPlayerDojutsuDamage(event);
        for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
            if (source.getImmediateSource() instanceof PlayerEntity) {
                PlayerEntity attacker = (PlayerEntity) source.getImmediateSource();
                LivingEntity target = event.getEntityLiving();
                if (attacker.getPersistentData().getBoolean(jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName())) {
                    jutsu.throwAttackEvent(attacker, target);
                }
                if (target instanceof PlayerEntity && target.getPersistentData().getBoolean(jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName())) {
                    if (jutsu.throwDamageEvent(event.getAmount(), event.getSource(), (PlayerEntity) target)) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void itemRightClick(PlayerInteractEvent.RightClickItem event)
    {
        PlayerEvents.clayRightClick(event);
    }
    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        EntityEvents.entityTick(event);
    }


    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority= EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(EntityViewRenderEvent.FogDensity event)
    {
        List<AbstractBijuuEntity> bijuuEntities = new ArrayList<>();
        for (Entity entity : Minecraft.getInstance().world.getAllEntities()) {
            if (entity instanceof AbstractBijuuEntity) {
                bijuuEntities.add((AbstractBijuuEntity) entity);
            }
        }
        if (bijuuEntities.size() > 0) {
            event.setDensity(0.001F);
        }
        else if (Minecraft.getInstance().player.isPotionActive(EffectInit.EXHAUSTION.get())) {
            event.setDensity(0.01F);
        }
        else if (!Minecraft.getInstance().player.isPotionActive(Effects.BLINDNESS)){
            event.setDensity(0.0001F);
        }
        event.setCanceled(true);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(EntityViewRenderEvent.FogColors event)
    {
        List<AbstractBijuuEntity> bijuuEntities = new ArrayList<>();
        for (Entity entity : Minecraft.getInstance().world.getAllEntities()) {
            if (entity instanceof AbstractBijuuEntity) {
                bijuuEntities.add((AbstractBijuuEntity) entity);
            }
        }
        if (bijuuEntities.size() > 0) {
            Color color = bijuuEntities.get(0).getChakraColor().getChakraColor();
            event.setRed(color.getRed());
            event.setGreen(color.getGreen());
            event.setBlue(color.getBlue());
        }
        else if (Minecraft.getInstance().player.isPotionActive(EffectInit.EXHAUSTION.get())) {
            event.setRed(Color.BLACK.getRed());
            event.setBlue(Color.BLACK.getBlue());
            event.setGreen(Color.BLACK.getGreen());
        }
    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onPlayerRenderScreen(GuiContainerEvent.DrawBackground event)
    {
        if(curiosLoaded)
            return;

        ContainerScreen screen = event.getGuiContainer();
        if(screen instanceof InventoryScreen)
        {
            InventoryScreen inventoryScreen = (InventoryScreen) screen;
            int left = inventoryScreen.getGuiLeft();
            int top = inventoryScreen.getGuiTop();
            inventoryScreen.getMinecraft().getTextureManager().bindTexture(ContainerScreen.INVENTORY_BACKGROUND);
            Screen.blit(left + 76, top + 43, 18, 18, 76, 61, 18, 18, 256, 256);
        }
        else if(screen instanceof CreativeScreen)
        {
            CreativeScreen creativeScreen = (CreativeScreen) screen;
            if(creativeScreen.getSelectedTabIndex() == ItemGroup.INVENTORY.getIndex())
            {
                int left = creativeScreen.getGuiLeft();
                int top = creativeScreen.getGuiTop();
                creativeScreen.getMinecraft().getTextureManager().bindTexture(ContainerScreen.INVENTORY_BACKGROUND);
                Screen.blit(left + 126, top + 19, 18, 18, 76, 61, 18, 18, 256, 256);
            }
        }
    }


    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onTextureStitch(TextureStitchEvent.Pre event)
    {
        if(event.getMap().getTextureLocation().equals(AtlasTexture.LOCATION_BLOCKS_TEXTURE))
        {
            event.addSprite(EMPTY_BACK_SLOT);
        }
    }

    @SubscribeEvent
    public void onPlayerClone(PlayerEvent.Clone event)
    {
        if(curiosLoaded)
            return;

        PlayerEntity oldPlayer = event.getOriginal();
        if(oldPlayer.inventory instanceof ExtendedPlayerInventory && event.getPlayer().inventory instanceof ExtendedPlayerInventory)
        {
            ((ExtendedPlayerInventory) event.getPlayer().inventory).copyStone((ExtendedPlayerInventory) oldPlayer.inventory);
        }
    }



    public static void onPlayerInit(PlayerEntity player)
    {
        if(curiosLoaded)
            return;
        ForgeEventSubscriber.patchInventory(player);
    }

    private static void patchInventory(PlayerEntity player)
    {
        if(inventoryField == null)
        {
            inventoryField = getFieldAndSetAccessible(PlayerEntity.class, "field_71071_by");
        }
        if(containerField == null)
        {
            containerField = getFieldAndSetAccessible(PlayerEntity.class, "field_71069_bz");
        }
        try
        {
            ExtendedPlayerInventory inventory = new ExtendedPlayerInventory(player);
            inventoryField.set(player, inventory);

            ExtendedPlayerInventory.ExtendedPlayerContainer container = new ExtendedPlayerInventory.ExtendedPlayerContainer(inventory, !player.world.isRemote, player);
            containerField.set(player, container);
            player.openContainer = container;
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }


    private static Field getFieldAndSetAccessible(Class clazz, String obfName)
    {
        Field field = ObfuscationReflectionHelper.findField(clazz, obfName);
        field.setAccessible(true);

        try
        {
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
        }
        catch(IllegalAccessException | NoSuchFieldException e)
        {
            e.printStackTrace();
        }

        return field;
    }


    @OnlyIn(Dist.CLIENT)
    public static void patchCreativeSlots(CreativeScreen.CreativeContainer creativeContainer)
    {
        if(curiosLoaded)
            return;

        creativeContainer.inventorySlots.stream().filter(slot -> slot.inventory instanceof ExtendedPlayerInventory && slot.getSlotIndex() == 41).findFirst().ifPresent(slot ->
        {
            ForgeEventSubscriber.setSlotPosition(slot, 127, 20);
        });
    }


    public static int getCreativeSlotMax(ServerPlayerEntity player)
    {
        if(!curiosLoaded && player.inventory instanceof ExtendedPlayerInventory)
        {
            return 46;
        }
        return 45;
    }


    private static void setSlotPosition(Slot slot, int x, int y)
    {
        try
        {
            if(xPosField == null)
            {
                Field xPos = ObfuscationReflectionHelper.findField(Slot.class, "field_75223_e");
                xPos.setAccessible(true);
                Field xPosModifiers = Field.class.getDeclaredField("modifiers");
                xPosModifiers.setAccessible(true);
                xPosModifiers.setInt(xPos, xPos.getModifiers() & ~Modifier.FINAL);
                xPosField = xPos;
            }
            if(yPosField == null)
            {
                Field yPos = ObfuscationReflectionHelper.findField(Slot.class, "field_75221_f");
                yPos.setAccessible(true);
                Field yPosModifiers = Field.class.getDeclaredField("modifiers");
                yPosModifiers.setAccessible(true);
                yPosModifiers.setInt(yPos, yPos.getModifiers() & ~Modifier.FINAL);
                yPosField = yPos;
            }
            xPosField.set(slot, x);
            yPosField.set(slot, y);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onRegisterDimensions(final RegisterDimensionsEvent event) {
        if (DimensionType.byName(TSUKUYOMI_DIMENSION) == null) {
            DimensionManager.registerDimension(TSUKUYOMI_DIMENSION, DimensionInit.TSUKUYOMI.get(), null, true);
        }
    }
}