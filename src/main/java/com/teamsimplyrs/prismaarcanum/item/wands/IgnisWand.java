package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.item.interfaces.ICastingItem;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.spells.IgnisFireball;
import com.teamsimplyrs.prismaarcanum.registry.PASpellRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class IgnisWand extends AbstractWand implements ICastingItem {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String ITEM_NAME = "ignis_wand_tier1";

    public static final Properties ITEM_PROPERTIES = new Properties();

    public static List<SpellBase> listSpellsIgnis = new ArrayList<>();
    public String current_spell_name = "ignis_fireball";
    public final String WAND_ELEMENT = "ignis";
    private boolean isBeingUsed = false;

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
        return listSpellsIgnis.get((spellIndex+1) % listSpellsIgnis.size());
    }

    @Override
    public SpellBase getPreviousSpell(ItemStack stack) {
        return listSpellsIgnis.get((spellIndex-1) % listSpellsIgnis.size());
    }

    @Override
    public void selectNextSpell(ItemStack stack) {
        spellIndex = (spellIndex+1) % listSpellsIgnis.size();
    }

    @Override
    public void selectPreviousSpell(ItemStack stack) {
        spellIndex = (spellIndex-1) % listSpellsIgnis.size();
    }

    @Override
    public boolean selectSpell(ItemStack stack, int index) {
        return false;
    }


    @Override
    public boolean cast(Player caster) {
        this.currentAffinity = Math.max(this.currentAffinity - 100, 0);
        return true;
    }

    public boolean isBeingUsed()
    {
        return isBeingUsed;
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        pPlayer.awardStat(Stats.ITEM_USED.get(this));

        isBeingUsed = true;
        pPlayer.startUsingItem(pUsedHand);

        return this.cast(pPlayer) ? InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide()) : InteractionResultHolder.fail(itemStack);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide)
        {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
        }
        return super.useOn(pContext);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {

        IgnisFireball currentSpell = (IgnisFireball) PASpellRegistry.getSpell(current_spell_name);
        Player pPlayer = (Player) pLivingEntity;
        InteractionHand pUsedHand = pPlayer.getUsedItemHand();
        currentSpell.spellCast(pLevel,pPlayer,pUsedHand);

        pLevel.getNearestPlayer(pLivingEntity,1).awardStat(Stats.ITEM_USED.get(this));
        isBeingUsed = false;
        pLivingEntity.stopUsingItem();
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 75000;
    }

}
