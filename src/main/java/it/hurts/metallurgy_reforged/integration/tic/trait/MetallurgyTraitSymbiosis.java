/*==============================================================================
 = Class: MetallurgyTraitDecay
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import javax.annotation.Nullable;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class MetallurgyTraitSymbiosis extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitSymbiosis() {
		super("symbiosis_trait", 0xFF575000);
		this.register("metallurgy.trait.symbiosis", "metallurgy.trait.symbiosis.tooltip");
	}

	@Override
	public void onUpdate(ItemStack tool, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (worldIn.isRemote)
			return;

		if (entityIn instanceof EntityPlayer) {
			EntityPlayer player = ((EntityPlayer) entityIn);
			float foodLevelPercentage = player.getFoodStats().getFoodLevel() / 20F;

			if (foodLevelPercentage < 0.85) {
				int secondsWait = (1 + MathHelper.floor(8 * foodLevelPercentage)) * 20;

				if (player.ticksExisted % secondsWait == 0) {
					ToolHelper.damageTool(tool, 3 + random.nextInt(4), player);
				}
			}
		}
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}
	
	@Override
	public boolean isToolWithTrait(ItemStack itemStack) {
		return super.isToolWithTrait(itemStack);
	}
}
