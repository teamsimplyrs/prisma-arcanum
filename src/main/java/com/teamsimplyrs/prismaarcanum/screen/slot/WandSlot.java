package com.teamsimplyrs.prismaarcanum.screen.slot;

import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class WandSlot extends SlotItemHandler {


    public WandSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    @Override
    public boolean mayPickup(Player playerIn) {
        boolean slot1Empty = this.getItemHandler().getStackInSlot(1).isEmpty();
        boolean slot2Empty = this.getItemHandler().getStackInSlot(2).isEmpty();
        boolean slot3Empty = this.getItemHandler().getStackInSlot(3).isEmpty();
        boolean slot4Empty = this.getItemHandler().getStackInSlot(4).isEmpty();
        return super.mayPickup(playerIn) && (slot1Empty && slot2Empty && slot3Empty && slot4Empty);
    }

    @Override
    public boolean mayPlace(ItemStack pStack) {
        return (pStack.getItem() instanceof AbstractWand);
    }
}
