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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class MetallurgyTraitPhoenix extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitPhoenix() 
	{
		super("phoenix_trait", 0xFF575000);
		this.register("metallurgy.trait.phoenix", "metallurgy.trait.phoenix.tooltip");
	}

	  @Override
	  public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
	    NBTTagCompound comp = tool.getOrCreateSubCompound(this.getModifierIdentifier());
	    if (comp.getBoolean("AttemptedRepair") || world.isRemote || !ToolHelper.isBroken(tool)) {
	    	return;
	    }
	    if (Math.random() > 0.5) {
	    	ToolHelper.unbreakTool(tool);
	    	ToolHelper.healTool(tool, 100, (EntityLivingBase) entity);
	    } else {
	      comp.setBoolean("AttemptedRepair", true);
	    }
	  }

	  @Override
	  public void onRepair(ItemStack tool, int amount) {
	    NBTTagCompound comp = tool.getOrCreateSubCompound(this.getModifierIdentifier());
	    comp.setBoolean("AttemptedRepair", false);
	  }

	@Override
	public void register(String name, @Nullable String tooltip) 
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
