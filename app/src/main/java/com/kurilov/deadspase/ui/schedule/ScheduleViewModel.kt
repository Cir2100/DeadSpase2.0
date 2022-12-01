package com.kurilov.deadspase.ui.schedule

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurilov.deadspase.data.preferenses.AppSharedPreferences
import com.kurilov.deadspase.data.repositories.schedule.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val context: Context, //todo
    private val scheduleRepository: ScheduleRepository,
): ViewModel() {

    val group = MutableStateFlow("")
    val isWeekRed = scheduleRepository.isWeekRed
    val schedule = scheduleRepository.schedule
    val isInternetSchedule = MutableStateFlow(false)

    init {
        group.value = AppSharedPreferences.getGroup(context)
        isInternetSchedule.value = AppSharedPreferences.getIsInternetSchedule(context)
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.init()
            scheduleRepository.getSchedule(
                name = group.value,
                isInternet = isInternetSchedule.value,
            )
        }
    }

    fun changeGroup(group: String) {
        this.group.value = group
    }

    fun onSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            saveGroup()
            loadSchedule()
        }
    }

    fun typeScheduleEdit() {
        isInternetSchedule.value = !isInternetSchedule.value
        saveIsInternetSchedule()
    }

    private suspend fun loadSchedule() {
        scheduleRepository.getSchedule(
            name = group.value,
            isInternet = true,
            isForceLoad = true,
        )
    }

    private fun saveGroup() {
        AppSharedPreferences.setGroup(context, group.value)
    }

    private fun saveIsInternetSchedule() {
        AppSharedPreferences.setIsInternetSchedule(context, isInternetSchedule.value)
    }

}