package com.kurilov.deadspase.ui.schedule

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kurilov.deadspase.R
import com.kurilov.deadspase.data.api.ErrorResult
import com.kurilov.deadspase.data.api.PendingResult
import com.kurilov.deadspase.data.api.SuccessResult
import com.kurilov.deadspase.data.api.takeSuccess
import com.kurilov.deadspase.ui.widgets.DeadSpaceAppBar
import com.kurilov.deadspase.ui.widgets.SearchField
import java.text.SimpleDateFormat
import java.util.*

fun getShortDayName(day: Int): String {
    val c = Calendar.getInstance(Locale.getDefault())
    c[2011, 7, 1, 0, 0] = 0
    c.add(Calendar.DAY_OF_MONTH, day)
    return java.lang.String.format("%ta", c)
} //todo move

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    navigateBack: () -> Unit = {},
    viewModel: ScheduleViewModel,
) {

    val context = LocalContext.current

    val isRefreshing = remember { mutableStateOf(false) } //todo

    val group = viewModel.group.collectAsState()
    val schedule = viewModel.schedule.collectAsState()
    val isInternetSchedule = viewModel.isInternetSchedule.collectAsState()
    val isWeekRed = viewModel.isWeekRed.collectAsState()
    val selectedWeekDay = viewModel.selectedWeekDay.collectAsState() //todo del

    val dateFormat = SimpleDateFormat("dd MMMM", Locale.getDefault())

    val weekDays = remember {
        mutableStateOf(
            listOf(
                getShortDayName(0),
                getShortDayName(1),
                getShortDayName(2),
                getShortDayName(3),
                getShortDayName(4),
                getShortDayName(5),
                context.getString(R.string.off_the_schedule_grid),
            )
        )
    }

    LaunchedEffect(schedule.value) {
        when(val newSchedule = schedule.value) {
            is PendingResult -> { isRefreshing.value = true }
            is SuccessResult -> { isRefreshing.value = false }
            is ErrorResult -> {
                isRefreshing.value = false
                Toast.makeText(
                    context,
                    newSchedule.exception.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    Scaffold(
        topBar = {
            DeadSpaceAppBar(
                title = stringResource(id = R.string.schedule),
                onNavigationClick = { navigateBack.invoke() },
            )
        },
        content = { paddingValues ->
            SwipeRefresh(
                onRefresh = { viewModel.reloadSchedule() },
                state = rememberSwipeRefreshState(isRefreshing.value),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = paddingValues.calculateTopPadding())
                        .padding(horizontal = 10.dp)
                ) {

                    SearchField(
                        modifier = Modifier.padding(top = 10.dp),
                        value = group.value,
                        setValue = { viewModel.changeGroup(it) },
                        hint = stringResource(id = R.string.search_hint),
                        onSearch = { viewModel.onSearch() },
                    ) //todo focus
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(text = stringResource(id = R.string.ignore_lectures))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(text = stringResource(id = R.string.your_schedule))
                            Spacer(modifier = Modifier.width(10.dp))
                            Switch(
                                checked = !isInternetSchedule.value,
                                onCheckedChange = { viewModel.typeScheduleEdit() })
                        }
                        IconButton(onClick = { viewModel.deleteLocalSchedule() }) {
                            Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = null)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {

                        Text(text = dateFormat.format(Calendar.getInstance().time.time))
                        Button(
                            modifier = Modifier.width(130.dp),
                            onClick = { viewModel.changeWeek() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isWeekRed.value) Color.Red else Color.Blue
                            ),
                        ) {
                            Text(text = stringResource(id = if (isWeekRed.value) R.string.red_week else R.string.blue_week))
                        }
                        Text(text = stringResource(id = R.string.week))
                    }
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.secondary),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        itemsIndexed(weekDays.value) { index, weekDay ->
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { viewModel.selectWeekDay(index) },
                                text = weekDay,
                                textAlign = TextAlign.Center,
                                color = if (selectedWeekDay.value == index) Color.Red //todo to theme
                                    else MaterialTheme.colorScheme.onSecondary,
                            )
                        }
                    }
                    LazyColumn(
                        state = rememberLazyListState(),
                    ) {
                        items(schedule.value.takeSuccess() ?: emptyList()) { schedulePair ->
                            ScheduleItem(schedulePair = schedulePair)
                        }
                    }
                }
            }
        },
    )
}