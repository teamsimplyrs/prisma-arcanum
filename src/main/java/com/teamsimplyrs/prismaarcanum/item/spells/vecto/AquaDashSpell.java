package com.teamsimplyrs.prismaarcanum.item.spells.vecto;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class AquaDashSpell extends SpellBase {
    @Override
    public void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

    }

    @Override
    public void chargeSpell(Player pPlayer) {

    }

    @Override
    public boolean shouldSpellEvolve() {
        return false;
    }

    @Override
    public int maximumMasteryPoints() {
        return 0;
    }

    @Override
    public int masteryPointsToNextEvo() {
        return 0;
    }


    public static class AquaDashHandler
    {
        private static int dashTimer = 0;

        public static void startDash() {
            dashTimer = 40;
        }

        public static void tick() {
            if (dashTimer > 0) {
                dashTimer--;
            }
        }

        public static boolean isDashing() {
            return dashTimer > 0;
        }
    }
}
