package com.teamsimplyrs.prismaarcanum.item.spells.spellholograms;

import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class AbstractSpellHologram extends Item {
    public final String ITEM_NAME = "spell_hologram";
    public AbstractSpellHologram(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }
}
