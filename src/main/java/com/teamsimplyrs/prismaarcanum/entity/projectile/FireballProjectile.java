package com.teamsimplyrs.prismaarcanum.entity.projectile;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.particle.particleOptions.IgnisParticleOptions;
import com.teamsimplyrs.prismaarcanum.registry.PAEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

public class FireballProjectile extends ThrowableItemProjectile {

    private static final Logger LOGGER = LogUtils.getLogger();

    public final AnimationState FIREBALL_ANIM_STATE = new AnimationState();
    public Vec3 scale = new Vec3(0.1,0.1,0.1);
    private int animationTimeout = 0;


    public FireballProjectile(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireballProjectile(Level pLevel) {
        super(PAEntities.FIREBALL_PROJECTILE.get(), pLevel);
    }

    public FireballProjectile(LivingEntity pShooter, Level pLevel) {
        super(PAEntities.FIREBALL_PROJECTILE.get(), pShooter, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return null;
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide)
        {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.level().setBlock(blockPosition(), Blocks.FIRE.defaultBlockState(), 3);
            this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 4.0F, Level.ExplosionInteraction.MOB);
            this.discard();
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if (!this.level().isClientSide)
        {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.level().broadcastDamageEvent(pResult.getEntity(), damageSources().onFire());
            pResult.getEntity().hurt(damageSources().onFire(), 4f);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(!(scale.x > 1f))
            scale = scale.add(0.05,0.05,0.05);
        if (this.level().isClientSide) {
            if(!this.getDeltaMovement().equals(Vec3.ZERO)){
                this.makeParticle(10);
            }
            else{
                this.makeParticle(2);
            }
            setupAnimationStates();
        }
    }

    private void setupAnimationStates()
    {
        FIREBALL_ANIM_STATE.startIfStopped(this.tickCount);
    }



    private void makeParticle(int pParticleAmount) {
        if (pParticleAmount > 0) {
            for(int j = 0; j < pParticleAmount; ++j) {
                double x = this.getRandomX(0.5D);
                double y = this.getRandomY()-0.5;
                double z = this.getRandomZ(0.5D);
                double x2 = this.getRandomX(0.5D);
                double y2 = this.getRandomY()-0.5;
                double z2 = this.getRandomZ(0.5D);
                Vec3 position = new Vec3(x,y,z);
                this.level().addParticle(new IgnisParticleOptions(position,20), x, y, z, 0,0,0);
                this.level().addParticle(ParticleTypes.SMOKE, x2, y2, z2, 0,0,0);
            }

        }
    }
}
