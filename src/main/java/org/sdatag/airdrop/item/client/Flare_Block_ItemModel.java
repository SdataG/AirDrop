package org.sdatag.airdrop.item.client;

import net.minecraft.resources.ResourceLocation;
import org.sdatag.airdrop.AirDrop;
import org.sdatag.airdrop.block.entity.custom.Flare_BlockEntity;
import org.sdatag.airdrop.item.custom.Flare_Block_Item;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class Flare_Block_ItemModel extends AnimatedGeoModel<Flare_Block_Item> {
    @Override
    public ResourceLocation getModelResource(Flare_Block_Item flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "geo/flare_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Flare_Block_Item flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "textures/block/flare_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Flare_Block_Item flareBlockEntity) {
        return new ResourceLocation(AirDrop.MODID, "geo/flare_block.geo.json");
    }
}
