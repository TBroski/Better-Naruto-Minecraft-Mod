package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.blocks.ToadOilFluidBlock;
import com.benarutomod.tbroski.fluid.ToadOilFluid;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {
    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, Main.MODID);

    public static final RegistryObject<FlowingFluid> FLOWING_TOAD_OIL = FLUIDS.register("flowing_toad_oil", () -> new ToadOilFluid.Flowing());
    public static final RegistryObject<FlowingFluid> TOAD_OIL = FLUIDS.register("toad_oil", () -> new ToadOilFluid.Source());
    public static final RegistryObject<Block> TOAD_OIL_BLOCK = BlockInit.BLOCKS.register("toad_oil", () -> new ToadOilFluidBlock(TOAD_OIL, Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops()));

    public static class Tags {
        public static final Tag<Fluid> TOAD_OIL = new FluidTags.Wrapper(new ResourceLocation(Main.MODID, "toad_oil"));
    }
}
