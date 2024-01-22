package com.teamsimplyrs.prismaarcanum.item.spells;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EmptySpell extends SpellBase {
    public static final String SPELL_NAME = "Empty";
    public static final String SPELL_ELEMENT = "None";
    public static final String SPELL_SCHOOL = "None";


    @Override
    public void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
    }

    @Override
    public void chargeSpell(Player pPlayer) {

    }
}
