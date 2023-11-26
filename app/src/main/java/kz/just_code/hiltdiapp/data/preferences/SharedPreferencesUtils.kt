package kz.just_code.hiltdiapp.data.preferences

import android.content.SharedPreferences

interface SharedPreferencesUtils {
    fun saveString(key: Preferences, value: String)
    fun saveInt(key: Preferences, value: Int)

    fun getString(key: Preferences): String
    fun getInt(key: Preferences): Int
}

enum class Preferences {
    NAME, PHONE, EMAIL
}

class SharedPreferencesUtilsImpl(
    private val preferences: SharedPreferences
): SharedPreferencesUtils {
    override fun saveString(key: Preferences, value: String) {
        val editor = preferences.edit()
        editor.putString(key.name, value)
        editor.apply()
    }

    override fun saveInt(key: Preferences, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key.name, value)
        editor.apply()
    }

    override fun getString(key: Preferences): String {
        return preferences.getString(key.name, "").orEmpty()
    }

    override fun getInt(key: Preferences): Int {
        return preferences.getInt(key.name, 0)
    }
}