package com.teamsimplyrs.prismaarcanum.particle.custom;

import com.teamsimplyrs.prismaarcanum.particle.particleOptions.IgnisParticleOptions;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class IgnisParticles extends TextureSheetParticle {

    private final Vec3 target;

    protected IgnisParticles(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, Vec3 pTarget, int pLifetime , SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);

        this.friction = 1f;
        this.xd = pXSpeed;
        this.yd = pYSpeed;
        this.zd = pZSpeed;
        this.quadSize *= 0.85f;
        this.lifetime = 60;
        this.hasPhysics = false;
        this.setSpriteFromAge(spriteSet);

        this.rCol = 1f;
        this.gCol = 1f;
        this.bCol = 1f;

        this.target = pTarget;
        this.lifetime = pLifetime;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            Vec3 destination = this.target;

                int i = this.lifetime - this.age;
                double d0 = 1.0D / (double)i;
                this.x = Mth.lerp(d0, this.x, destination.x());
                this.y = Mth.lerp(d0, this.y, destination.y());
                this.z = Mth.lerp(d0, this.z, destination.z());
                this.setPos(this.x, this.y, this.z); // FORGE: Update the particle's bounding box
         }
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
    public static class Provider implements ParticleProvider<IgnisParticleOptions>
    {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet)
        {
            this.sprites = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(IgnisParticleOptions options, ClientLevel clientLevel, double x, double y, double z, double xd, double yd, double zd) {
            return new IgnisParticles(clientLevel, x, y, z, xd, yd, zd,options.getDestination(),options.getArrivalInTicks(), sprites);
        }
    }
}
