package org.sdatag.airdrop.block.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.sdatag.airdrop.block.entity.custom.Flare_BlockEntity;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class Flare_Block_Renderer extends GeoBlockRenderer<Flare_BlockEntity> {
    public Flare_Block_Renderer(BlockEntityRendererProvider.Context rendererProvider) {
        super(rendererProvider, new Flare_Block_Model());
    }

    @Override
    public RenderType getRenderType(Flare_BlockEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
}
