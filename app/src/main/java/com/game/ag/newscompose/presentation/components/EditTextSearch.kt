package com.game.ag.newscompose.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.game.ag.newscompose.presentation.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTextSearch(viewModel: NewsViewModel = hiltViewModel()) {

    var txtSearchState by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {

        OutlinedTextField(
            value = txtSearchState,
            onValueChange = { newValue ->
                txtSearchState = newValue
                if (txtSearchState.trim().isNotEmpty()) {
                    viewModel.getNewsByName(newValue)
                }
            },
            placeholder = { Text(text = "Search ....") },

            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            },
            maxLines = 1,
            shape = RoundedCornerShape(23.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
    }
}