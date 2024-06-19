package org.sdatag.airdrop.entity.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.sdatag.airdrop.Sound.ModSounds;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Ac_130Entity extends PathfinderMob implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);
    public Ac_130Entity (EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }
    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D).build();
    }
    private <E extends IAnimatable>PlayState predicate(AnimationEvent<E> event) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ac_130.idle", true));
            return PlayState.CONTINUE;
        }


    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));

    }


    private int tickCounter = 550;

        @Override
        public void tick () {
            super.tick();
            if (++this.tickCounter >= 600) {
                this.level.playLocalSound(this.getX(), this.getY(), this.getZ(),ModSounds.Ac130Sound.get(), this.getSoundSource(), 2, 1, true);
                this.tickCounter = 0;
            }
        }

    @Override
    public void setNoGravity(boolean pNoGravity) {
        super.setNoGravity(true);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
