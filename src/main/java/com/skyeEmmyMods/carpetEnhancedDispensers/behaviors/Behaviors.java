package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors;

import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors.FlameArrowBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Behaviors {
	public static Map<Item, List<GenericBehavior>> behaviorMap = new HashMap<>();

	public static final GenericBehavior FLAME_ARROW_BEHAVIOR = new FlameArrowBehavior();

	public static void addBehaviorToMap(Item item, GenericBehavior behavior) {
		if(behaviorMap.containsKey(item)) {
			behaviorMap.get(item).add(behavior);
		} else {
			behaviorMap.put(item, List.of(behavior));
		}
	}

	static {
		addBehaviorToMap(Items.ARROW, FLAME_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.SPECTRAL_ARROW, FLAME_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.TIPPED_ARROW, FLAME_ARROW_BEHAVIOR);
	}
}
