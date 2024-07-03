package org.sdatag.airdrop.item.client;

import net.minecraft.resources.ResourceLocation;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.item.custom.Flare_Block_Item;
import org.sdatag.airdrop.item.custom.gunDrop_Item;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class gunDrop_Block_ItemModel extends AnimatedGeoModel<gunDrop_Item> {
    @Override
    public ResourceLocation getModelResource(gunDrop_Item flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "geo/gundrop.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(gunDrop_Item flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "textures/block/gundrop.png");
    }

    @Override
    public ResourceLocation getAnimationResource(gunDrop_Item flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "geo/gundrop.geo.json");
    }
}
