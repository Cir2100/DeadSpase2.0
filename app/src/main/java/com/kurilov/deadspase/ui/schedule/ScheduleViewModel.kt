package com.kurilov.deadspase.ui.schedule

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurilov.deadspase.data.api.PendingResult
import com.kurilov.deadspase.data.preferenses.AppSharedPreferences
import com.kurilov.deadspase.data.repositories.schedule.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val context: Context, //todo
    private val scheduleRepository: ScheduleRepository,
): ViewModel() {

    val group = MutableStateFlow("")
    val isWeekRed = MutableStateFlow(false)
    val isInternetSchedule = MutableStateFlow(true)
    val selectedWeekDay = MutableStateFlow(0)
    val schedule = scheduleRepository.schedule.map { schedule ->
        schedule.map { list -> list.sortedBy { it.less } }
    }.distinctUntilChanged()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), PendingResult())

    init {
        group.value = AppSharedPreferences.getGroup(context)
        isWeekRed.value = scheduleRepository.isWeekRed.value
        isInternetSchedule.value = AppSharedPreferences.getIsInternetSchedule(context)
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.init()
            getSchedule()
        }
    }

    fun changeGroup(group: String) {
        this.group.value = group
    }

    fun selectWeekDay(index: Int) {
        selectedWeekDay.value = index
        getSchedule()
    }

    fun changeWeek() {
        isWeekRed.value = !isWeekRed.value
        getSchedule()
    }

    fun onSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            saveGroup()
            loadInternetSchedule()
        }
    }

    fun reloadSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            setIsInternetSchedule(true)
            loadInternetSchedule()
        }
    }

    fun typeScheduleEdit() {
        viewModelScope.launch(Dispatchers.IO) {
            setIsInternetSchedule(!isInternetSchedule.value)
            getSchedule()
        }
    }

    fun deleteLocalSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            setIsInternetSchedule(true)
            //todo
        }
    }

    private fun loadInternetSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.getSchedule(
                name = group.value,
                week = isWeekRed.value,
                day = selectedWeekDay.value,
                isInternet = true,
                isForceLoad = true,
            )
        }
    }

    private fun getSchedule() {
        viewModelScope.launch(Dispatchers.IO) {
            scheduleRepository.getSchedule(
                name = group.value,
                week = isWeekRed.value,
                day = selectedWeekDay.value,
                isInternet = isInternetSchedule.value,
            )
        }
    }

    private fun saveGroup() {
        AppSharedPreferences.setGroup(context, group.value)
    }

    private fun setIsInternetSchedule(isInternetSchedule: Boolean) {
        this.isInternetSchedule.value = isInternetSchedule
        AppSharedPreferences.setIsInternetSchedule(context, isInternetSchedule)
    }

}