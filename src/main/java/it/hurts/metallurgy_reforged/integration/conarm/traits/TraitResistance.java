/*==============================================================================
 = Class: TraitResistance
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2020.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.conarm.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import it.hurts.metallurgy_reforged.integration.conarm.MetallurgyConArmorStats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitResistance extends AbstractArmorTrait implements IConarmMetallurgyTrait {

	public TraitResistance() //gives player resistance 1 if at least one piece is worn, gives resistance 2 if all four pieces are worn
	{
		super("resistance", TextFormatting.GREEN);
	}

    int level = 0;

    @SubscribeEvent
    public void onArmorTick(PlayerTickEvent event)
    {
        int amount = getTotalPieces(event.player);
        if (amount  > 0)
            event.player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 40, amount < 4 ? 0 : 1, false, false));
    }

    private int getTotalPieces(EntityPlayer player)
    {
        int amount = 0;
        for (ItemStack armorPiece : player.getArmorInventoryList())
        {
            if (this.isToolWithTrait(armorPiece) && !ToolHelper.isBroken(armorPiece))
                amount++;
        }

        return amount;
    }
}