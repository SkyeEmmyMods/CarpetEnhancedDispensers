package com.skyeEmmyMods.carpetEnhancedDispensers;

import net.minecraft.nbt.NbtList;

public interface DispenserBlockDuckInterface {

	NbtList getEnchantments();

	void setEnchantments(NbtList enchantments);
	
	int getRepairCost();
	
	void setRepairCost(int cost);

	DispenserBehaviorManager getDispenserBehaviorManager();

}
