package com.teamsimplyrs.prismaarcanum.block;

import com.teamsimplyrs.prismaarcanum.block.entity.SpellNexusBlockEntity;
import com.teamsimplyrs.prismaarcanum.block.voxelshapes.PAVoxelShapes;
import com.teamsimplyrs.prismaarcanum.registry.PABlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class SpellNexus extends BaseEntityBlock {

    public static final String BLOCK_NAME = "spell_nexus";
    public static final Properties BLOCK_PROPERTIES = Properties.copy(Blocks.IRON_BLOCK).strength(0.6f).dynamicShape().noOcclusion();

    public SpellNexus(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return PAVoxelShapes.SPELL_NEXUS_SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState)
    {
        return new SpellNexusBlockEntity(blockPos, blockState);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock())
        {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SpellNexusBlockEntity)
            {
                ((SpellNexusBlockEntity) blockEntity).drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        if (!pLevel.isClientSide())
        {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof SpellNexusBlockEntity)
            {
                NetworkHooks.openScreen((ServerPlayer) pPlayer, (SpellNexusBlockEntity) blockEntity, pPos);
            } else {
                throw new IllegalStateException("[ERROR] Spell Nexus: Container provider missing");
            }
        }

        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide())
        {
            return null;
        }

        return createTickerHelper(pBlockEntityType, PABlockEntityRegistry.SPELL_NEXUS_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }
}
