package com.teamsimplyrs.prismaarcanum.item.spells;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class SpellBase {

    public abstract void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand);

    public abstract void chargeSpell(Player pPlayer);
}
