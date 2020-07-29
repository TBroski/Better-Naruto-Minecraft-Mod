package com.benarutomod.tbroski.blocks.explosivepaper;

import com.benarutomod.tbroski.Main;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ExplosivePaperItemBase extends BlockItem {
    public ExplosivePaperItemBase(Block blockIn) {
        super(blockIn, new Item.Properties().group(Main.TAB));
    }
}
