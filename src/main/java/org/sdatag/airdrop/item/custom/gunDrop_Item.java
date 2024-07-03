package org.sdatag.airdrop.item.custom;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.sdatag.airdrop.item.client.Flare_Item_Block_Renderer;
import org.sdatag.airdrop.item.client.gunDrop_Item_Block_Renderer;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Consumer;

public class gunDrop_Item extends BlockItem implements IAnimatable {
    @SuppressWarnings("removal")
    public AnimationFactory factory = new AnimationFactory(this);

    public gunDrop_Item(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }
@Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private gunDrop_Item_Block_Renderer renderer;

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null) {
                    this.renderer = new gunDrop_Item_Block_Renderer();
}
                return this.renderer;
            }
        });
        }




    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    private <E extends IAnimatable > PlayState predicate(AnimationEvent < E > event) {
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}


