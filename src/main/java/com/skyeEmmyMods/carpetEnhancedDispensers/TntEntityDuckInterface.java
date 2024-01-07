package com.skyeEmmyMods.carpetEnhancedDispensers;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.nbt.NbtList;

public interface TntEntityDuckInterface {

    void addEnchantment(Enchantment enchantment, int level);

    NbtList getEnchantmentNBT();
}
