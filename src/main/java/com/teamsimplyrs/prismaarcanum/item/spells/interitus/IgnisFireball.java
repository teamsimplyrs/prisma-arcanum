package com.teamsimplyrs.prismaarcanum.item.spells.interitus;

import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellProjectile;
import com.teamsimplyrs.prismaarcanum.particle.particleOptions.IgnisParticleOptions;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IgnisFireball extends SpellProjectile {

    public static String spellName = "Fireball";
    public static String spellElement = "Ignis";
    public static String spellSchool = "Interitus";
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide)
        {
            FireballProjectile fireballProjectile = new FireballProjectile(pPlayer, pLevel);
            fireballProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 0.6F, 1F);
            pLevel.addFreshEntity(fireballProjectile);
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
        Vec3 perpendicularVector = lookVector.cross(upVector).normalize();

        // Calculate the spawn location at a radius of 2f
        Vec3 spawnLocation = pPlayer.getEyePosition().add(lookVector.multiply(2f, 2f, 2f));

        // Add particles with velocity towards the origin spawnLocation
        for (int i = 0; i < 5; i++) {
            double angle = Math.toDegrees(pPlayer.getRandom().nextIntBetweenInclusive(0,360));
            double x, y, z;
            if (Math.abs(lookVector.y) <= 0.8f)
            {
                x = spawnLocation.x + 2f * Math.cos(angle) * perpendicularVector.x + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;
                y = spawnLocation.y + 2f * Math.sin(angle) + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.5;
                z = spawnLocation.z + 2f * Math.cos(angle) * perpendicularVector.z + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;
            }
            else
            {
                x = spawnLocation.x + 2f * Math.cos(angle) + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;
                y = spawnLocation.y + 2f * Math.cos(angle) * perpendicularVector.y + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.5;
                z = spawnLocation.z + 2f * Math.sin(angle) + (pPlayer.getRandom().nextFloat() - 0.5f) * 0.2;
            }

            double velocityX = (spawnLocation.x - x) * 0.1;
            double velocityY = (spawnLocation.y - y) * 0.1;
            double velocityZ = (spawnLocation.z - z) * 0.1;

            pPlayer.level().addParticle(new IgnisParticleOptions(spawnLocation, 10), x, y, z, velocityX, velocityY, velocityZ);
        }
    }
}
