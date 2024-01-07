package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractSpellHolgram extends Item {
    public final String ITEM_NAME = "spell_hologram";
    public AbstractSpellHolgram(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
