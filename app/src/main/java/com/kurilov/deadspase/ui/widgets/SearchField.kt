package com.kurilov.deadspase.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import com.kurilov.deadspase.R


//todo suggestions and focusable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    setValue: (String) -> Unit,
    onSearch: () -> Unit,
    hint: String = "",
) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { setValue.invoke(it) },
        maxLines = 1,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                //modifier = Modifier.padding(15.dp),
            )
        },
        trailingIcon = {
            IconButton(onClick = { setValue.invoke("") }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clear),
                    contentDescription = null,
                    //modifier = Modifier.padding(15.dp),
                )
            }
        },
        label = {
            Text(
                text = hint,
                //style = MaterialTheme.typography.bodyLarge
            )
        },
        keyboardActions = KeyboardActions {
            keyboardController?.hide()
            onSearch.invoke()
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
    )
}