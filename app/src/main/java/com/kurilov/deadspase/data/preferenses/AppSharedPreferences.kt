package com.kurilov.deadspase.data.preferenses

import android.content.Context
import androidx.core.content.edit

object AppSharedPreferences {

    private const val PREFS_TAG = "DeadSapcePreferences"
    private const val KEY_GROUP = "group"

    fun getGroup(context: Context): String {
        val sPrefs =
            context.applicationContext.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE)
        return sPrefs.getString(KEY_GROUP, "") ?: ""
    }

    fun setGroup(context: Context, group: String) {
        val sPrefs =
            context.applicationContext.getSharedPreferences(PREFS_TAG, Context.MODE_PRIVATE)
        sPrefs.edit {
            putString(KEY_GROUP, group)
        }
    }
}