package utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import java.io.IOException

fun getTranslationFromResourcePath(lang: String?, classLoader: ClassLoader): MutableMap<String, String> {
    lang ?: return mutableMapOf()
    val langFile = classLoader.getResource("assets/carpet-enhanced-dispensers/lang/$lang.json")
    langFile ?: return mutableMapOf()
    val jsonData: String = try {
        langFile.readText()
    } catch (e: IOException) {
        return mutableMapOf()
    }
    return Json.decodeFromJsonElement<Map<String, String>>(Json.parseToJsonElement(jsonData)).toMutableMap()
}