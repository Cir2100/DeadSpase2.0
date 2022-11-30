package com.kurilov.deadspase.ui.widgets

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.kurilov.deadspase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeadSpaceAppBar(
    title: String,
    isNavigation: Boolean = true,
    onNavigationClick: (() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (isNavigation) {
                IconButton(onClick = { onNavigationClick?.invoke() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null,
                        tint = Color.Unspecified,
                    )
                }
            }
        },
    )
}