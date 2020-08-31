package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.api.item.EtherealItem;
import com.benarutomod.tbroski.blocks.dojutsuskull.DojutsuSkullItemBase;
import com.benarutomod.tbroski.blocks.explosivepaper.ExplosivePaperItemBase;
import com.benarutomod.tbroski.blocks.teleportationpaper.TeleportationPaperItemBase;
import com.benarutomod.tbroski.items.armor.AkatsukiModel;
import com.benarutomod.tbroski.items.armor.AnbuModel;
import com.benarutomod.tbroski.items.armor.HeadbandModel;
import com.benarutomod.tbroski.items.disc.NarutoMusicDisc;
import com.benarutomod.tbroski.items.materials.BookItemBase;
import com.benarutomod.tbroski.items.food.DilutedRationsPillItemBase;
import com.benarutomod.tbroski.items.food.RationsPillItemBase;
import com.benarutomod.tbroski.items.food.SpikedRationsPillItemBase;
import com.benarutomod.tbroski.items.nbt.ClaySpawnEggItemBase;
import com.benarutomod.tbroski.items.projectile.ExplosiveKunaiItemBase;
import com.benarutomod.tbroski.items.projectile.KunaiItemBase;
import com.benarutomod.tbroski.items.projectile.TeleportationKunaiItemBase;
import com.benarutomod.tbroski.items.materials.ModdedSpawnEggItem;
import com.benarutomod.tbroski.items.materials.RiceItemBase;
import com.benarutomod.tbroski.items.materials.UniqueItemBase;
import com.benarutomod.tbroski.items.nbt.ScrollItemBase;
import com.benarutomod.tbroski.items.tools.BeNMSwordItemBase;
import com.benarutomod.tbroski.util.enums.AkatsukiArmorMaterial;
import com.benarutomod.tbroski.util.enums.AnbuArmorMaterial;
import com.benarutomod.tbroski.util.enums.BeNMItemTier;
import com.benarutomod.tbroski.util.enums.HeadbandArmorMaterial;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

import static net.minecraft.item.Items.BUCKET;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Main.MODID);

    //ArmorItems
    public static final RegistryObject<ArmorItem> HEADBAND_HELMET = ITEMS.register("headband_helmet", () -> new HeadbandModel(HeadbandArmorMaterial.HEADBAND, EquipmentSlotType.HEAD, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ArmorItem> ANBU_HELMET = ITEMS.register("anbu_helmet", () -> new AnbuModel(AnbuArmorMaterial.ANBU, EquipmentSlotType.HEAD, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ArmorItem> ANBU_CHESTPLATE = ITEMS.register("anbu_chestplate", () -> new AnbuModel(AnbuArmorMaterial.ANBU, EquipmentSlotType.CHEST, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ArmorItem> AKATSUKI_CHESTPLATE = ITEMS.register("akatsuki_chestplate", () -> new AkatsukiModel(AkatsukiArmorMaterial.AKATSUKI, EquipmentSlotType.CHEST, new Item.Properties().group(Main.TAB)));


    //MaterialItems
    public static final RegistryObject<Item> GUIDE_BOOK = ITEMS.register("guide_book", BookItemBase::new);
    public static final RegistryObject<Item> RICE = ITEMS.register("rice", RiceItemBase::new);


    //Items
    public static final RegistryObject<Item> KUNAI = ITEMS.register("kunai", KunaiItemBase::new);
    public static final RegistryObject<Item> EXPLOSIVE_KUNAI = ITEMS.register("explosive_kunai", ExplosiveKunaiItemBase::new);
    public static final RegistryObject<Item> TELEPORTATION_KUNAI = ITEMS.register("teleportation_kunai", TeleportationKunaiItemBase::new);

    public static final RegistryObject<Item> KATANA = ITEMS.register("katana", () -> new BeNMSwordItemBase(BeNMItemTier.CHAKRA_IRON, 3, -2.0F, (new Item.Properties()).group(Main.TAB)));
    //Ethereal
    public static final RegistryObject<EtherealItem> TOTSUKA_BlADE = ITEMS.register("totsuka_blade", () -> new EtherealItem((new Item.Properties()).group(Main.TAB), new EtherealItem.EtherealProperties().setDamage(3F).setRange(1.5F)).setRenderFire(EntityType.ITEM.getWidth() * 2, EntityType.ITEM.getHeight() * 10, Color.YELLOW));
    public static final RegistryObject<EtherealItem> YATA_MIRROR = ITEMS.register("yata_mirror", () -> new EtherealItem((new Item.Properties()).group(Main.TAB), new EtherealItem.EtherealProperties().setArmor(4F).setCancelsNinjutsu()).setRenderFire(EntityType.ITEM.getWidth() * 5, EntityType.ITEM.getHeight() * 5, Color.RED));

    //SoundItems
    public static final RegistryObject<Item> ITACHI_MUSIC_DISC = ITEMS.register("naruto_itachi_disc", () -> new NarutoMusicDisc(1, SoundInit.ITACHI_DISC_LAZY.get(), new Item.Properties().maxStackSize(1).group(Main.TAB)));
    public static final RegistryObject<Item> PAIN_MUSIC_DISC = ITEMS.register("naruto_pain_disc", () -> new NarutoMusicDisc(1, SoundInit.PAIN_DISC_LAZY.get(), new Item.Properties().maxStackSize(1).group(Main.TAB)));


    //BlockItems
    public static final RegistryObject<Item> EXPLOSIVE_PAPER_ITEM = ITEMS.register("explosive_paper", ()-> new ExplosivePaperItemBase(BlockInit.EXPLOSIVE_PAPER.get()));
    public static final RegistryObject<Item> TELEPORTATION_PAPER_ITEM = ITEMS.register("teleportation_paper", ()-> new TeleportationPaperItemBase(BlockInit.TELEPORTATION_PAPER.get()));

    public static final RegistryObject<Item> DOJUTSU_SKULL_ITEM = ITEMS.register("dojutsu_skull", ()-> new DojutsuSkullItemBase(BlockInit.DOJUTSU_SKULL.get()));

    public static final RegistryObject<Item> SAKURA_PLANKS_ITEM = ITEMS.register("sakura_planks", ()-> new BlockItem(BlockInit.SAKURA_PLANKS.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_SAPLING_ITEM = ITEMS.register("sakura_sapling", ()-> new BlockItem(BlockInit.SAKURA_SAPLING.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_LOG_ITEM = ITEMS.register("sakura_log", ()-> new BlockItem(BlockInit.SAKURA_LOG.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> STRIPPED_SAKURA_LOG_ITEM = ITEMS.register("stripped_sakura_log", ()-> new BlockItem(BlockInit.STRIPPED_SAKURA_LOG.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> STRIPPED_SAKURA_WOOD_ITEM = ITEMS.register("stripped_sakura_wood", ()-> new BlockItem(BlockInit.STRIPPED_SAKURA_WOOD.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_WOOD_ITEM = ITEMS.register("sakura_wood", ()-> new BlockItem(BlockInit.SAKURA_WOOD.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_LEAVES_ITEM = ITEMS.register("sakura_leaves", ()-> new BlockItem(BlockInit.SAKURA_LEAVES.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_SIGN_ITEM = ITEMS.register("sakura_sign", ()-> new SignItem(new Item.Properties().maxStackSize(16).group(Main.TAB), BlockInit.SAKURA_SIGN.get(), BlockInit.SAKURA_WALL_SIGN.get()));
    public static final RegistryObject<Item> SAKURA_DOOR_ITEM = ITEMS.register("sakura_door", ()-> new TallBlockItem(BlockInit.SAKURA_DOOR.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_SLAB_ITEM = ITEMS.register("sakura_slab", ()-> new BlockItem(BlockInit.SAKURA_SLAB.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_STAIRS_ITEM = ITEMS.register("sakura_stairs", ()-> new BlockItem(BlockInit.SAKURA_STAIRS.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_FENCE_ITEM = ITEMS.register("sakura_fence", ()-> new BlockItem(BlockInit.SAKURA_FENCE.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_FENCE_GATE_ITEM = ITEMS.register("sakura_fence_gate", ()-> new BlockItem(BlockInit.SAKURA_FENCE_GATE.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_BUTTON_ITEM = ITEMS.register("sakura_button", ()-> new BlockItem(BlockInit.SAKURA_BUTTON.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> SAKURA_PRESSURE_PLATE_ITEM = ITEMS.register("sakura_pressure_plate", ()-> new BlockItem(BlockInit.SAKURA_PRESSURE_PLATE.get(), new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<Item> LIGNITE_ITEM = ITEMS.register("lignite", ()-> new BlockItem(BlockInit.LIGNITE.get(), new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<Item> IRON_SAND_ITEM = ITEMS.register("iron_sab", ()-> new BlockItem(BlockInit.IRON_SAND.get(), new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<Item> TOAD_OIL_BUCKET_ITEM = ITEMS.register("toad_oil_bucket", ()-> new BucketItem(FluidInit.TOAD_OIL, (new Item.Properties()).containerItem(BUCKET).maxStackSize(1).group(Main.TAB)));


    //FoodItems
    public static final RegistryObject<Item> RATIONS_PILL_ITEM = ITEMS.register("rations_pill", RationsPillItemBase::new);
    public static final RegistryObject<Item> DILUTED_RATIONS_PILL_ITEM = ITEMS.register("diluted_rations_pill", DilutedRationsPillItemBase::new);
    public static final RegistryObject<Item> SPIKED_RATIONS_PILL_ITEM = ITEMS.register("spiked_rations_pill", SpikedRationsPillItemBase::new);


    //SpawnItems
    public static final RegistryObject<ModdedSpawnEggItem> GENIN_SPAWN_EGG = ITEMS.register("genin_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.GENIN, 0xeac086, 0x52C6C6, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> CHUNIN_SPAWN_EGG = ITEMS.register("chunin_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.CHUNIN, 0xeac086, 0x00FF00, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> JONIN_SPAWN_EGG = ITEMS.register("jonin_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.JONIN, 0xeac086, 0x9400D3, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> ANBU_SPAWN_EGG = ITEMS.register("anbu_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.ANBU, 0xeac086, 0x8B0000, new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<ModdedSpawnEggItem> BASIC_SHARINGAN_SPAWN_EGG = ITEMS.register("basic_sharingan_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.BASIC_SHARINGAN, 0xD70303, 0x52C6C6, new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<ModdedSpawnEggItem> BASIC_BYAKUGAN_SPAWN_EGG = ITEMS.register("basic_byakugan_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.BASIC_BYAKUGAN, 0xE7E1F1, 0x52C6C6, new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<ModdedSpawnEggItem> DEIDARA_SPAWN_EGG = ITEMS.register("deidara_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.DEIDARA, 0x000000, 0xD70303, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> ITACHI_SPAWN_EGG = ITEMS.register("itachi_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.ITACHI, 0x000000, 0xD70303, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> KAKUZU_SPAWN_EGG = ITEMS.register("kakuzu_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.KAKUZU, 0x000000, 0xD70303, new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<ModdedSpawnEggItem> SNAKE_SPAWN_EGG = ITEMS.register("snake_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.SNAKE, 0x3ACC27, 0x248C04, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> FROG_SPAWN_EGG = ITEMS.register("frog_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.FROG, 0x3ACC27, 0xFFA500, new Item.Properties().group(Main.TAB)));

    public static final RegistryObject<ModdedSpawnEggItem> SHUKAKU_SPAWN_EGG = ITEMS.register("shukaku_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.SHUKAKU, 0x7F9993, 0xA79275, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> MATATABI_SPAWN_EGG = ITEMS.register("matatabi_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.MATATABI, 0x7F9993, 0x4A70CB, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> ISOBU_SPAWN_EGG = ITEMS.register("isobu_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.ISOBU, 0x7F9993, 0x9DA1AA, new Item.Properties().group(Main.TAB)));
    public static final RegistryObject<ModdedSpawnEggItem> SON_GOKU_SPAWN_EGG = ITEMS.register("son_goku_spawn_egg", ()-> new ModdedSpawnEggItem(EntityInit.SON_GOKU, 0x7F9993, 0x9D0206, new Item.Properties().group(Main.TAB)));


    //NBTItems
    public static final RegistryObject<Item> SCROLL = ITEMS.register("summoning_scroll", ScrollItemBase::new);

    public static final RegistryObject<Item> CLAY_SPAWN_EGG = ITEMS.register("clay_spawn_egg", ClaySpawnEggItemBase::new);


    //SymbolItems
    public static final RegistryObject<Item> CHAKRA_SYMBOL = ITEMS.register("chakra_symbol", UniqueItemBase::new);
    public static final RegistryObject<Item> WATER_SYMBOL = ITEMS.register("water_symbol", UniqueItemBase::new);
    public static final RegistryObject<Item> FIRE_SYMBOL = ITEMS.register("fire_symbol", UniqueItemBase::new);
    public static final RegistryObject<Item> EARTH_SYMBOL = ITEMS.register("earth_symbol", UniqueItemBase::new);
    public static final RegistryObject<Item> LIGHTNING_SYMBOL = ITEMS.register("lightning_symbol", UniqueItemBase::new);
    public static final RegistryObject<Item> WIND_SYMBOL = ITEMS.register("wind_symbol", UniqueItemBase::new);

    public static final RegistryObject<Item> FANGS_SYMBOL = ITEMS.register("fangs_symbol", UniqueItemBase::new);


    //Jutsu but not really
    public static final RegistryObject<Item> MINI_ROCKET = ITEMS.register("mini_rocket", UniqueItemBase::new);
    public static final RegistryObject<Item> TAILED_BEAST_BOMB = ITEMS.register("tailed_beast_bomb", UniqueItemBase::new);
    //Jutsu
    public static final RegistryObject<Item> FIREBALL_JUTSU = ITEMS.register("fireball_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> PHOENIX_FLOWER_JUTSU = ITEMS.register("phoenix_flower_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> LIGHTNING_BALL_JUTSU = ITEMS.register("lightning_ball_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> LIGHTNING_ARROW_JUTSU = ITEMS.register("lightning_arrow_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> GALE_PALM_JUTSU = ITEMS.register("gale_palm_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> WIND_EXPLOSION_JUTSU = ITEMS.register("wind_explosion_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> WIND_ARROW_JUTSU = ITEMS.register("wind_arrow_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> FLYING_STONES_JUTSU = ITEMS.register("flying_stones_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> MUD_MOAT_JUTSU = ITEMS.register("mud_moat_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> WATER_SHURIKEN_JUTSU = ITEMS.register("water_shuriken_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> RAGING_WAVES_JUTSU = ITEMS.register("raging_waves_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> WATER_SHARK_BULLET_JUTSU = ITEMS.register("water_shark_bullet_jutsu", UniqueItemBase::new);
    //Kekkai Genkai
    public static final RegistryObject<Item> BLACK_IRON_FIST_JUTSU = ITEMS.register("black_iron_fist_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> SCORCHING_STREAM_ROCK_JUTSU = ITEMS.register("scorching_stream_rock_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> LAVA_SHURIKEN_JUTSU = ITEMS.register("lava_shuriken_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> BURNING_AQUA_GUN_JUTSU = ITEMS.register("burning_aqua_gun_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> CORROSIVE_ARROW_JUTSU = ITEMS.register("corrosive_arrow_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> ONE_HORNED_WHITE_WHALE_JUTSU = ITEMS.register("one_horned_white_whale_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> TEN_THOUSAND_ICE_PETALS_JUTSU = ITEMS.register("ten_thousand_ice_petals_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> BLACK_HUNTING_JUTSU = ITEMS.register("black_hunting_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> LASER_CIRCUS_JUTSU = ITEMS.register("laser_circus_jutsu", UniqueItemBase::new);

    public static final RegistryObject<Item> EXTREAMLY_STEAMING_MURDER_JUTSU = ITEMS.register("extreamly_steaming_murder_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> BLAZING_PELLETS_JUTSU = ITEMS.register("blazing_pellets_jutsu", UniqueItemBase::new);
    public static final RegistryObject<Item> GREAT_FIREBALL_JUTSU = ITEMS.register("great_fireball_jutsu", UniqueItemBase::new);
}