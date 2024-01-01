package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.item.interfaces.ICastingItem;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class IgnisWand extends AbstractWand implements ICastingItem {

    private static final Logger LOG = LogManager.getLogger();
    public static final String ITEM_NAME = "ignis_wand_tier1";

    public static final Properties ITEM_PROPERTIES = new Properties();

    public static List<SpellBase> listSpellsIgnis = new ArrayList<>();

    private static int spellIndex = 0;

    public IgnisWand() {
        super(ITEM_PROPERTIES);
    }


    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        return true;
    }

    @Override
    public SpellBase getCurrentSpell(ItemStack stack) {
        return listSpellsIgnis.get(spellIndex);
    }

    @Override
    public SpellBase getNextSpell(ItemStack stack) {
        return listSpellsIgnis.get((spellIndex+1)%listSpellsIgnis.size());
    }

    @Override
    public SpellBase getPreviousSpell(ItemStack stack) {
        return listSpellsIgnis.get((spellIndex-1)%listSpellsIgnis.size());
    }

    @Override
    public void selectNextSpell(ItemStack stack) {
        spellIndex = (spellIndex+1)%listSpellsIgnis.size();
    }

    @Override
    public void selectPreviousSpell(ItemStack stack) {

    }

    @Override
    public boolean selectSpell(ItemStack stack, int index) {
        return false;
    }

    @Override
    public boolean displayHUD(ItemStack stack, Player player) {
        return false;
    }

    @Override
    public boolean cast(ItemStack stack, SpellBase spell, Player caster) {
        return false;
    }
}
