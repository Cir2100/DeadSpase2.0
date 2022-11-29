package com.kurilov.deadspase.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kurilov.deadspase.R
import com.kurilov.deadspase.ui.widgets.MainButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigateToDeadline: () -> Unit,
    navigateToSchedule: () -> Unit,
    navigateToExam: () -> Unit,
) {

    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(stringResource(id = R.string.main_title))
                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = paddingValues.calculateTopPadding()),
                state = scrollState,
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)//todo
                            .clickable { navigateToSchedule.invoke() }
                    ) {  }
                }
                item {
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        MainButton(
                            iconResource = R.drawable.ic_deadline,
                            text = stringResource(id = R.string.deadline),
                        ) { navigateToDeadline.invoke() }
                        MainButton(
                            iconResource = R.drawable.ic_schedule,
                            text = stringResource(id = R.string.schedule),
                        ) { navigateToSchedule.invoke() }
                        MainButton(
                            iconResource = R.drawable.ic_exam,
                            text = stringResource(id = R.string.exam),
                        ) { navigateToExam.invoke() }
                    }
                }
            }
        },
    )
}