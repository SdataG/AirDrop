package org.sdatag.airdrop.item.client;

import org.sdatag.airdrop.item.custom.Flare_Block_Item;
import org.sdatag.airdrop.item.custom.gunDrop_Item;
import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

public class gunDrop_Item_Block_Renderer extends GeoItemRenderer<gunDrop_Item> {
    public gunDrop_Item_Block_Renderer() {super(new gunDrop_Block_ItemModel());}
}
