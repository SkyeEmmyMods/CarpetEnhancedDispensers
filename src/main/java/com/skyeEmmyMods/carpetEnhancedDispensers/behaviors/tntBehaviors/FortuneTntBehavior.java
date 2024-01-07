package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.tntBehaviors;

import com.skyeEmmyMods.carpetEnhancedDispensers.Options;
import com.skyeEmmyMods.carpetEnhancedDispensers.TntEntityDuckInterface;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.TntBehavior;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.TntEntity;
import net.minecraft.item.ItemStack;

public class FortuneTntBehavior extends GenericBehavior implements TntBehavior {
    @Override
    public Enchantment getEnchant() {
        return Enchantments.FORTUNE;
    }

    @Override
    public boolean isEnabled() {
        return Options.fortuneTntDispenser;
    }

    public void applyBehavior(TntEntity tnt, ItemStack item) {
        ((TntEntityDuckInterface) tnt).addEnchantment(this.getEnchant(), this.level);
    }
}
