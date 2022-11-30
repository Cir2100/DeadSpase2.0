package com.kurilov.deadspase.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kurilov.deadspase.ui.deadline.DeadlineScreen
import com.kurilov.deadspase.ui.exam.ExamScreen
import com.kurilov.deadspase.ui.main.MainScreen
import com.kurilov.deadspase.ui.schedule.ScheduleScreen

sealed class AppDestinations(val route: String) {
    object Main: AppDestinations("main")
    object Deadline: AppDestinations("deadline")
    object Schedule: AppDestinations("schedule")
    object Exam: AppDestinations("exam")
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun AppNavGraph() { //todo animation

    val navHostController = rememberNavController()

   NavHost(
        modifier = Modifier
            .fillMaxSize(),
        navController = navHostController,
        startDestination = AppDestinations.Main.route,
    ) {
        composable(
            AppDestinations.Main.route,
        ) {
            MainScreen(
                navigateToDeadline = { navHostController.navigate(AppDestinations.Deadline.route) },
                navigateToSchedule = { navHostController.navigate(AppDestinations.Schedule.route) },
                navigateToExam = { navHostController.navigate(AppDestinations.Exam.route) },
            )
        }
        composable(
            AppDestinations.Schedule.route,
        ) {
            ScheduleScreen(
                navigateBack = { navHostController.navigateUp() },
                viewModel = hiltViewModel(),
            )
        }
        composable(
            AppDestinations.Exam.route,
        ) {
            ExamScreen(
                navController = navHostController,
            )
        }
        composable(
            AppDestinations.Deadline.route,
        ) {
            DeadlineScreen(
                navController = navHostController,
            )
        }
    }
}