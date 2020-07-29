package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.blocks.*;
import com.benarutomod.tbroski.blocks.dojutsuskull.DojutsuSkullBlockBase;
import com.benarutomod.tbroski.blocks.explosivepaper.ExplosivePaperBlockBase;
import com.benarutomod.tbroski.blocks.teleportationpaper.TeleportationPaperBlockBase;
import com.benarutomod.tbroski.blocks.trees.SakuraTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Main.MODID);

    public static final ModdedWoodType SAKURA_WOOD_TYPE = new ModdedWoodType("sakura");


    //Blocks
    public static final RegistryObject<Block> EXPLOSIVE_PAPER = BLOCKS.register("explosive_paper", ExplosivePaperBlockBase::new);
    public static final RegistryObject<Block> TELEPORTATION_PAPER = BLOCKS.register("teleportation_paper", TeleportationPaperBlockBase::new);

    public static final RegistryObject<Block> AMATERASU = BLOCKS.register("amaterasu", AmaterasuFireBlockBase::new);

    public static final RegistryObject<Block> DOJUTSU_SKULL = BLOCKS.register("dojutsu_skull", DojutsuSkullBlockBase::new);

    public static final RegistryObject<Block> SAKURA_PLANKS = BLOCKS.register("sakura_planks", () -> new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_LOG = BLOCKS.register("sakura_log", () -> new LogBlock(MaterialColor.PINK, Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_SAKURA_LOG = BLOCKS.register("stripped_sakura_log", () -> new LogBlock(MaterialColor.PINK, Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_WOOD = BLOCKS.register("sakura_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_SAKURA_WOOD = BLOCKS.register("stripped_sakura_wood", () -> new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_LEAVES = BLOCKS.register("sakura_leaves", () -> new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<Block> SAKURA_SAPLING = BLOCKS.register("sakura_sapling", () -> new ModdedSaplingBlock(new SakuraTree(), Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0F).sound(SoundType.PLANT)));
    public static final RegistryObject<Block> SAKURA_SIGN = BLOCKS.register("sakura_sign", () -> new StandingSignBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.OAK));
    public static final RegistryObject<Block> SAKURA_WALL_SIGN = BLOCKS.register("sakura_wall_sign", () -> new WallSignBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).doesNotBlockMovement().hardnessAndResistance(1.0F).sound(SoundType.WOOD), WoodType.OAK));
    public static final RegistryObject<Block> SAKURA_DOOR = BLOCKS.register("sakura_door", () -> new ModdedDoorBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(3.0F).sound(SoundType.WOOD).notSolid()));
    public static final RegistryObject<Block> SAKURA_SLAB = BLOCKS.register("sakura_slab", () -> new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_STAIRS = BLOCKS.register("sakura_stairs", () -> new StairsBlock((() -> SAKURA_PLANKS.get().getDefaultState()), Block.Properties.from(SAKURA_PLANKS.get())));
    public static final RegistryObject<Block> SAKURA_FENCE_GATE = BLOCKS.register("sakura_fence_gate", () -> new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_FENCE = BLOCKS.register("sakura_fence", () -> new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.PINK).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_BUTTON = BLOCKS.register("sakura_button", () -> new ModdedWoodButtonBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SAKURA_PRESSURE_PLATE = BLOCKS.register("sakura_pressure_plate", () -> new ModdedPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.PINK).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)));

    public static final RegistryObject<Block> LIGNITE = BLOCKS.register("lignite", () -> new Block(Block.Properties.create(Material.ROCK, MaterialColor.DIRT).hardnessAndResistance(1.5F, 6.0F)));



    public static class ModdedWoodType extends WoodType {

        public ModdedWoodType(String nameIn) {
            super(nameIn);
        }
    }
}
