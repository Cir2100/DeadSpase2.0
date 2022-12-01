package com.kurilov.deadspase.ui.schedule

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kurilov.deadspase.R
import com.kurilov.deadspase.data.api.ErrorResult
import com.kurilov.deadspase.data.api.PendingResult
import com.kurilov.deadspase.data.api.SuccessResult
import com.kurilov.deadspase.ui.widgets.DeadSpaceAppBar
import com.kurilov.deadspase.ui.widgets.SearchField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    navigateBack: () -> Unit = {},
    viewModel: ScheduleViewModel,
) {

    val scrollState = rememberLazyListState()

    val group = viewModel.group.collectAsState()
    val schedule = viewModel.schedule.collectAsState()
    val isInternetSchedule = viewModel.isInternetSchedule.collectAsState()
    val isWeekRed = viewModel.isWeekRed.collectAsState()
    when(val s = schedule.value) {
        is PendingResult -> Log.d("Fuck","PendingResult Schedule")
        is SuccessResult -> Log.d("Fuck","SuccesResult Schedule = ${s.data}")
        is ErrorResult -> Log.d("Fuck","ErrorResult Schedule = ${s.exception}")
    }


    Scaffold(
        topBar = {
            DeadSpaceAppBar(
                title = stringResource(id = R.string.schedule),
                onNavigationClick = { navigateBack.invoke() },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding())
                    .padding(horizontal = 10.dp),
            ) {
                SearchField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = group.value,
                    setValue = { viewModel.changeGroup(it) },
                    hint = stringResource(id = R.string.search_hint),
                    onSearch = { viewModel.onSearch() },
                )
                LazyColumn(
                    state = scrollState,
                ) {

                }
            }
        },
    )
}