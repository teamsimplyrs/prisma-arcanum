package com.teamsimplyrs.prismaarcanum.screen.slot;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.AbstractSpellHologram;
import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.slf4j.Logger;

public class SpellHologramSlot extends SlotItemHandler {
    public SpellHologramSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public boolean mayPlace(ItemStack stack) {
        return (stack.getItem() instanceof AbstractSpellHologram);
    }

    @Override
    public boolean isHighlightable() {
        return this.getItemHandler().getStackInSlot(0).getItem() instanceof AbstractWand;
    }
}
