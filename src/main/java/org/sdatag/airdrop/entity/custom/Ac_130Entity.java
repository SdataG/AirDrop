package org.sdatag.airdrop.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;
import org.sdatag.airdrop.DropChance;
import org.sdatag.airdrop.Sound.ModSounds;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.EnumSet;
import java.util.List;

public class Ac_130Entity extends PathfinderMob implements IAnimatable {
    private AnimationFactory factory = new AnimationFactory(this);

    public Ac_130Entity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
        this.setPersistenceRequired();
        this.moveControl = new FlyingMoveControl(Ac_130Entity.this, 1, true);
    }

    public static AttributeSupplier setAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.30D)
                .add(Attributes.FLYING_SPEED, 5).build();

    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.ac_130.idle", true));
        return PlayState.CONTINUE;
    }


    private int tickCounter = 550;

    @Override
    public void tick() {
        super.tick();
        // Only execute on the client side
        if (!this.level.isClientSide) {
            return;
        }
        // Get all players within 50 blocks
        List<Player> players = this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(75.0));
        for (Player player : players) {
            double distance = this.distanceTo(player);
            // Assume approx 50 blocks is the radius at which the audio should stop playing.
            if(distance >= 100.0) {
                return; // Exit the method if the player is outside of the radius.
            }
            float volume = (float) (1.0 - (distance / 50.0)); // Decrease volume proportionally to distance
            if (++this.tickCounter >= 600) {
                // Play the sound only for this player with adjusted volume
                player.playSound(ModSounds.Ac130Sound.get(), Math.max(volume, 0.1F), 1F);
                this.tickCounter = 0;
            }
        }
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));

    }

    @Override
    public void setNoGravity(boolean noGravity) {
        super.setNoGravity(noGravity);
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, worldIn);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }
    @Override
    public boolean removeWhenFarAway(double distance) {
        return false;
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new Goal() {
            private Vec3 dropLocation = DropChance.getDropLocation();
            private static final double CLOSE_ENOUGH_TO_GOAL_THRESHOLD = 1.0;

            @Override
            public boolean canUse() {
                return dropLocation != null &&
                        Ac_130Entity.this.position().distanceTo(dropLocation) > CLOSE_ENOUGH_TO_GOAL_THRESHOLD;
            }

            @Override
            public void start() {

                double dx = dropLocation.x - Ac_130Entity.this.getX();
                double dz = dropLocation.z - Ac_130Entity.this.getZ();
                double angle = (Math.atan2(dz, dx) * 180 / Math.PI) - 90;


                Ac_130Entity.this.setYRot((float)angle);
                Ac_130Entity.this.setNoGravity(true); // added line
                Vec3 currentPos = Ac_130Entity.this.position();
                Vec3 motion = dropLocation.subtract(currentPos).normalize().scale(0.5);
                Ac_130Entity.this.setDeltaMovement(motion);
            }

            @Override
            public void tick() {
                super.tick();
                if (dropLocation != null) {
                    Vec3 nextPosition = Ac_130Entity.this.position().add(
                            dropLocation.subtract(Ac_130Entity.this.position()).normalize().scale(0.5)
                    );

                    Ac_130Entity.this.setPos(nextPosition.x, Ac_130Entity.this.getY(), nextPosition.z);
                }
            }

            @Override
            public boolean canContinueToUse() {
                return !this.isNearDropLocation();  // Continue flying if not close enough to the drop location
            }

            private boolean isNearDropLocation() {
                return Ac_130Entity.this.position().distanceTo(dropLocation) <= CLOSE_ENOUGH_TO_GOAL_THRESHOLD;
            }

        });
    }
}
