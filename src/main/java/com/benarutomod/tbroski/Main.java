package com.benarutomod.tbroski;

import com.benarutomod.tbroski.api.BeNMRegistry;
import com.benarutomod.tbroski.api.IBeNMPlugin;
import com.benarutomod.tbroski.api.internal.BeNMJutsu;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Mod(Main.MODID)
public class Main {

	public static final String MODID = "benarutomod";

	public Main() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		MinecraftForge.EVENT_BUS.register(new ChakraBar());
		MinecraftForge.EVENT_BUS.register(new Notifications());
		MinecraftForge.EVENT_BUS.register(new Amaterasu());
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		MinecraftForge.EVENT_BUS.register(new ForgeEventSubscriber());
		MinecraftForge.EVENT_BUS.register(new KeyboardHelper());
		MinecraftForge.EVENT_BUS.register(new TransformationJutsu());
		MinecraftForge.EVENT_BUS.register(new AmaterasuFireBlockBase());
		MinecraftForge.EVENT_BUS.register(new DojutsuEntityFinder());
		BeNMRegistry.registerPlugin(new MainPlugin());
		NetworkLoader.registerMessages();

		MinecraftForge.EVENT_BUS.register(this);

		KeybindInit.register();
		FluidInit.FLUIDS.register(modEventBus);
		EffectInit.EFFECT.register(modEventBus);
		EntityInit.ENTITIES.register(modEventBus);
		ItemInit.ITEMS.register(modEventBus);
		BlockInit.BLOCKS.register(modEventBus);
		FeatureInit.FEATURES.register(modEventBus);
		TileEntityInit.TILE_ENTITIES.register(modEventBus);
		BiomeInit.BIOMES.register(modEventBus);
		SoundInit.SOUNDS.register(modEventBus);
		DimensionInit.DIMENSIONS.register(modEventBus);

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC, "benarutomod-common.toml");
		modEventBus.addListener(this::onClientSetup);
		modEventBus.addListener(this::onCommonSetup);
	}

	private void onCommonSetup(FMLCommonSetupEvent e)
	{
		CapabilityManager.INSTANCE.register(IPlayerHandler.class, new PlayerCapability.Storage(), PlayerCapability::new);
		EntityInit.registerEntityWorldSpawns();
		FeatureInit.registerBiomeFeatures();

		for (IBeNMPlugin plugin : BeNMRegistry.PLUGINS) {
			System.out.println("BeNM Plugin, id: " + plugin.getPluginId() + ". Has been registered.");
			plugin.registerNewJutsu(BeNMRegistry.JUTSUS);
			plugin.registerNewDojutsus(BeNMRegistry.DOJUTSUS);
			plugin.registerNewClans(BeNMRegistry.CLANS);
			plugin.registerNewBodyModes(BeNMRegistry.BODY_MODES);
		}
		blackListJutsus();
		// API Search and Registry with help from ClassGraph libraries.
/*		ScanResult scanResult = new ClassGraph().enableClassInfo().enableAnnotationInfo().scan();
		ClassInfoList classInfoList = scanResult.getClassesWithAnnotation(BeNMPlugin.class.getName());
		for (Class markedClass : classInfoList.loadClasses()) {
			for (Class markedInterface : markedClass.getInterfaces()) {
				if (markedInterface == IBeNMPlugin.class) {
					System.out.println("BeNM Plugin found at: " + markedClass.getName());
					try {
						IBeNMPlugin plugin = (IBeNMPlugin) markedClass.newInstance();
						plugin.registerNewJutsu(BeNMRegistry.JUTSUS);
						plugin.registerNewDojutsus(BeNMRegistry.DOJUTSUS);
						plugin.registerNewClans(BeNMRegistry.CLANS);
						plugin.registerNewBodyModes(BeNMRegistry.BODY_MODES);
					} catch (InstantiationException instantiationException) {
						instantiationException.printStackTrace();
					} catch (IllegalAccessException illegalAccessException) {
						illegalAccessException.printStackTrace();
					}
				}
			}
		}*/
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


	public static class FilteredJutsu {

		private String id;
		private String registryName;
		private boolean blackListed;

		public FilteredJutsu(String id, String registryName, boolean blackListed) {
			this.id = id;
			this.registryName = registryName;
			this.blackListed = blackListed;
		}

		public boolean isBlackListed() {
			return blackListed;
		}

		public String getId() {
			return id;
		}

		public String getRegistryName() {
			return registryName;
		}
	}

	private static void blackListJutsus() {
		File file = new File("config/benarutomod-jutsu-blacklist.json");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		FileWriter writer = null;
		List<FilteredJutsu> filteredJutsuList = null;
		try {
			file.createNewFile();

			FileReader reader = new FileReader(file);
			filteredJutsuList = gson.fromJson(reader, new TypeToken<List<FilteredJutsu>>(){}.getType());

			writer = new FileWriter(file);
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		if (filteredJutsuList == null) {
			filteredJutsuList = new ArrayList<>();
			System.out.println("Filtered jutsu is null.");
		}

		List<BeNMJutsu> blacklistedValues = new ArrayList<>();
		List<FilteredJutsu> filteredJutsuToAdd = new ArrayList<>();
		for (BeNMJutsu jutsu : BeNMRegistry.JUTSUS.getValues()) {
			if (filteredJutsuList.size() == 0) {
				filteredJutsuToAdd.add(new FilteredJutsu(jutsu.getCorrelatedPlugin().getPluginId(), jutsu.getName(), false));
			}
			for (FilteredJutsu filteredJutsu : filteredJutsuList) {
				String jutsuName = jutsu.getCorrelatedPlugin().getPluginId() + "_" + jutsu.getName();
				String filteredJutsuName = filteredJutsu.getId() + "_" + filteredJutsu.getRegistryName();
				if (!jutsuName.equalsIgnoreCase(filteredJutsuName)) {
					filteredJutsuToAdd.add(new FilteredJutsu(jutsu.getCorrelatedPlugin().getPluginId(), jutsu.getName(), false));
				} else if (jutsuName.equalsIgnoreCase(filteredJutsuName) && filteredJutsu.isBlackListed()) {
					System.out.println("Blacklisted jutsu: " + jutsuName);
					blacklistedValues.add(jutsu);
				}
			}
		}
		for (BeNMJutsu jutsu : blacklistedValues) {
			BeNMRegistry.JUTSUS.getValues().remove(jutsu);
		}
		for (FilteredJutsu jutsu : filteredJutsuToAdd) {
			filteredJutsuList.add(jutsu);
		}

		String json = gson.toJson(filteredJutsuList);
		try {
			writer.write(json);
			writer.flush();
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
