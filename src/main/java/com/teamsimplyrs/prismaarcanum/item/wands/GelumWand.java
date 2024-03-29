package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.item.interfaces.ICastingItem;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GelumWand extends AbstractWand implements ICastingItem {

    public static final String ITEM_NAME = "gelum_wand_tier1";

    public static final Properties ITEM_PROPERTIES = new Properties();

    public static List<SpellBase> listSpellsIgnis = new ArrayList<>();

    public final String WAND_ELEMENT = "gelum";
    @Override
    public SpellBase getCurrentSpell(ItemStack stack) {
        return null;
    }

    @Override
    public SpellBase getNextSpell(ItemStack stack) {
        return null;
    }

    @Override
    public SpellBase getPreviousSpell(ItemStack stack) {
        return null;
    }

    @Override
    public void selectNextSpell(ItemStack stack) {

    }

    @Override
    public void selectPreviousSpell(ItemStack stack) {

    }

    @Override
    public boolean selectSpell(ItemStack stack, int index) {
        return false;
    }


    @Override
    public boolean cast(Player caster) {
        return false;
    }
}
