package org.sdatag.airdrop.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.sdatag.airdrop.block.entity.ModBlockEntities;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class gunDropEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public gunDropEntity(BlockPos pWorldPosition, BlockState pBlockstate) {
        super(ModBlockEntities.gunDrop_Enity.get(), pWorldPosition, pBlockstate);
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        AnimationController<gunDropEntity> E = new AnimationController<gunDropEntity>
                (this, "controller", 0, this::predicate);
        animationData.addAnimationController(E);
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.model.IDLE", true));
        return PlayState.CONTINUE;
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
