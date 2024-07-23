/*==============================================================================
 = Class: MetallurgyTraitDuplication
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyTraitDevouring extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitDevouring() {
		super("devouring_trait", 0xFF575000);
		this.register("metallurgy.trait.devouring", "metallurgy.trait.devouring.tooltip");
	}

	@Override
	public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
		if (player instanceof EntityPlayer && target.getHealth() <= 0 && Math.random() < 0.5) {
			FoodStats foodStats = ((EntityPlayer) player).getFoodStats();
			foodStats.addStats(1, 0.5f);
		}
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
