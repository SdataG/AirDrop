package org.sdatag.airdrop.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class CustomCampfireSmokeParticle extends TextureSheetParticle {
    CustomCampfireSmokeParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, boolean pSignal) {
        super(pLevel, pX, pY, pZ);
        this.scale(3.0F);
        this.setSize(0.25F, 0.25F);
        if (pSignal) {
            this.lifetime = this.random.nextInt(50) + 280;
        } else {
            this.lifetime = this.random.nextInt(50) + 80;
        }

        this.gravity = 3.0E-6F;
        this.xd = pXSpeed;
        this.yd = pYSpeed + (double)(this.random.nextFloat() / 500.0F);
        this.zd = pZSpeed;
        this.rCol = 2;
        this.bCol = 0.25f;
        this.gCol = 0.25f;
        this.alpha = 0.05f;
    }

    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ < this.lifetime && !(this.alpha <= 0.0F)) {
            this.xd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.zd += (double)(this.random.nextFloat() / 5000.0F * (float)(this.random.nextBoolean() ? 1 : -1));
            this.yd -= (double)this.gravity;
            this.move(this.xd, this.yd, this.zd);
            if (this.age >= this.lifetime - 60 && this.alpha > 0.01F) {
                this.alpha -= 0.015F;
            }

        } else {
            this.remove();
        }
    }
    @Override
    public boolean shouldCull() {
        return false;
    }


    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class CosyProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public CosyProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            CustomCampfireSmokeParticle custom = new CustomCampfireSmokeParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, false);
            custom.setAlpha(0.9F);
            custom.pickSprite(this.sprites);
            return custom;
        }
    }


    @OnlyIn(Dist.CLIENT)
    public static class SignalProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public SignalProvider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            CustomCampfireSmokeParticle custom = new CustomCampfireSmokeParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed, true);
            custom.setAlpha(0.95F);
            custom.pickSprite(this.sprites);
            return custom;

        }

    }

}

