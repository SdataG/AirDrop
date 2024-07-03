package org.sdatag.airdrop.block.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.entity.custom.Flare_BlockEntity;
import org.sdatag.airdrop.block.entity.custom.gunDropEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class gunDrop_Model extends AnimatedGeoModel<gunDropEntity> {
    @Override
    public ResourceLocation getModelResource(gunDropEntity gunDrop_Entity) {
        return new ResourceLocation(AirDrop.MODID, "geo/gundrop.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(gunDropEntity gunDrop_Entity) {
        return new ResourceLocation(AirDrop.MODID, "textures/block/gundrop.png");
    }

    @Override
    public ResourceLocation getAnimationResource(gunDropEntity gunDrop_Entity) {
        return new ResourceLocation(AirDrop.MODID, "animations/gundrop.animation.json");
    }
}
