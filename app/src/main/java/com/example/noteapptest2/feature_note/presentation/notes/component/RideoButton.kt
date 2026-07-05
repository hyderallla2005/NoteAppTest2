package com.example.noteapptest2.feature_note.presentation.notes.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier) {
    Row(modifier = modifier,
        verticalAlignment = Alignment.CenterVertically) {
        androidx.compose.material3.RadioButton(selected = selected, onClick = onSelected)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
        text= text,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}