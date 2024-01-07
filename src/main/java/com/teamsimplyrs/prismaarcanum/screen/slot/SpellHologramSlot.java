package com.teamsimplyrs.prismaarcanum.screen.slot;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.AbstractSpellHolgram;
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
        LOGGER.info(stack.getItem().toString() + " " + (stack.getItem() instanceof AbstractSpellHolgram));
        return (stack.getItem() instanceof AbstractSpellHolgram);
    }
}
