package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.entity.ClayEntity;
import com.benarutomod.tbroski.entity.EtherealItemEntity;
import com.benarutomod.tbroski.entity.clones.WoodCloneEntity;
import com.benarutomod.tbroski.entity.mobs.FrogEntity;
import com.benarutomod.tbroski.entity.mobs.SnakeEntity;
import com.benarutomod.tbroski.entity.clones.BasicCloneEntity;
import com.benarutomod.tbroski.entity.mobs.bijuu.IsobuEntity;
import com.benarutomod.tbroski.entity.mobs.bijuu.MatatabiEntity;
import com.benarutomod.tbroski.entity.mobs.bijuu.ShukakuEntity;
import com.benarutomod.tbroski.entity.mobs.bijuu.SonGokuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.TailedBeastBombEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.boil.BurningAquaGunEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.boil.CorrosiveArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.FlyingStonesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.earth.MudMoatEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.FireballEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.fire.PhoenixFlowerEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lava.LavaShurikenEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lava.ScorchingStreamRockEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.lightning.LightningBallEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.magnet.BlackIronFistEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.sharingan.AmaterasuJutsuEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.sixpath.MiniRocketProjectileEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.RagingWavesEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterSharkBulletEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.water.WaterShurikenEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.GalePalmEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindArrowEntity;
import com.benarutomod.tbroski.entity.projectile.jutsu.wind.WindExplosionEntity;
import com.benarutomod.tbroski.entity.projectile.ExplosiveKunaiEntity;
import com.benarutomod.tbroski.entity.projectile.KunaiEntity;
import com.benarutomod.tbroski.entity.projectile.TeleportationKunaiEntity;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.DeidaraEntity;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.ItachiEntity;
import com.benarutomod.tbroski.entity.shinobi.akatsuki.kakuzu.*;
import com.benarutomod.tbroski.entity.shinobi.shinobi.*;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, Main.MODID);

    //Projectiles
    public static final RegistryObject<EntityType<KunaiEntity>> KUNAI = ENTITIES.register("kunai", () -> EntityType.Builder.<KunaiEntity>create(KunaiEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + "kunai"));
    public static final RegistryObject<EntityType<ExplosiveKunaiEntity>> EXPLOSIVE_KUNAI = ENTITIES.register("explosive_kunai", () -> EntityType.Builder.<ExplosiveKunaiEntity>create(ExplosiveKunaiEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + "explosive_kunai"));
    public static final RegistryObject<EntityType<TeleportationKunaiEntity>> TELEPORTATION_KUNAI = ENTITIES.register("teleportation_kunai", () -> EntityType.Builder.<TeleportationKunaiEntity>create(TeleportationKunaiEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + "teleportation_kunai"));


    //Entities
    public static final RegistryObject<EntityType<GeninEntity>> GENIN = ENTITIES.register("genin", () -> EntityType.Builder.<GeninEntity>create(GeninEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "genin").toString()));
    public static final RegistryObject<EntityType<ChuninEntity>> CHUNIN = ENTITIES.register("chunin", () -> EntityType.Builder.<ChuninEntity>create(ChuninEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "chunin").toString()));
    public static final RegistryObject<EntityType<JoninEntity>> JONIN = ENTITIES.register("jonin", () -> EntityType.Builder.<JoninEntity>create(JoninEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "jonin").toString()));
    public static final RegistryObject<EntityType<AnbuEntity>> ANBU = ENTITIES.register("anbu", () -> EntityType.Builder.<AnbuEntity>create(AnbuEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "anbu").toString()));
    public static final RegistryObject<EntityType<BasicSharinganEntity>> BASIC_SHARINGAN = ENTITIES.register("basic_sharingan", () -> EntityType.Builder.<BasicSharinganEntity>create(BasicSharinganEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "basic_sharingan").toString()));
    public static final RegistryObject<EntityType<BasicByakuganEntity>> BASIC_BYAKUGAN = ENTITIES.register("basic_byakugan", () -> EntityType.Builder.<BasicByakuganEntity>create(BasicByakuganEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "basic_byakugan").toString()));
    public static final RegistryObject<EntityType<BrotherSharinganEntity>> BROTHER_SHARINGAN = ENTITIES.register("brother_sharingan", () -> EntityType.Builder.<BrotherSharinganEntity>create(BrotherSharinganEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "brother_sharingan").toString()));

    public static final RegistryObject<EntityType<BasicCloneEntity>> BASIC_CLONE = ENTITIES.register("basic_clone", () -> EntityType.Builder.<BasicCloneEntity>create(BasicCloneEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "basic_clone").toString()));
    public static final RegistryObject<EntityType<WoodCloneEntity>> WOOD_CLONE = ENTITIES.register("wood_clone", () -> EntityType.Builder.<WoodCloneEntity>create(WoodCloneEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "wood_clone").toString()));

    public static final RegistryObject<EntityType<DeidaraEntity>> DEIDARA = ENTITIES.register("deidara", () -> EntityType.Builder.<DeidaraEntity>create(DeidaraEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "deidara").toString()));
    public static final RegistryObject<EntityType<ItachiEntity>> ITACHI = ENTITIES.register("itachi", () -> EntityType.Builder.<ItachiEntity>create(ItachiEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "itachi").toString()));
    public static final RegistryObject<EntityType<KakuzuEntity>> KAKUZU = ENTITIES.register("kakuzu", () -> EntityType.Builder.<KakuzuEntity>create(KakuzuEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "kakuzu").toString()));

    public static final RegistryObject<EntityType<SnakeEntity>> SNAKE = ENTITIES.register("snake", () -> EntityType.Builder.<SnakeEntity>create(SnakeEntity::new, EntityClassification.CREATURE).size(1.0F, 1.0F).build(new ResourceLocation(Main.MODID, "snake").toString()));
    public static final RegistryObject<EntityType<FrogEntity>> FROG = ENTITIES.register("frog", () -> EntityType.Builder.<FrogEntity>create(FrogEntity::new, EntityClassification.CREATURE).size(EntityType.RABBIT.getWidth(), EntityType.RABBIT.getHeight()).build(new ResourceLocation(Main.MODID, "frog").toString()));

    //Items
    public static RegistryObject<EntityType<EtherealItemEntity>> ETHEREAL_ITEM = ENTITIES.register("ethereal_item", () -> EntityType.Builder.<EtherealItemEntity>create(EtherealItemEntity::new, EntityClassification.MISC).size(EntityType.ITEM.getWidth(), EntityType.ITEM.getHeight()).build(new ResourceLocation(Main.MODID, "ethereal_item").toString()));
    //Bijuu
    public static final RegistryObject<EntityType<ShukakuEntity>> SHUKAKU = ENTITIES.register("shukaku", () -> EntityType.Builder.<ShukakuEntity>create(ShukakuEntity::new, EntityClassification.CREATURE).size(3F, 4F).build(new ResourceLocation(Main.MODID, "shukaku").toString()));
    public static final RegistryObject<EntityType<MatatabiEntity>> MATATABI = ENTITIES.register("matatabi", () -> EntityType.Builder.<MatatabiEntity>create(MatatabiEntity::new, EntityClassification.CREATURE).size(3F, 4F).build(new ResourceLocation(Main.MODID, "matatabi").toString()));
    public static final RegistryObject<EntityType<IsobuEntity>> ISOBU = ENTITIES.register("isobu", () -> EntityType.Builder.<IsobuEntity>create(IsobuEntity::new, EntityClassification.CREATURE).size(3F, 4F).build(new ResourceLocation(Main.MODID, "isobu").toString()));
    public static final RegistryObject<EntityType<SonGokuEntity>> SON_GOKU = ENTITIES.register("son_goku", () -> EntityType.Builder.<SonGokuEntity>create(SonGokuEntity::new, EntityClassification.CREATURE).size(3F, 4F).build(new ResourceLocation(Main.MODID, "son_goku").toString()));

    //Kakuzu's Goons
    public static final RegistryObject<EntityType<LightningStyleMaskedAnimalEntity>> LIGHTNING_STYLE_MASKED_ANIMAL = ENTITIES.register("lightning_style_masked_animal", () -> EntityType.Builder.<LightningStyleMaskedAnimalEntity>create(LightningStyleMaskedAnimalEntity::new, EntityClassification.CREATURE).size(1.5F, 2.5F).build(new ResourceLocation(Main.MODID, "lightning_style_masked_animal").toString()));
    public static final RegistryObject<EntityType<FireStyleMaskedAnimalEntity>> FIRE_STYLE_MASKED_ANIMAL = ENTITIES.register("fire_style_masked_animal", () -> EntityType.Builder.<FireStyleMaskedAnimalEntity>create(FireStyleMaskedAnimalEntity::new, EntityClassification.CREATURE).size(1.5F, 2F).build(new ResourceLocation(Main.MODID, "fire_style_masked_animal").toString()));
    public static final RegistryObject<EntityType<WindStyleMaskedAnimalEntity>> WIND_STYLE_MASKED_ANIMAL = ENTITIES.register("wind_style_masked_animal", () -> EntityType.Builder.<WindStyleMaskedAnimalEntity>create(WindStyleMaskedAnimalEntity::new, EntityClassification.CREATURE).size(1.5F, 2F).build(new ResourceLocation(Main.MODID, "wind_style_masked_animal").toString()));
    public static final RegistryObject<EntityType<WaterStyleMaskedAnimalEntity>> WATER_STYLE_MASKED_ANIMAL = ENTITIES.register("water_style_masked_animal", () -> EntityType.Builder.<WaterStyleMaskedAnimalEntity>create(WaterStyleMaskedAnimalEntity::new, EntityClassification.CREATURE).size(1.7F, EntityType.SHEEP.getHeight() + 0.3F).build(new ResourceLocation(Main.MODID, "water_style_masked_animal").toString()));
    //Camera
    public static final RegistryObject<EntityType<ClayEntity>> CLAY_ENTITY = ENTITIES.register("clay_entity", () -> EntityType.Builder.<ClayEntity>create(ClayEntity::new, EntityClassification.CREATURE).size(EntityType.PLAYER.getWidth(), EntityType.PLAYER.getHeight()).build(new ResourceLocation(Main.MODID, "clay_entity").toString()));



    public static void registerEntityWorldSpawns()
    {
        registerEntityWorldSpawn(GENIN.get(), 12, 1, 3);
        registerEntityWorldSpawn(CHUNIN.get(), 8, 1, 3);
        registerEntityWorldSpawn(JONIN.get(), 5, 1, 3);
        registerEntityWorldSpawn(ANBU.get(), 3, 3, 5);
        registerEntityWorldSpawn(BASIC_SHARINGAN.get(), 1, 1, 1);
        registerEntityWorldSpawn(BASIC_BYAKUGAN.get(), 1, 1, 1);
        registerEntityLocalSpawn(SNAKE.get(), 40, 2, 4, Biomes.SWAMP, Biomes.SWAMP_HILLS);
        registerEntityLocalSpawn(FROG.get(), 100, 3, 8, BiomeInit.MOUNT_MYOBOKU_BIOME.get());
    }


    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroupCountIn, int maxGroupCountIn)
    {
        for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
            biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
        }
    }

    public static void registerEntityLocalSpawn(EntityType<?> entity, int weight, int minGroupCountIn, int maxGroupCountIn, Biome... biomes)
    {
        for (Biome biome : biomes) {
            biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
        }
    }

    //Jutsu but not really
    public static final RegistryObject<EntityType<MiniRocketProjectileEntity>> MINI_ROCKET = ENTITIES.register("mini_rocket", () -> EntityType.Builder.<MiniRocketProjectileEntity>create(MiniRocketProjectileEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":mini_rocket"));
    public static final RegistryObject<EntityType<TailedBeastBombEntity>> TAILED_BEAST_BOMB = ENTITIES.register("tailed_beast_bomb", () -> EntityType.Builder.<TailedBeastBombEntity>create(TailedBeastBombEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":tailed_beast_bomb"));
    //Jutsu
    public static final RegistryObject<EntityType<FireballEntity>> FIREBALL_JUTSU = ENTITIES.register("fireball_jutsu", () -> EntityType.Builder.<FireballEntity>create(FireballEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":fireball_jutsu"));
    public static final RegistryObject<EntityType<PhoenixFlowerEntity>> PHOENIX_FLOWER_JUTSU = ENTITIES.register("phoenix_flower_jutsu", () -> EntityType.Builder.<PhoenixFlowerEntity>create(PhoenixFlowerEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":phoenix_flower_jutsu"));

    public static final RegistryObject<EntityType<LightningBallEntity>> LIGHTNING_BALL_JUTSU = ENTITIES.register("lightning_ball_jutsu", () -> EntityType.Builder.<LightningBallEntity>create(LightningBallEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":lightning_ball_jutsu"));
    public static final RegistryObject<EntityType<LightningArrowEntity>> LIGHTNING_ARROW_JUTSU = ENTITIES.register("lightning_arrow_jutsu", () -> EntityType.Builder.<LightningArrowEntity>create(LightningArrowEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":lightning_arrow_jutsu"));

    public static final RegistryObject<EntityType<GalePalmEntity>> GALE_PALM_JUTSU = ENTITIES.register("gale_palm_jutsu", () -> EntityType.Builder.<GalePalmEntity>create(GalePalmEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":gale_palm_jutsu"));
    public static final RegistryObject<EntityType<WindExplosionEntity>> WIND_EXPLOSION_JUTSU = ENTITIES.register("wind_explosion_jutsu", () -> EntityType.Builder.<WindExplosionEntity>create(WindExplosionEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":wind_explosion_jutsu"));
    public static final RegistryObject<EntityType<WindArrowEntity>> WIND_ARROW_JUTSU = ENTITIES.register("wind_arrow_jutsu", () -> EntityType.Builder.<WindArrowEntity>create(WindArrowEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":wind_arrow_jutsu"));

    public static final RegistryObject<EntityType<FlyingStonesEntity>> FLYING_STONES_JUTSU = ENTITIES.register("flying_stones_jutsu", () -> EntityType.Builder.<FlyingStonesEntity>create(FlyingStonesEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":flying_stones_jutsu"));
    public static final RegistryObject<EntityType<MudMoatEntity>> MUD_MOAT_JUTSU = ENTITIES.register("mud_moat_jutsu", () -> EntityType.Builder.<MudMoatEntity>create(MudMoatEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":mud_moat_jutsu"));

    public static final RegistryObject<EntityType<WaterShurikenEntity>> WATER_SHURIKEN_JUTSU = ENTITIES.register("water_shuriken_jutsu", () -> EntityType.Builder.<WaterShurikenEntity>create(WaterShurikenEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":water_shuriken_jutsu"));
    public static final RegistryObject<EntityType<RagingWavesEntity>> RAGING_WAVES_JUTSU = ENTITIES.register("raging_waves_jutsu", () -> EntityType.Builder.<RagingWavesEntity>create(RagingWavesEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":raging_waves_jutsu"));
    public static final RegistryObject<EntityType<WaterSharkBulletEntity>> WATER_SHARK_BULLET_JUTSU = ENTITIES.register("water_shark_bullet_jutsu", () -> EntityType.Builder.<WaterSharkBulletEntity>create(WaterSharkBulletEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":water_shark_bullet_jutsu"));

    public static final RegistryObject<EntityType<BlackIronFistEntity>> BLACK_IRON_FIST_JUTSU = ENTITIES.register("black_iron_fist_jutsu", () -> EntityType.Builder.<BlackIronFistEntity>create(BlackIronFistEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":iron_fist_jutsu"));

    public static final RegistryObject<EntityType<ScorchingStreamRockEntity>> SCORCHING_STREAM_ROCK_JUTSU = ENTITIES.register("scorching_stream_rock_jutsu", () -> EntityType.Builder.<ScorchingStreamRockEntity>create(ScorchingStreamRockEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":scorching_stream_rock_jutsu"));
    public static final RegistryObject<EntityType<LavaShurikenEntity>> LAVA_SHURIKEN_JUTSU = ENTITIES.register("lava_shuriken_jutsu", () -> EntityType.Builder.<LavaShurikenEntity>create(LavaShurikenEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":lava_shuriken_jutsu"));

    public static final RegistryObject<EntityType<BurningAquaGunEntity>> BURNING_AQUA_GUN_JUTSU = ENTITIES.register("burning_aqua_gun_jutsu", () -> EntityType.Builder.<BurningAquaGunEntity>create(BurningAquaGunEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":burning_aqua_gun_jutsu"));
    public static final RegistryObject<EntityType<CorrosiveArrowEntity>> CORROSIVE_ARROW_JUTSU = ENTITIES.register("corrosive_arrow_jutsu", () -> EntityType.Builder.<CorrosiveArrowEntity>create(CorrosiveArrowEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":corrosive_arrow_jutsu"));

    public static final RegistryObject<EntityType<AmaterasuJutsuEntity>> AMATERASU_JUTSU = ENTITIES.register("amaterasu_jutsu", () -> EntityType.Builder.<AmaterasuJutsuEntity>create(AmaterasuJutsuEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setTrackingRange(128).size(1F,1F).build(Main.MODID + ":amaterasu_jutsu"));
}

