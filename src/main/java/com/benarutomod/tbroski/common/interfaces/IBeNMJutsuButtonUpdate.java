package com.benarutomod.tbroski.common.interfaces;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public interface IBeNMJutsuButtonUpdate {
    void update(GuiButtonJutsu buttonJutsu, IPlayerHandler playerCapability);
}
