package com.teamsimplyrs.prismaarcanum.entity.projectile;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.particle.PAParticles;
import com.teamsimplyrs.prismaarcanum.registry.PAEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.slf4j.Logger;

import java.util.Random;

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

        if (this.level().isClientSide())
        {
            setupAnimationStates();
            summonSpellParticles((float) this.position().x, (float) this.position().y, (float) this.position().z);
        }
    }

    private void setupAnimationStates()
    {
//        FIREBALL_ANIM_STATE.start(this.tickCount);
//        FIREBALL_ANIM_STATE.animateWhen(true, this.tickCount);
    }

    private void summonSpellParticles(float posX, float posY, float posZ)
    {
        this.level().addParticle(PAParticles.IGNIS_PARTICLES.get(),
                posX, posY, posZ, 1f, 1f, 1f
                );
    }
}
