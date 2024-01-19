package com.teamsimplyrs.prismaarcanum.entity.projectile;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.particle.particleOptions.IgnisParticleOptions;
import com.teamsimplyrs.prismaarcanum.registry.PAEntities;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;

public class FireballProjectile extends ThrowableItemProjectile {

    private static final Logger LOGGER = LogUtils.getLogger();

    public static final AnimationState FIREBALL_ANIM_STATE = new AnimationState();
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
            this.discard();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide) {
            this.makeParticle(2);
        }

        if (this.level().isClientSide())
        {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates()
    {
        if (this.animationTimeout <= 0)
        {
            this.animationTimeout = this.random.nextInt(40)+80;
            FIREBALL_ANIM_STATE.start(this.tickCount);
        } else {
            animationTimeout--;
        }
    }

    private void makeParticle(int pParticleAmount) {
        if (pParticleAmount > 0) {
            for(int j = 0; j < pParticleAmount; ++j) {
                double x = this.getRandomX(0.5D);
                double y = this.getRandomY();
                double z = this.getRandomZ(0.5D);
                Vec3 position = new Vec3(x,y,z);
                this.level().addParticle(new IgnisParticleOptions(position,20), position.x, position.y, position.z, 0,0,0);
            }

        }
    }
}
