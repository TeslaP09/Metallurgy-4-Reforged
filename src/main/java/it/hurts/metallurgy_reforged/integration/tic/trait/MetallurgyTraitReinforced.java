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
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class MetallurgyTraitReinforced extends AbstractTrait implements IMetallurgyTrait {

	public MetallurgyTraitReinforced()
	{
		super("reinforced_trait", 0xFF575000);
		this.register("metallurgy.trait.reinforced", "metallurgy.trait.reinforced.tooltip");
	}

	@SubscribeEvent
	public void onEntityHurtEvent(LivingHurtEvent event)
	{
		EntityLivingBase entity = event.getEntityLiving();
		if (!canBeApplied(entity))
			return;

		float absorbedDamage = event.getAmount() * 0.125F;
		ToolHelper.damageTool(entity.getHeldItemMainhand(), (int) (absorbedDamage * 2), entity);
		event.setAmount(event.getAmount() - absorbedDamage);

	}

	private boolean canBeApplied(EntityLivingBase entity) {
		return this.isToolWithTrait(entity.getHeldItemMainhand());
	}

	@Override
	public void register(String name, @Nullable String tooltip)
	{
		Utils.localizeEscapingCustomSequences(String.format(LOC_Name, name));
		if (tooltip != null)
			Utils.localizeEscapingCustomSequences(String.format(LOC_Name, tooltip));
	}

}
 