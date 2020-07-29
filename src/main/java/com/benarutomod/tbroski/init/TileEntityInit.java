package com.benarutomod.tbroski.init;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.tileentity.AmaterasuTileEntity;
import com.benarutomod.tbroski.tileentity.DojutsuSkullTileEntity;
import com.benarutomod.tbroski.tileentity.TeleportationPaperTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityInit {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Main.MODID);


    public static final RegistryObject<TileEntityType<TeleportationPaperTileEntity>> TELEPORTATION_PAPER = TILE_ENTITIES.register("teleportation_paper", () ->
            TileEntityType.Builder.create(TeleportationPaperTileEntity::new, BlockInit.TELEPORTATION_PAPER.get()).build(null));

    public static final RegistryObject<TileEntityType<AmaterasuTileEntity>> AMATERASU = TILE_ENTITIES.register("amaterasu", () ->
            TileEntityType.Builder.create(AmaterasuTileEntity::new, BlockInit.AMATERASU.get()).build(null));

    public static final RegistryObject<TileEntityType<DojutsuSkullTileEntity>> DOJUTSU_SKULL = TILE_ENTITIES.register("dojutsu_skull", () ->
            TileEntityType.Builder.create(DojutsuSkullTileEntity::new, BlockInit.DOJUTSU_SKULL.get()).build(null));
}
