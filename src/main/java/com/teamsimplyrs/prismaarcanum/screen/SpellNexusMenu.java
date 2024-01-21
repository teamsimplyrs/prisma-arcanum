package com.teamsimplyrs.prismaarcanum.screen;

import com.mojang.logging.LogUtils;
import com.teamsimplyrs.prismaarcanum.block.entity.SpellNexusBlockEntity;
import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.AbstractSpellHologram;
import com.teamsimplyrs.prismaarcanum.screen.slot.SpellHologramSlot;
import com.teamsimplyrs.prismaarcanum.screen.slot.WandSlot;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.slf4j.Logger;

public class SpellNexusMenu extends AbstractContainerMenu {

    public final SpellNexusBlockEntity blockEntity;
    private final Level level;
    private final ContainerData data;

    private static final Logger LOGGER = LogUtils.getLogger();

    protected SpellNexusMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public SpellNexusMenu(int pContainerId, Inventory inv, BlockEntity blockEntity, ContainerData data)
    {
        super(PAMenuTypes.SPELL_NEXUS_MENU.get(), pContainerId);
        checkContainerSize(inv, 1);

        this.blockEntity = ((SpellNexusBlockEntity) blockEntity);
        this.level = inv.player.level();
        this.data = data;



        this.blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(iItemHandler -> {
            this.addSlot(new WandSlot(iItemHandler, 0, 80, 40));
            this.addSlot(new SpellHologramSlot(iItemHandler, 1, 80, 40-46));
            this.addSlot(new SpellHologramSlot(iItemHandler, 2, 80+48, 43));
            this.addSlot(new SpellHologramSlot(iItemHandler, 3, 80, 40+51));
            this.addSlot(new SpellHologramSlot(iItemHandler, 4, 80-48, 43));
        });
        addPlayerInventory(inv);
        addPlayerHotbar(inv);


        addDataSlots(data);
    }

    // Quick Move Stack: Shift-clicking items in and out of Block Entity Inventory. Copy-pasted from source, don't pay much mind.
    private static final int HOTBAR_SLOT_COUNT = 9;
    private static final int PLAYER_INVENTORY_ROW_COUNT = 3;
    private static final int PLAYER_INVENTORY_COLUMN_COUNT = 9;
    private static final int PLAYER_INVENTORY_SLOT_COUNT = PLAYER_INVENTORY_COLUMN_COUNT * PLAYER_INVENTORY_ROW_COUNT;
    private static final int VANILLA_SLOT_COUNT = HOTBAR_SLOT_COUNT + PLAYER_INVENTORY_SLOT_COUNT;
    private static final int VANILLA_FIRST_SLOT_INDEX = 5;
    private static final int TE_INVENTORY_FIRST_SLOT_INDEX = 0;

    // THIS YOU HAVE TO DEFINE!
    private static final int TE_INVENTORY_SLOT_COUNT = 5;  // must be the number of slots you have!
    @Override
    public ItemStack quickMoveStack(Player playerIn, int pIndex) {
        Slot sourceSlot = slots.get(pIndex);
        if (sourceSlot == null || !sourceSlot.hasItem()) return ItemStack.EMPTY;  //EMPTY_ITEM
        ItemStack sourceStack = sourceSlot.getItem();
        ItemStack copyOfSourceStack = sourceStack.copy();
        // Check if the slot clicked is one of the vanilla container slots
        if (pIndex < TE_INVENTORY_FIRST_SLOT_INDEX + TE_INVENTORY_SLOT_COUNT) {
            // This is a TE slot so merge the stack into the players inventory
            if (!moveItemStackTo(sourceStack, VANILLA_FIRST_SLOT_INDEX, VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;
            }
        }
        else if (pIndex < VANILLA_FIRST_SLOT_INDEX + VANILLA_SLOT_COUNT) {
            // This is a vanilla container slot so merge the stack into the tile inventory
            if((this.slots.get(pIndex).getItem().getItem() instanceof AbstractSpellHologram && !this.slots.get(0).hasItem())){
                return ItemStack.EMPTY;
            }
            if (!moveItemStackTo(sourceStack, TE_INVENTORY_FIRST_SLOT_INDEX, TE_INVENTORY_FIRST_SLOT_INDEX
                    + TE_INVENTORY_SLOT_COUNT, false)) {
                return ItemStack.EMPTY;  // EMPTY_ITEM
            }
        }
        else {
            System.out.println("Invalid slotIndex:" + pIndex);
            return ItemStack.EMPTY;
        }
        // If stack size == 0 (the entire stack was moved) set slot contents to null
        if (sourceStack.getCount() == 0) {
            sourceSlot.set(ItemStack.EMPTY);
        } else {
            sourceSlot.setChanged();
        }
        sourceSlot.onTake(playerIn, sourceStack);
        return copyOfSourceStack;
    }

    // End of Quick Move Stack method

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    private void addPlayerInventory(Inventory pPlayerInv)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlot(new Slot(pPlayerInv, j + i*9 + 9, 8 + j*18, 136 + i*18));
            }
        }
    }

    private void addPlayerHotbar(Inventory pPlayerInv)
    {
        for (int i = 0; i < 9; i++)
        {
            this.addSlot(new Slot(pPlayerInv, i, 8 + i*18, 194));
        }
    }

    @Override
    public void slotsChanged(Container pContainer)
    {
        super.slotsChanged(pContainer);
    }

    public ItemStack getWandInSlot()
    {
        return this.slots.get(0).getItem();
    }

}
