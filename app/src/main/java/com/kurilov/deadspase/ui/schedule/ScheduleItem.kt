package com.kurilov.deadspase.ui.schedule

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.kurilov.deadspase.R
import com.kurilov.deadspase.data.db.entry.SchedulePair

@Composable
fun ScheduleItem(
    schedulePair: SchedulePair,
) {
    Card(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 15.dp).padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = schedulePair.type,
            )
            Text(
                modifier = Modifier.weight(5f),
                text = schedulePair.disc,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
            )
            IconButton(
                modifier = Modifier.weight(1f),
                onClick = { /*TODO*/ },
            ) {
                Icon(painter = painterResource(id = R.drawable.ic_clear), contentDescription = null)
            }
        }
        Row(modifier = Modifier.fillMaxWidth().padding(start = 15.dp).padding(top = 5.dp)) {
            Text(
                text = if (schedulePair.less != 0) schedulePair.less.toString() else "-", //todo to utils
                fontWeight = FontWeight.Bold,
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(schedulePair.build ?: "-") //todo to utils
                    Text(schedulePair.room ?: "-")
                }
                Text("time")
            }
        }
    }
}