package com.teamsimplyrs.prismaarcanum.item.spells.interitus;

import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellProjectile;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IgnisFireball extends SpellProjectile {

    public static String spellName = "Fireball";
    public static String spellElement = "Ignis";
    public static String spellSchool = "Interitus";


    public static void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        if (!pLevel.isClientSide)
        {
            FireballProjectile fireballProjectile = new FireballProjectile(pPlayer, pLevel);
            fireballProjectile.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0f, 0.6F, 1F);
            pLevel.addFreshEntity(fireballProjectile);
        }
    }
}
