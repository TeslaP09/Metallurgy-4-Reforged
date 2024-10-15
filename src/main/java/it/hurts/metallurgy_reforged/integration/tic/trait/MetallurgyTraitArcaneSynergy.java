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
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class MetallurgyTraitArcaneSynergy extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitArcaneSynergy() {
		super("arcanesynergy_trait", 0xFF575000);
		this.register("metallurgy.trait.arcanesynergy", "metallurgy.trait.arcanesynergy.tooltip");
	}

	public int getPieces(EntityLivingBase entity) {
        int amount = 0;
        for (ItemStack armorPiece : entity.getArmorInventoryList())
        {
            if (!EnchantmentHelper.getEnchantments(armorPiece).isEmpty())
                amount++;
        }
        return amount;
	}
	
	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		float bonus = getPieces(event.getEntityPlayer()) * 0.1f;
		event.setNewSpeed(event.getNewSpeed() * (1 + bonus));
	}

	@Override
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
		float bonus = getPieces(player) * 0.1f;
		return newDamage * (1 + bonus);
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
