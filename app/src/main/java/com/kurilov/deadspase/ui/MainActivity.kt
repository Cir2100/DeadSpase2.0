package com.kurilov.deadspase.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.ExperimentalComposeUiApi
import com.kurilov.deadspase.ui.navigation.AppNavGraph
import com.kurilov.deadspase.ui.theme.DeadSpaseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalComposeUiApi::class,
        ExperimentalAnimationApi::class,
        ExperimentalMaterial3Api::class,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeadSpaseTheme {
                Scaffold {
                    AppNavGraph()
                }
            }
        }
    }
}