package com.benarutomod.tbroski.client.handler;

import com.benarutomod.tbroski.client.gui.container.ExtendedPlayerInventory;
import com.benarutomod.tbroski.client.renderer.mobs.FrogRenderer;
import com.benarutomod.tbroski.client.renderer.mobs.SnakeRenderer;
import com.benarutomod.tbroski.client.renderer.layers.BackLayer;
import com.benarutomod.tbroski.client.renderer.layers.ClayLayer;
import com.benarutomod.tbroski.client.renderer.layers.DojutsuLayer;
import com.benarutomod.tbroski.client.renderer.layers.ScrollLayer;
import com.benarutomod.tbroski.client.renderer.projectile.ExplosiveKunaiRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.KunaiRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.TeleportationKunaiRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.earth.FlyingStonesRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.earth.MudMoatRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.fire.FireBallRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.fire.PhoenixFlowerRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.lightning.LightningArrowRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.lightning.LightningBallRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.sharingan.AmaterasuJutsuRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.water.RagingWavesRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.water.WaterSharkBulletRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.water.WaterShurikenRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.wind.GalePalmRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.wind.WindArrowRenderer;
import com.benarutomod.tbroski.client.renderer.projectile.jutsu.wind.WindExplosionRenderer;
import com.benarutomod.tbroski.client.renderer.shinobi.*;
import com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.DeidaraRenderer;
import com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.ItachiRenderer;
import com.benarutomod.tbroski.client.renderer.shinobi.akatsuki.kakuzu.*;
import com.benarutomod.tbroski.client.renderer.shinobi.shinobi.*;
import com.benarutomod.tbroski.init.BlockInit;
import com.benarutomod.tbroski.init.EntityInit;

import com.benarutomod.tbroski.init.TileEntityInit;
import com.benarutomod.tbroski.tileentity.renderer.DojutsuSkullTileEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientHandler {

    public static void onsetup()
    {
        //Projectiles
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KUNAI.get(), new KunaiRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.EXPLOSIVE_KUNAI.get(), new ExplosiveKunaiRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.TELEPORTATION_KUNAI.get(), new TeleportationKunaiRenderer.Factory());

        //Entities
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GENIN.get(), GeninRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.CHUNIN.get(), ChuninRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.JONIN.get(), JoninRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.ANBU.get(), AnbuRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BASIC_SHARINGAN.get(), BasicSharinganRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BASIC_BYAKUGAN.get(), BasicByakuganRenderer::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.SNAKE.get(), SnakeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.FROG.get(), FrogRenderer::new);

        //Clones
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.BASIC_CLONE.get(), CloneRenderer::new);

        //Akatsuki
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.DEIDARA.get(), DeidaraRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.ITACHI.get(), ItachiRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.KAKUZU.get(), KakuzuRenderer::new);
        //Kakuzu's Dudes
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.LIGHTNING_STYLE_MASKED_ANIMAL.get(), LightningStyleMaskedAnimalRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.FIRE_STYLE_MASKED_ANIMAL.get(), FireStyleMaskedAnimalRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WIND_STYLE_MASKED_ANIMAL.get(), WindStyleMaskedAnimalRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WATER_STYLE_MASKED_ANIMAL.get(), WaterStyleMaskedAnimalRenderer::new);


        //Jutsu
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.FIREBALL_JUTSU.get(), new FireBallRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.LIGHTNING_BALL_JUTSU.get(), new LightningBallRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.GALE_PALM_JUTSU.get(), new GalePalmRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.FLYING_STONES_JUTSU.get(), new FlyingStonesRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WATER_SHURIKEN_JUTSU.get(), new WaterShurikenRenderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.PHOENIX_FLOWER_JUTSU.get(), new PhoenixFlowerRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WIND_EXPLOSION_JUTSU.get(), new WindExplosionRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.MUD_MOAT_JUTSU.get(), new MudMoatRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.RAGING_WAVES_JUTSU.get(), new RagingWavesRenderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.LIGHTNING_ARROW_JUTSU.get(), new LightningArrowRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WIND_ARROW_JUTSU.get(), new WindArrowRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(EntityInit.WATER_SHARK_BULLET_JUTSU.get(), new WaterSharkBulletRenderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(EntityInit.AMATERASU_JUTSU.get(), new AmaterasuJutsuRenderer.Factory());


        RenderTypeLookup.setRenderLayer(BlockInit.AMATERASU.get(), RenderType.getCutoutMipped());

        ClientRegistry.bindTileEntityRenderer(TileEntityInit.DOJUTSU_SKULL.get(), DojutsuSkullTileEntityRenderer::new);

        Map<String, PlayerRenderer> playerSkinMap = Minecraft.getInstance().getRenderManager().getSkinMap();
        ClientHandler.addPlayerLayers(playerSkinMap.get("default"));
        ClientHandler.addPlayerLayers(playerSkinMap.get("slim"));

        Map<EntityType<?>, EntityRenderer<?>> entityMap = Minecraft.getInstance().getRenderManager().renderers;
        for (Map.Entry<EntityType<?>, EntityRenderer<?>> renderer : entityMap.entrySet())
        {
            if (renderer.getValue() instanceof LivingRenderer) {
                ClientHandler.addEntityLayers((LivingRenderer) renderer.getValue());
            }
        }
    }

    public static void addPlayerLayers(PlayerRenderer renderer)
    {
        List<LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>> layers = ObfuscationReflectionHelper.getPrivateValue(LivingRenderer.class, renderer, "field_177097_h");
        if(layers != null)
        {
            layers.add(new BackLayer<>(renderer));
            layers.add(new ScrollLayer<>(renderer));
            layers.add(new DojutsuLayer<>(renderer));
        }
    }

    public static void setPlayerBackpack(int entityId, boolean wearing, String itemName)
    {
        Minecraft minecraft = Minecraft.getInstance();
        Item item = null;
        for (Item items : ForgeRegistries.ITEMS.getValues()) {
            if (items.getRegistryName().toString().equalsIgnoreCase(itemName)) {
                item = items;
            }
        }
        if(minecraft.world != null)
        {
            Entity entity = minecraft.world.getEntityByID(entityId);
            if(entity instanceof PlayerEntity)
            {
                PlayerEntity player = (PlayerEntity) entity;
                if(player.inventory instanceof ExtendedPlayerInventory)
                {
                    ((ExtendedPlayerInventory) player.inventory).getBackItems().set(0, wearing ? new ItemStack(item) : ItemStack.EMPTY);
                }
            }
        }
    }

    public static void addEntityLayers(LivingRenderer renderer)
    {
        List<LayerRenderer<LivingEntity, SegmentedModel<LivingEntity>>> layers = ObfuscationReflectionHelper.getPrivateValue(LivingRenderer.class, renderer, "field_177097_h");
        if(layers != null)
        {
            layers.add(new ClayLayer<>(renderer));
        }
    }

    private static double syncedFuel;
    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            System.err.println("Failed to load dependency entity for procedure SyncedFuelProcedure!");
            return;
        }
        Entity entity = (Entity) dependencies.get("entity");
        if (entity.world.isRemote) {
            entity.getPersistentData().putDouble("fuel", syncedFuel);
        }
        else {
            syncedFuel = entity.getPersistentData().getDouble("fuel");
        }
        System.out.println(entity.world.isRemote);
    }
}
