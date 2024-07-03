package org.sdatag.airdrop.block.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;

import net.minecraft.world.level.block.state.BlockState;
import org.sdatag.airdrop.block.entity.ModBlockEntities;
import org.sdatag.airdrop.particle.ModParticles;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.ParticleKeyFrameEvent;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Flare_BlockEntity extends BlockEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    public Flare_BlockEntity(BlockPos pWorldPosition, BlockState pBlockstate) {
        super(ModBlockEntities.Flare_Block_Entity.get(), pWorldPosition, pBlockstate);
    }
    int delayCounter = 0;
    public void tick() {
        double x = getBlockPos().getX();  // 10 blocks away in the 'x' axis
        double y = getBlockPos().getY();
        double z = getBlockPos().getZ();// 10 blocks away in the 'z' axis
        delayCounter++;
        if (delayCounter >= 3) {
            level.addParticle(ModParticles.CustomCampfireSmokeParticle.get(), true, x, y, z, 0f, 0.1f, 0f);
            delayCounter = 0;
        }
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        AnimationController<Flare_BlockEntity> E = new AnimationController<Flare_BlockEntity>
                (this, "controller", 0, this::predicate);
        E.registerParticleListener(new AnimationController.IParticleListener<Flare_BlockEntity>() {
            @Override
            public void summonParticle(ParticleKeyFrameEvent<Flare_BlockEntity> particleKeyFrameEvent) {
            }
        });
        animationData.addAnimationController(E);
    }

    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
        return PlayState.CONTINUE;
    }
    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
