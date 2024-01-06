package com.teamsimplyrs.prismaarcanum.screen.slot;

import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.AbstractSpellHolgram;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class SpellHologramSlot extends SlotItemHandler {
    public SpellHologramSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return stack.getItem() instanceof AbstractSpellHolgram;
    }
}
