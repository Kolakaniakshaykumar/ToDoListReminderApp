package tessides3399241.todolistapp.Kolakaniakshaykumar

import android.content.Context



object TaskManagerPrefs {

    private const val PREF_NAME = "TASK_USER_CONFIG"
    private const val KEY_AUTH_STATE = "IS_LOGGED_IN"
    private const val KEY_DISPLAY_NAME = "TASK_USER_NAME"
    private const val KEY_CONTACT_EMAIL = "TASK_USER_EMAIL"

    fun setAuthStatus(context: Context, status: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_AUTH_STATE, status).apply()
    }

    fun isUserAuthenticated(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_AUTH_STATE, false)
    }

    fun saveDisplayName(context: Context, name: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_DISPLAY_NAME, name).apply()
    }

    fun fetchDisplayName(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_DISPLAY_NAME, "") ?: ""
    }

    fun storeEmail(context: Context, email: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_CONTACT_EMAIL, email).apply()
    }

    fun getEmail(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_CONTACT_EMAIL, "") ?: ""
    }
}
