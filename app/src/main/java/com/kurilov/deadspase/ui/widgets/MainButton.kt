package com.kurilov.deadspase.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MainButton(
    iconResource: Int,
    text: String,
    onClick: () -> Unit,
) {

    val cardHeight = 100.dp
    val cardWidth = 200.dp

    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(cardHeight)
            .width(cardWidth)
            .clickable { onClick.invoke() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = iconResource),
                contentDescription = text,
                tint = Color.Unspecified,
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = text
            )
        }
    }
}