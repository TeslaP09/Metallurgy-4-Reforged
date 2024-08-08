package it.hurts.metallurgy_reforged.integration.tic;

import it.hurts.metallurgy_reforged.integration.tic.trait.MetallurgyTinkerTraits;
import net.minecraft.item.ItemStack;

public class TICCompatSafe {
	protected static boolean tconLoaded = false;

	public static boolean isToolWithSymbiosis(ItemStack stack) {
		if (!tconLoaded) {
			return false;
		}
		return isToolWithSymbiosisUnsafe(stack);
	}
	
	private static boolean isToolWithSymbiosisUnsafe(ItemStack stack) {
		return MetallurgyTinkerTraits.symbiosisTrait.isToolWithTrait(stack);
	}
}
