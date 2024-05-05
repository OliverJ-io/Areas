package io.oliverj.areas.client.rendering;

import io.oliverj.areas.block.entity.AreaCoreBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RotationAxis;

@Environment(EnvType.CLIENT)
public class AreaCoreBlockEntityRenderer implements BlockEntityRenderer<AreaCoreBlockEntity> {

    public AreaCoreBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}
    @Override
    public void render(AreaCoreBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();

        entity.getWorld().updateListeners(entity.getPos(), entity.getWorld().getBlockState(entity.getPos()), entity.getWorld().getBlockState(entity.getPos()), Block.NOTIFY_LISTENERS);

        double offset = Math.sin((entity.getWorld().getTime() + tickDelta) / 8.0) / 4.0;
        matrices.translate(0.5, 1.25 + offset, 0.5);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((entity.getWorld().getTime() + tickDelta) * 4));

        MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), ModelTransformationMode.GROUND, light, overlay, matrices, vertexConsumers, entity.getWorld(), 0);

        matrices.pop();
    }
}
