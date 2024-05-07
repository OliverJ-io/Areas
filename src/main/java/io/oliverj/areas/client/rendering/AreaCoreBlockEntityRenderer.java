package io.oliverj.areas.client.rendering;

import io.oliverj.areas.block.entity.AreaCoreBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.mixin.client.model.loading.ModelLoaderMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class AreaCoreBlockEntityRenderer implements BlockEntityRenderer<AreaCoreBlockEntity> {

    @SuppressWarnings("unused")
    public AreaCoreBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {}
    @Override
    public void render(AreaCoreBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = entity.getRenderStack();
        if (stack.isEmpty()) {
            stack = ItemStack.EMPTY;
        }
        matrices.push();
        matrices.translate(0.5f, 0.70f, 0.5f);
        matrices.scale(0.35f, 0.35f, 0.35f);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(270f));
        itemRenderer.renderItem(
                stack,
                ModelTransformationMode.GUI,
                light,
                OverlayTexture.DEFAULT_UV,
                matrices,
                vertexConsumers,
                entity.getWorld(),
                1
        );
        matrices.pop();
    }

    private int getLightLevel(World world, BlockPos pos) {
        int bLight = world.getLightLevel(LightType.BLOCK, pos);
        int sLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(bLight, sLight);
    }
}
