package com.teamsimplyrs.prismaarcanum.item.spells.venatus;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Ignite extends SpellBase {

    public static final String SPELL_NAME = "Ignite";
    public static final String SPELL_ELEMENT = "Ignis";
    public static final String SPELL_SCHOOL = "Venatus";

    public boolean cast(){ return false; }

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
