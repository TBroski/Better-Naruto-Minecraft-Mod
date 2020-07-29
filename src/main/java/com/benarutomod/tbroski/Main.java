package com.benarutomod.tbroski;

import com.benarutomod.tbroski.integration.Curios;
import com.benarutomod.tbroski.blocks.AmaterasuFireBlockBase;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.container.ExtendedPlayerInventory;
import com.benarutomod.tbroski.client.renderer.blocks.Amaterasu;
import com.benarutomod.tbroski.client.gui.overlay.ChakraBar;
import com.benarutomod.tbroski.client.gui.overlay.DojutsuEntityFinder;
import com.benarutomod.tbroski.client.gui.overlay.Notifications;
import com.benarutomod.tbroski.client.handler.ClientHandler;
import com.benarutomod.tbroski.common.jutsu.TransformationJutsu;
import com.benarutomod.tbroski.event.ForgeEventSubscriber;
import com.benarutomod.tbroski.init.*;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.capabilities.CapabilityHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;

import com.benarutomod.tbroski.util.helpers.KeyboardHelper;
import com.google.common.collect.ImmutableSet;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LiquidsConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.concurrent.atomic.AtomicReference;

@Mod(Main.MODID)
public class Main {

	public static final String MODID = "benarutomod";

	public Main()
	{
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(new ChakraBar());
		MinecraftForge.EVENT_BUS.register(new Notifications());
		MinecraftForge.EVENT_BUS.register(new Amaterasu());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new ForgeEventSubscriber());
		MinecraftForge.EVENT_BUS.register(new NetworkLoader());
		MinecraftForge.EVENT_BUS.register(new KeyboardHelper());
		MinecraftForge.EVENT_BUS.register(new TransformationJutsu());
		MinecraftForge.EVENT_BUS.register(new AmaterasuFireBlockBase());
		MinecraftForge.EVENT_BUS.register(new DojutsuEntityFinder());
		NetworkLoader.registerMessages();

		MinecraftForge.EVENT_BUS.register(this);


		KeybindInit.register();
		ClanInit.register();
		DojutsuInit.register();
		FluidInit.FLUIDS.register(modEventBus);
		EffectInit.EFFECT.register(modEventBus);
		EntityInit.ENTITIES.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		FeatureInit.FEATURES.register(modEventBus);
		TileEntityInit.TILE_ENTITIES.register(modEventBus);
		BiomeInit.BIOMES.register(modEventBus);
		SoundInit.SOUNDS.register(modEventBus);

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_SPEC, "benarutomod-server.toml");
		modEventBus.addListener(this::onClientSetup);
		modEventBus.addListener(this::onCommonSetup);
	}

	private void onCommonSetup(FMLCommonSetupEvent e)
	{
		CapabilityManager.INSTANCE.register(IPlayerHandler.class, new PlayerCapability.Storage(), PlayerCapability::new);
		EntityInit.registerEntityWorldSpawns();
		FeatureInit.registerBiomeFeatures();
	}

	private void onClientSetup(FMLClientSetupEvent e)
	{
		ClientHandler.onsetup();
	}

	public static final ItemGroup TAB = new ItemGroup("benarutoTab")
	{
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(ItemInit.KUNAI.get());
		}
	};

	public static ItemStack getBackpackStack(PlayerEntity player)
	{
		AtomicReference<ItemStack> back = new AtomicReference<>(ItemStack.EMPTY);
		if(ForgeEventSubscriber.isCuriosLoaded())
		{
			back.set(Curios.getBackStack(player));
		}
		if(player.inventory instanceof ExtendedPlayerInventory)
		{
			ExtendedPlayerInventory inventory = (ExtendedPlayerInventory) player.inventory;
			back.set(inventory.getBackItems().get(0));
		}
		return back.get();
	}
}