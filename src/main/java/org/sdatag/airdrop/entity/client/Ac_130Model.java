package org.sdatag.airdrop.entity.client;

import net.minecraft.resources.ResourceLocation;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.entity.custom.Ac_130Entity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Ac_130Model extends AnimatedGeoModel<Ac_130Entity> {
    @Override
    public ResourceLocation getModelResource(Ac_130Entity ac130Entity) {
        return new ResourceLocation(AirDrop.MODID, "geo/ac_130.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Ac_130Entity ac130Entity) {
        return new ResourceLocation(AirDrop.MODID, "textures/entity/ac_130.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Ac_130Entity ac130Entity) {
        return new ResourceLocation(AirDrop.MODID, "animations/ac_130.animation.json");
    }
}
