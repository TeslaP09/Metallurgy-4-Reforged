/*==============================================================================
 = Class: MetallurgyTraitDuplication
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitExplosive extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitExplosive() { //1/5 chance to cause an explosion on hit
		super("explosive_trait", 0xFF575000);
		this.register("metallurgy.trait.explosive", "metallurgy.trait.explosive.tooltip");
	}

	@Override
	public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) 
	{	
		if (player.world.isRemote) return;
		if (Math.random() < 0.21 && (player instanceof EntityPlayer && ((EntityPlayer) player).getCooledAttackStrength(1f) > 0.9))
			target.world.createExplosion(player, target.posX, target.posY + 1.0, target.posZ, 1, false);
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
