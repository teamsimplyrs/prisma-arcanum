package com.teamsimplyrs.prismaarcanum.screen.slot;

import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class WandSlot extends SlotItemHandler {


    public WandSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return (pStack.getItem() instanceof AbstractWand);
    }
}
