package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.entity.projectile.FireballProjectile;
import com.teamsimplyrs.prismaarcanum.item.interfaces.ICastingItem;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
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

    private static final Logger LOG = LogManager.getLogger();
    public static final String ITEM_NAME = "ignis_wand_tier1";

    public static final Properties ITEM_PROPERTIES = new Properties();

    public static List<SpellBase> listSpellsIgnis = new ArrayList<>();
    public static boolean isBeingUsed = false;

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

    public boolean isBeingUsed(){
        return isBeingUsed;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);


        isBeingUsed = true;
        pPlayer.startUsingItem(pUsedHand);

        return InteractionResultHolder.sidedSuccess(itemStack, pLevel.isClientSide());
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide)
        {
            BlockPos posClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            spawnIgnisParticles(pContext, posClicked);
        }
        return super.useOn(pContext);
    }

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity, int pTimeCharged) {
        if (!pLevel.isClientSide)
        {
            FireballProjectile fireballProjectile = new FireballProjectile(pLivingEntity, pLevel);
            fireballProjectile.shootFromRotation(pLivingEntity, pLivingEntity.getXRot(), pLivingEntity.getYRot(), 0F, 1F, 1F);
            pLevel.addFreshEntity(fireballProjectile);
        }

        pLevel.getNearestPlayer(pLivingEntity,1).awardStat(Stats.ITEM_USED.get(this));
        isBeingUsed = false;
        pLivingEntity.stopUsingItem();
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 75000;
    }

    private void spawnIgnisParticles(UseOnContext pContext, BlockPos posClicked)
    {
        for (int i = 0; i < 360; i++)
        {
            if (i%20 == 0)
            {
//                pContext.getLevel().addParticle(PAParticles.IGNIS_PARTICLES.get(),
//                        posClicked.getX() + 0.5f, posClicked.getY() + 1, posClicked.getZ() + 0.5f,
//                        Math.cos(i) + 0.25f, 0.15f, Math.sin(i)*0.25f
//                        );
            }
        }
    }
}
