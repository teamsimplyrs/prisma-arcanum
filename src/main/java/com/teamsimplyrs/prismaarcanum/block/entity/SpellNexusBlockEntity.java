package com.teamsimplyrs.prismaarcanum.block.entity;

import com.teamsimplyrs.prismaarcanum.item.spells.spellholograms.AbstractSpellHologram;
import com.teamsimplyrs.prismaarcanum.item.wands.AbstractWand;
import com.teamsimplyrs.prismaarcanum.registry.PABlockEntityRegistry;
import com.teamsimplyrs.prismaarcanum.screen.SpellNexusMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpellNexusBlockEntity extends BlockEntity implements MenuProvider {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String BE_NAME = "spell_nexus_be";
    private final ItemStackHandler itemHandler = new ItemStackHandler(8) {
        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            if(slot==0){
                return stack.getItem() instanceof AbstractWand;
            }
            return stack.getItem() instanceof AbstractSpellHologram && getStackInSlot(0).getItem() instanceof AbstractWand;
        }

        @Override
        public int getSlotLimit(int slot)
        {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();

            if (!level.isClientSide())
            {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };
    private static final int INPUT_SLOT_WAND = 0;
    private static final int INPUT_SLOT_SPELL_1 = 1, INPUT_SLOT_SPELL_2 = 2, INPUT_SLOT_SPELL_3 = 3,
    INPUT_SLOT_SPELL_4 = 4, INPUT_SLOT_SPELL_5 = 5, INPUT_SLOT_SPELL_6 = 6, INPUT_SLOT_SPELL_7 = 7;

    public static final int PLAYER_INV_SIZE = 36;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;

    public SpellNexusBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(PABlockEntityRegistry.SPELL_NEXUS_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int i) {
                return 0;
            }

            @Override
            public void set(int i, int i1) {

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("block.prismaarcanum.spell_nexus");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player)
    {
        return new SpellNexusMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }


    // All block logic for spell infusion/processing goes here
    public void tick(Level pLevel, BlockPos pPos, BlockState pState1) {


    }

    // Method for BlockEntityRenderer that returns the wand in the Spell Nexus' wand slot
    public ItemStack getWandRenderStack()
    {
        return itemHandler.getStackInSlot(INPUT_SLOT_WAND).isEmpty() ? ItemStack.EMPTY : itemHandler.getStackInSlot(INPUT_SLOT_WAND);
    }
    public ItemStack getSpellHologramRenderStack(int spellSlotIndex)
    {
        return itemHandler.getStackInSlot(spellSlotIndex).isEmpty() ? ItemStack.EMPTY : itemHandler.getStackInSlot(spellSlotIndex);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
