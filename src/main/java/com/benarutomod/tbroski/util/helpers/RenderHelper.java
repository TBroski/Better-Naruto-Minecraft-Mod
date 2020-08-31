package com.benarutomod.tbroski.util.helpers;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

public class RenderHelper {


    public static void drawOutlinedBox(AxisAlignedBB bb)
    {
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);

        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);

        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);

        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);

        GL11.glVertex3d(bb.minX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);

        GL11.glVertex3d(bb.maxX, bb.minY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);

        GL11.glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

        GL11.glVertex3d(bb.minX, bb.minY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);

        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);

        GL11.glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

        GL11.glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);

        GL11.glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        GL11.glVertex3d(bb.minX, bb.maxY, bb.minZ);
        GL11.glEnd();
    }
/*
    @OnlyIn(Dist.CLIENT)
    public static boolean forceRenderEntity(Entity entity) {
        ActiveRenderInfo camera = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();

        MatrixStack projectionStack = new MatrixStack();
        projectionStack.rotate(Vector3f.XP.rotationDegrees(camera.getPitch()));
        projectionStack.rotate(Vector3f.YP.rotationDegrees(camera.getYaw() + 180.0F));
        Matrix4f projectionMatrix = projectionStack.getLast().getMatrix();

        MatrixStack matrixStack = new MatrixStack();
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.getLast().getMatrix().mul(Minecraft.getInstance().gameRenderer.getProjectionMatrix(camera, 1.0F, true));
        Vector3f vector3f = new Vector3f(0.0F, MathHelper.SQRT_2 / 2.0F, MathHelper.SQRT_2 / 2.0F);
        matrixstack.scale(1.0F / f1, 1.0F, 1.0F);
        float f2 = -((float)this.rendererUpdateCount + partialTicks) * (float)i;
        matrixstack.rotate(vector3f.rotationDegrees(f2));
        Matrix4f matrix4f = matrixStack.getLast().getMatrix();
        Minecraft.getInstance().gameRenderer.resetProjectionMatrix(matrix4f);


        return false;
    }*/
}
