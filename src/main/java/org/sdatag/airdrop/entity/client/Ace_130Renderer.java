package org.sdatag.airdrop.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.entity.custom.Ac_130Entity;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class Ace_130Renderer extends GeoEntityRenderer<Ac_130Entity> {
    public Ace_130Renderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new Ac_130Model());
        this.shadowRadius = 0.7F;
    }
        @Override
        public ResourceLocation getTextureLocation (Ac_130Entity ac130Entity){
            return new ResourceLocation(AirDrop.MODID, "textures/entity/ac_130.png");

    }

    @Override
    public RenderType getRenderType(Ac_130Entity animatable, float partialTick, PoseStack poseStack,
                                    @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer,
                                    int packedLight, ResourceLocation texture) {
        poseStack.scale(2, 2,2);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
