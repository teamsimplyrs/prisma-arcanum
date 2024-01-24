package com.teamsimplyrs.prismaarcanum.item.spells;

import com.teamsimplyrs.prismaarcanum.registry.PASpellRegistry;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public abstract class SpellBase {

    public String spellName, spellElement, spellSchool, spellTier;
    public SpellBase SpellEvo;
    public SpellBase SpellEvolvesFrom;

    public abstract void spellCast(Level pLevel, Player pPlayer, InteractionHand pUsedHand);

    public abstract void chargeSpell(Player pPlayer);

    public boolean hasEvolution()
    {
        return (SpellEvo != PASpellRegistry.getSpell("null"));
    }
    public boolean isSpellEvolution()
    {
        return (SpellEvolvesFrom != PASpellRegistry.getSpell("null"));
    }
    public abstract boolean shouldSpellEvolve();
    public abstract int maximumMasteryPoints();
    public abstract int masteryPointsToNextEvo();
}
