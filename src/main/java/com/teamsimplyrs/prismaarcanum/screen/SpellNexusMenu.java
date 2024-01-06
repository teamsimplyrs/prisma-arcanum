package com.teamsimplyrs.prismaarcanum.screen;

import com.teamsimplyrs.prismaarcanum.block.entity.SpellNexusBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.jetbrains.annotations.Nullable;

public class SpellNexusMenu extends AbstractContainerMenu {

//    public final SpellNexusBlockEntity blockEntity;
//    private final Level level;
//    private final ContainerData data;


    protected SpellNexusMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()), new SimpleContainerData(2));
    }

    public SpellNexusMenu(int pContainerId, Inventory inv, BlockEntity blockEntity, ContainerData data)
    {
        super(null, pContainerId);
        checkContainerSize(inv, 2);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int i) {
        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        return false;
    }
}
