package com.teamsimplyrs.prismaarcanum.item.spells;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class NullSpell extends SpellBase {


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
}
