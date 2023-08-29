package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors;

import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;

public interface ArrowBehavior {
	void applyBehavior(ProjectileEntity arrow, ItemStack itemStack);
}
