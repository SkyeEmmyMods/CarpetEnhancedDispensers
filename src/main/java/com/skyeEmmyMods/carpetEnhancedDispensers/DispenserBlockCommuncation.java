package com.skyeEmmyMods.carpetEnhancedDispensers;

import net.minecraft.nbt.NbtList;

public interface DispenserBlockCommuncation {

	NbtList getEnchantments();

	void setEnchantments(NbtList enchantments);

}
