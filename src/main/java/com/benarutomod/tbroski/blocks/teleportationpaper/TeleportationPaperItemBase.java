package com.benarutomod.tbroski.blocks.teleportationpaper;

import com.benarutomod.tbroski.Main;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class TeleportationPaperItemBase extends BlockItem {
    public TeleportationPaperItemBase(Block blockIn) {
        super(blockIn, new Item.Properties().group(Main.TAB));
    }
}
