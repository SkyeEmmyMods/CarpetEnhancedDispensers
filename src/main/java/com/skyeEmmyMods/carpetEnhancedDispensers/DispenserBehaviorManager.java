package com.skyeEmmyMods.carpetEnhancedDispensers;

import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.Behaviors;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.GenericBehavior;
import net.minecraft.enchantment.Enchantment;
import static net.minecraft.enchantment.EnchantmentHelper.*;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;

import java.util.List;
import java.util.Map;

public class DispenserBehaviorManager {
    private final Map<Enchantment, Integer> enchantments;

    public DispenserBehaviorManager(NbtList enchantments) {
        this.enchantments = fromNbt(enchantments);
    }

    public List<GenericBehavior> getApplicableBehaviors(ItemStack itemStack) {
        Item item = itemStack.getItem();
        List<GenericBehavior> behaviors = List.copyOf(Behaviors.behaviorMap.get(item).stream().filter(
                behavior -> enchantments.containsKey(behavior.getEnchant()) && behavior.isEnabled()
        ).toList());
        behaviors.forEach(
                behavior -> behavior.setLevel(enchantments.get(behavior.getEnchant()))
        );
        return behaviors;
    }
}
