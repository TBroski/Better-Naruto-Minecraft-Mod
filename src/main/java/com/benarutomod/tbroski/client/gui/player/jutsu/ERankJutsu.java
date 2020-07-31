package com.benarutomod.tbroski.client.gui.player.jutsu;

import com.benarutomod.tbroski.Main;
import com.benarutomod.tbroski.client.gui.widgets.jutsu.GuiButtonJutsu;
import com.benarutomod.tbroski.common.jutsu.BodyReplacementJutsu;
import com.benarutomod.tbroski.common.jutsu.CloneJutsu;
import com.benarutomod.tbroski.common.jutsu.EffectsJutsu;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.common.jutsu.SharinganJutsu;
import com.benarutomod.tbroski.common.jutsu.TransformationJutsu;
import com.benarutomod.tbroski.networking.NetworkLoader;
import com.benarutomod.tbroski.networking.packets.PacketBeNMPointsSync;
import com.benarutomod.tbroski.networking.packets.settings.PacketKeybindSet;
import com.benarutomod.tbroski.networking.packets.jutsu.PacketSetJutsuBoolean;
import com.benarutomod.tbroski.networking.packets.PacketShinobiLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import org.lwjgl.opengl.GL11;

public class ERankJutsu extends AbstractJutsuScreen {

    GuiButtonJutsu cloneJutsu;
    GuiButtonJutsu bodyReplacement;
    GuiButtonJutsu invisibilityJutsu;
    GuiButtonJutsu transformation;

    public ERankJutsu() {
        super(new TranslationTextComponent("gui." + Main.MODID + ".title.erankjutsu"));
    }

    @Override
    public void registerJutsus(IPlayerHandler playerCapability) {
        addButton(cloneJutsu = new GuiButtonJutsu(this.guiLeft - 90, this.guiTop - 90, 0, 16, "clone", playerCapability.hasCloneJutsuBoolean(), 6,  $ -> {
            boolean didBuy = cloneJutsu.doNormalPress(this);
            if (didBuy) {
                playerCapability.setCloneJutsuBoolean(true);
                cloneJutsu.sendPackets(CloneJutsu.BasicCloneJutsuID, true);
            }
            setGenin();
        }));

        addButton(bodyReplacement = new GuiButtonJutsu(this.guiLeft - 70, this.guiTop - 90, 0, 32, "replacement", playerCapability.hasBodyReplacementBoolean(), 3, $ -> {
            boolean didBuy = bodyReplacement.doNormalPress(this);
            if (didBuy) {
                playerCapability.setBodyReplacementBoolean(true);
                bodyReplacement.sendPackets(BodyReplacementJutsu.BasicBodyReplacementJutsuID, true);
            }
            setGenin();
        }));

        addButton(invisibilityJutsu = new GuiButtonJutsu(this.guiLeft - 50, this.guiTop - 90, 0, 0, "invisibility", playerCapability.hasInvisibilityBoolean(), 2, $ -> {
            boolean didBuy = invisibilityJutsu.doNormalPress(this);
            if (didBuy) {
                playerCapability.setInvisibilityBoolean(true);
                invisibilityJutsu.sendPackets(EffectsJutsu.InvisibilityTechniqueID, true);
            }
            setGenin();
        }));

        addButton(transformation = new GuiButtonJutsu(this.guiLeft - 30, this.guiTop - 90, 0, 64, "transformation", playerCapability.hasTransformationBoolean(), 5, $ -> {
            boolean didBuy = transformation.doNormalPress(this);
            if (didBuy) {
                playerCapability.setInvisibilityBoolean(true);
                transformation.sendPackets(TransformationJutsu.TransformationJutsuID, true);
            }
            setGenin();
        }));
    }

    @Override
    public void setJutsuBooleans(IPlayerHandler playerCapability) {
        cloneJutsu.setHasJutsu(playerCapability.hasCloneJutsuBoolean());
        bodyReplacement.setHasJutsu(playerCapability.hasBodyReplacementBoolean());
        invisibilityJutsu.setHasJutsu(playerCapability.hasInvisibilityBoolean());
        transformation.setHasJutsu(playerCapability.hasTransformationBoolean());
    }


    public void setGenin()
    {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        LazyOptional<IPlayerHandler> player_cap = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
        IPlayerHandler playerc = player_cap.orElse(new PlayerCapability());

        playerc.setShinobiLevel(1);
        NetworkLoader.INSTANCE.sendToServer(new PacketShinobiLevel(playerc.returnShinobiLevel(), false));
    }
}
