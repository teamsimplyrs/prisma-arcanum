package com.teamsimplyrs.prismaarcanum.item.wands;

import com.teamsimplyrs.prismaarcanum.client.vfx.AquaVFX;
import com.teamsimplyrs.prismaarcanum.item.interfaces.ICastingItem;
import com.teamsimplyrs.prismaarcanum.item.spells.SpellBase;
import com.teamsimplyrs.prismaarcanum.item.spells.vecto.AquaDashSpell;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AquaWand extends AbstractWand implements ICastingItem {
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String ITEM_NAME = "aqua_wand_tier1";

    public static final Properties ITEM_PROPERTIES = new Properties();

    private static List<SpellBase> listSpellsAqua = new ArrayList<>();
    private boolean isBeingUsed = false;
    public static final String WAND_ELEMENT = "aqua";

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        LOGGER.info("water wand used");
        // Use the wand only on client for debugging
        if (pLevel.isClientSide) {
//            // Check if the Aqua Dash is not already active
//            if (!AquaDashSpell.AquaDashHandler.isDashing()) {
//                AquaDashSpell.AquaDashHandler.startDash();
//                LOGGER.info("Aqua Dash Activated!");
//            } else {
//                LOGGER.info("Aqua Dash is already active!");
//            }

            AquaVFX.BasicWaterSurroundFX();
        }
        // Apply a cooldown so the spell can't be triggered again during the dash
        pPlayer.getCooldowns().addCooldown(this, 40);
        return InteractionResultHolder.success(pPlayer.getItemInHand(pUsedHand));
    }

    @Override
    public SpellBase getCurrentSpell(ItemStack stack) {
        return null;
    }

    @Override
    public SpellBase getNextSpell(ItemStack stack) {
        return null;
    }

    @Override
    public SpellBase getPreviousSpell(ItemStack stack) {
        return null;
    }

    @Override
    public void selectNextSpell(ItemStack stack) {

    }

    @Override
    public void selectPreviousSpell(ItemStack stack) {

    }

    @Override
    public boolean selectSpell(ItemStack stack, int index) {
        return false;
    }


    @Override
    public boolean cast(Player caster) {
        return false;
    }
}
