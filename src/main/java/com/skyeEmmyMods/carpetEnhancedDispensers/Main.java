package com.skyeEmmyMods.carpetEnhancedDispensers;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.skyeEmmyMods.carpetEnhancedDispensers.utils.LootTableHandling;
import net.fabricmc.api.ModInitializer;
import utils.UtilsKt;

import java.util.Map;

public class Main implements ModInitializer, CarpetExtension {

	public static final String ENHANCED_DISPENSER = "enhanced-dispenser";

	@Override
	public void onInitialize() {
		CarpetServer.manageExtension(this);
	}

	@Override
	public String version() {
		return "Carpet-Enhanced-Dispensers";
	}

	@Override
	public Map<String, String> canHasTranslations(String lang) {
		return UtilsKt.getTranslationFromResourcePath(lang, this.getClass().getClassLoader());
	}

	@Override
	public void onGameStarted() {
		CarpetServer.settingsManager.parseSettingsClass(Options.class);
		LootTableHandling.registerDispenserCopyEnchants();
	}
}
