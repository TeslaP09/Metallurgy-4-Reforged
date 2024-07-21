/*==============================================================================
 = Class: MetallurgyTraitDecay
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/

package it.hurts.metallurgy_reforged.integration.tic.trait;

import it.hurts.metallurgy_reforged.util.Utils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import javax.annotation.Nullable;

public class MetallurgyTraitAdaptable extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitAdaptable() 
	{
		super("adaptable_trait", 0xFF575000);
		this.register("metallurgy.trait.adaptable", "metallurgy.trait.adaptable.tooltip");
	}

	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		if (tool.getItemDamage() <= (tool.getMaxDamage() * 0.2)) {
			return newDamage * 1.25f;
		}
		return newDamage;
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) 
	{
		if (tool.getItemDamage() <= (tool.getMaxDamage() * 0.2)) {
			event.setNewSpeed(event.getNewSpeed() * 1.25f);
		}
	}

	@Override
	public void register(String name, @Nullable String tooltip) 
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
