package com.teamsimplyrs.prismaarcanum.item.interfaces;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface ICastingItem {
    SpellBase getCurrentSpell(ItemStack stack);

    SpellBase getNextSpell(ItemStack stack);
    SpellBase getPreviousSpell(ItemStack stack);

    default SpellBase[] getAllEquippedSpells(ItemStack stack)
    {
        return new SpellBase[]{getCurrentSpell(stack)};
    }

    void selectNextSpell(ItemStack stack);
    void selectPreviousSpell(ItemStack stack);

    boolean selectSpell(ItemStack stack, int index);


    boolean cast(Player caster);

}
