expect class Settings {

    fun getLocale(): String?
    fun setLocale(locale: String)
    fun getString(key: String): String?
    fun setString(key: String, value: String)
}