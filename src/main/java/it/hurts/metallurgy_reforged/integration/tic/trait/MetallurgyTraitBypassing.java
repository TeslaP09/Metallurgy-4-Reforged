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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTraitLeveled;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class MetallurgyTraitBypassing extends AbstractTraitLeveled implements IMetallurgyTrait {

	public MetallurgyTraitBypassing(int level) { // +10% damage per level will ignore armor and resistance
		super("bypassing_trait", 0xFF575000, 2, level);
		this.register("metallurgy.trait.bypassing", "metallurgy.trait.bypassing.tooltip");
	}

	@SubscribeEvent
	public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
	        NBTTagList tagList = TagUtil.getModifiersTagList(player.getHeldItemMainhand());
	        int index = TinkerUtil.getIndexInCompoundList(tagList, this.getModifierIdentifier());	

	        int level = 0;

	        if (index > -1) {
	            ModifierNBT modifier = ModifierNBT.readTag(tagList.getCompoundTagAt(index));
	            level = modifier.level;
	        }  
			
			float f1 = target.getHealth();
			float bypassedDamage = newDamage * level * 0.1f;
			
			target.getCombatTracker().trackDamage(DamageSource.causeMobDamage(player), f1, bypassedDamage);
			target.setHealth(f1 - bypassedDamage);
			target.setAbsorptionAmount(target.getAbsorptionAmount() - bypassedDamage);

			System.out.println("bypassing level: " + level);
			System.out.println("bypassed damage: " + bypassedDamage);
			return newDamage - bypassedDamage;
	}

	@Override
	public int getPriority() {
		return Integer.MAX_VALUE / 2;
	}

	@Override
	public void register(String name, @Nullable String tooltip) {
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
