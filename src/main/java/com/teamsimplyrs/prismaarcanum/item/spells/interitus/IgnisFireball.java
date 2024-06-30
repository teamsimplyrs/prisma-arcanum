package com.teamsimplyrs.prismaarcanum.item.spells.interitus;

import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellProjectile;
import com.teamsimplyrs.prismaarcanum.particle.particleOptions.IgnisParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;

public class IgnisFireball extends SpellProjectile {

    private static final Logger LOGGER = LogManager.getLogger();
    private UUID currentFireballUUID = null;

    public IgnisFireball(){
        this.spellName = "Fireball";
        this.spellElement = "Ignis";
        this.spellSchool = "Interitus";
    }


    @Override
    public void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide){
            FireballProjectile entity = (FireballProjectile) ((ServerLevel)pLevel).getEntity(currentFireballUUID);
            if(entity.getDeltaMovement().equals(Vec3.ZERO)){
                entity.setNoGravity(false);
                entity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 0.6F, 1F);
            }
        }
    }

    @Override
    public void chargeSpell(Player pPlayer) {
        Vec3 lookVector = pPlayer.getLookAngle();

        // Calculate the up vector based on the pitch angle
        float pitch = pPlayer.getXRot();
        double pitchRadians = Math.toRadians(pitch);
        Vec3 upVector = new Vec3(-Math.sin(pitchRadians), Math.cos(pitchRadians), 0);

        // Calculate a perpendicular vector to the look vector
        Vec3 UVector = lookVector.cross(upVector).normalize();
        Vec3 VVector = lookVector.cross(UVector).normalize();

        // Calculate the spawn location at a radius of 2f
        Vec3 spawnLocation = pPlayer.getEyePosition().add(lookVector.multiply(2f, 2f, 2f));

        // Add particles with velocity towards the origin spawnLocation
        for (int i = 0; i < 5; i++) {
            double angle = Math.toDegrees(pPlayer.getRandom().nextIntBetweenInclusive(0,360));
            double x, y, z;

            x = spawnLocation.x + UVector.x * 2f * Math.cos(angle) + VVector.x * 2f * Math.sin(angle) + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;
            y = spawnLocation.y + UVector.y * 2f * Math.cos(angle) + VVector.y * 2f * Math.sin(angle) + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;
            z = spawnLocation.z + UVector.z * 2f * Math.cos(angle) + VVector.z * 2f * Math.sin(angle) + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;


            double velocityX = (spawnLocation.x - x) * 0.1;
            double velocityY = (spawnLocation.y - y) * 0.1;
            double velocityZ = (spawnLocation.z - z) * 0.1;

            pPlayer.level().addParticle(new IgnisParticleOptions(spawnLocation, 10), x, y, z, velocityX, velocityY, velocityZ);
        }

        Level level = pPlayer.level();
        if(!level.isClientSide()){
            ServerLevel serverLevel = (ServerLevel)level;
            if(currentFireballUUID==null){
                FireballProjectile fireballProjectile = new FireballProjectile(pPlayer, serverLevel);
                fireballProjectile.setPos(pPlayer.getEyePosition().add(lookVector.multiply(2f, 2f, 2f)));
                fireballProjectile.setNoGravity(true);
                serverLevel.addFreshEntity(fireballProjectile);
                currentFireballUUID = fireballProjectile.getUUID();
//                fireballProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 0.6F, 1F);
            }
            else{
                Entity entity = serverLevel.getEntity(currentFireballUUID);
                if(entity==null){
                    currentFireballUUID = null;
                }
                else if(entity.getDeltaMovement().equals(Vec3.ZERO)){
                    entity.setPos(pPlayer.getEyePosition().add(lookVector.multiply(2f, 2f, 2f)));
                }
            }
        }
    }

    @Override
    public boolean shouldSpellEvolve() {
        return true;
    }

    @Override
    public int maximumMasteryPoints() {
        return 0;
    }

    @Override
    public int masteryPointsToNextEvo() {
        return 0;
    }
}
