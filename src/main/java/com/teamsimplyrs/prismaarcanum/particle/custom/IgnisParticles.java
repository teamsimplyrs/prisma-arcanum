package com.teamsimplyrs.prismaarcanum.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class IgnisParticles extends TextureSheetParticle {
    protected IgnisParticles(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 0.8f;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.quadSize *= 0.85f;
        this.lifetime = 60;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;
    }

    @Override
    public void tick() {
        super.tick();
        particleFadeOut();
    }

    private void particleFadeOut()
    {
        this.alpha = (-(1/(float)lifetime) * age + 1);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet)
        {
            this.sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double xd, double yd, double zd) {
            return new IgnisParticles(clientLevel, x, y, z, xd, yd, zd, sprites);
        }
    }
}
