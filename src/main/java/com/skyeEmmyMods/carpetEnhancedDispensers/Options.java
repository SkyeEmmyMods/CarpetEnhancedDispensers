package com.skyeEmmyMods.carpetEnhancedDispensers;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class Options {
	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean flameArrowDispenser = false;

	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean powerArrowDispenser = false;

	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean punchArrowDispenser = false;
	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean infinityArrowDispenser = false;
	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean silkTouchTntDispenser = false;
	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean fortuneTntDispenser = false;
	@Rule(
			categories = { RuleCategory.DISPENSER, Main.ENHANCED_DISPENSER }
	)
	public static boolean lootingTntDispenser = false;

}
