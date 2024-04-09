package com.game.ag.newscompose.presentation.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField() {

    var txtSearch by remember {
        mutableStateOf("")
    }

    Box (modifier =Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        OutlinedTextField(
            value = txtSearch,
            onValueChange = {
                txtSearch = it
            },
            placeholder = { Text(text = "Search ....") },

            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search")
            },
            shape = RoundedCornerShape(23.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        )
    }


}