package org.sdatag.airdrop.block.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.entity.custom.Flare_BlockEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Flare_Block_Model extends AnimatedGeoModel<Flare_BlockEntity> {
    @Override
    public ResourceLocation getModelResource(Flare_BlockEntity flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "geo/flare_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Flare_BlockEntity flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "textures/block/flare_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Flare_BlockEntity flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "animations/flare_block.animation.json");
    }
}
