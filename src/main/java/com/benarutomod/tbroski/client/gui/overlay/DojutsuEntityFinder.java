package com.benarutomod.tbroski.client.gui.overlay;

import com.benarutomod.tbroski.Config;
import com.benarutomod.tbroski.capabilities.player.IPlayerHandler;
import com.benarutomod.tbroski.capabilities.player.PlayerCapability;
import com.benarutomod.tbroski.capabilities.player.PlayerProvider;
import com.benarutomod.tbroski.util.helpers.DojutsuHelper;
import com.benarutomod.tbroski.util.helpers.RenderHelper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;


public class DojutsuEntityFinder {

    @SubscribeEvent
    public void render(RenderHandEvent event) {

            ClientPlayerEntity player = Minecraft.getInstance().player;
            LazyOptional<IPlayerHandler> playerc = player.getCapability(PlayerProvider.CAPABILITY_PLAYER, null);
            IPlayerHandler player_cap = playerc.orElse(new PlayerCapability());
            ArrayList<MobEntity> entities = new ArrayList<>();


            if (player_cap.returnBodyInfusionToggled()) {
                if (player_cap.returnPlayerLeftDojutsu().canSeeChakra() || player_cap.returnPlayerRightDojutsu().canSeeChakra()) {
                    entities.clear();
                    for (Entity entity : Minecraft.getInstance().world.getAllEntities()) {
                        if ((!(entity instanceof PlayerEntity) || Config.COMMON.byakuganCanSeePlayers.get()) && entity instanceof MobEntity) {
                            entities.add((MobEntity) entity);
                        }
                    }

                    GL11.glEnable(GL11.GL_LINE_SMOOTH);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    GL11.glLineWidth(2);
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    GL11.glDisable(GL11.GL_DEPTH_TEST);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glPushMatrix();

                    ActiveRenderInfo camera = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
                    MatrixStack matrixstack = new MatrixStack();
                    matrixstack.getLast().getMatrix().mul(Minecraft.getInstance().gameRenderer.getProjectionMatrix(camera, event.getPartialTicks(), true));

                    Matrix4f matrix4f = matrixstack.getLast().getMatrix();
                    Minecraft.getInstance().gameRenderer.resetProjectionMatrix(matrix4f);
                    camera.update(Minecraft.getInstance().world, (Entity)(Minecraft.getInstance().getRenderViewEntity() == null ? Minecraft.getInstance().player : Minecraft.getInstance().getRenderViewEntity()), Minecraft.getInstance().gameSettings.thirdPersonView > 0, Minecraft.getInstance().gameSettings.thirdPersonView == 2, event.getPartialTicks());
                    GL11.glRotated(MathHelper.wrapDegrees(camera.getPitch()) + (player.getFovModifier() * 0.5F), 1, 0, 0);
                    GL11.glRotated(MathHelper.wrapDegrees(camera.getYaw() + 180.0), 0, 1, 0);
                    Vec3d camPos = Minecraft.getInstance().gameRenderer.getActiveRenderInfo().getProjectedView();
                    GL11.glTranslated(-camPos.x, -camPos.y, -camPos.z);

                    renderBoxes(entities, event);


                    GL11.glPopMatrix();
                    GL11.glColor4f(1, 1, 1, 1);
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glEnable(GL11.GL_DEPTH_TEST);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glDisable(GL11.GL_LINE_SMOOTH);
                }
            }
        }

    private void renderBoxes(ArrayList<MobEntity> entities, RenderHandEvent event) {
        for (MobEntity e : entities) {

            GL11.glPushMatrix();

            float partialTicks = event.getPartialTicks();
            GL11.glTranslated(e.prevPosX + (e.getPosX() - e.prevPosX) * partialTicks, e.prevPosY + (e.getPosY() - e.prevPosY) * partialTicks, e.prevPosZ + (e.getPosZ() - e.prevPosZ) * partialTicks);
            GL11.glScaled(e.getWidth(), e.getHeight(), e.getWidth());

            float f = Minecraft.getInstance().player.getDistance(e) / 20F;
            GL11.glColor4f(0, f, 2 - f, 0.5F);

            AxisAlignedBB bb = new AxisAlignedBB(-0.5, 0, -0.5, 0.5, 1, 0.5);
            RenderHelper.drawOutlinedBox(bb);
            GL11.glPopMatrix();

        }
    }
}
