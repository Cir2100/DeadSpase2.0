package com.kurilov.deadspase.ui.schedule

import android.content.Context
import androidx.lifecycle.ViewModel
import com.kurilov.deadspase.data.preferenses.AppSharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val context: Context, //todo
): ViewModel() {

    val group = MutableStateFlow("")

    init {
        group.value = AppSharedPreferences.getGroup(context)
    }

    fun changeGroup(group: String) {
        this.group.value = group
    }

    fun onSearch() {
        saveGroup()
    }

    private fun saveGroup() {
        AppSharedPreferences.setGroup(context, group.value)
    }

}