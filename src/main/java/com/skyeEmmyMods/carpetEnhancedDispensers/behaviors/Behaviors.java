package com.skyeEmmyMods.carpetEnhancedDispensers.behaviors;

import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors.FlameArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors.InfinityArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors.PowerArrowBehavior;
import com.skyeEmmyMods.carpetEnhancedDispensers.behaviors.arrowBehaviors.PunchArrowBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Behaviors {
	public static Map<Item, List<GenericBehavior>> behaviorMap = new HashMap<>();

	public static final GenericBehavior FLAME_ARROW_BEHAVIOR = new FlameArrowBehavior();
	public static final GenericBehavior POWER_ARROW_BEHAVIOR = new PowerArrowBehavior();
	public static final GenericBehavior PUNCH_ARROW_BEHAVIOR = new PunchArrowBehavior();
	public static final GenericBehavior INFINITY_ARROW_BEHAVIOR = new InfinityArrowBehavior();

	public static void addBehaviorToMap(Item item, GenericBehavior behavior) {
		if(!behaviorMap.containsKey(item)) {
			behaviorMap.put(item, new ArrayList<>());
		}
		behaviorMap.get(item).add(behavior);
	}

	static {
		addBehaviorToMap(Items.ARROW, FLAME_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.SPECTRAL_ARROW, FLAME_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.TIPPED_ARROW, FLAME_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.ARROW, POWER_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.SPECTRAL_ARROW, POWER_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.TIPPED_ARROW, POWER_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.ARROW, PUNCH_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.SPECTRAL_ARROW, PUNCH_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.TIPPED_ARROW, PUNCH_ARROW_BEHAVIOR);
		addBehaviorToMap(Items.ARROW, INFINITY_ARROW_BEHAVIOR);
	}
}
