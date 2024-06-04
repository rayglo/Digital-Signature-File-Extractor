import java.util.prefs.Preferences

actual class Settings {

    private val preferences = Preferences.userRoot().node("extractor_settings")

    actual fun getLocale(): String? {
        return preferences.get("locale", null)
    }

    actual fun setLocale(locale: String) {
        preferences.put("locale", locale)
    }

    actual fun getString(key: String): String? {
        return preferences.get(key, null)
    }

    actual fun setString(key: String, value: String) {
        preferences.put(key, value)
    }

}