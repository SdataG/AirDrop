package org.sdatag.airdrop.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.sdatag.airdrop.block.entity.custom.Flare_BlockEntity;
import org.sdatag.airdrop.block.entity.custom.gunDropEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class gunDrop_Renderer extends GeoBlockRenderer<gunDropEntity> {
    public gunDrop_Renderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new gunDrop_Model());
    }

    @Override
    public RenderType getRenderType(gunDropEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
