package com.benarutomod.tbroski.common.interfaces;

import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;

@OnlyIn(Dist.CLIENT)
public interface IBeNMJutsuButtonPress extends Button.IPressable {

    void onPress(GuiButtonJutsu buttonJutsu, IPlayerHandler playerCapability);

    @Override
    default void onPress(Button p_onPress_1_) {
        if (p_onPress_1_ instanceof GuiButtonJutsu) {
            ClientPlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

            onPress((GuiButtonJutsu) p_onPress_1_, playerc);
        }
    }
}
