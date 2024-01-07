package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors;

import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;

public interface TntBehavior {

    void applyBehavior(TntEntity tnt, ItemStack item);
}
