package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors;

import net.minecraft.enchantment.Enchantment;

public class GenericBehavior {

	private int level;

	public Enchantment getEnchant() {
		return null;
	}

	public boolean isEnabled() {
		return false;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
