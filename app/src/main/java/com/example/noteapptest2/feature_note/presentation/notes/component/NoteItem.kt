package com.example.noteapptest2.feature_note.presentation.notes.component

import android.text.style.IconMarginSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import com.example.noteapptest2.feature_note.domain.model.Note
import java.sql.Date
import java.time.LocalDateTime
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.max

@Composable
fun NoteItem(
    note: Note,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(
                topEnd = 40.dp,
                topStart = 16.dp,
                bottomEnd = 16.dp,
                bottomStart = 16.dp,
            ))
            .background(color = Color(note.color))
            .padding(16.dp)
    ){
        Column(modifier = Modifier.padding(end = 32.dp)) {
            Text(
                note.title,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onDeleteClick
        ) {
            Icon(
                Icons.Filled.Delete,contentDescription = null,

            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Show2() {
    NoteItem(
        note = Note(
            title = "hyder",
            content = "sauof;haaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbawbvwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwrvbvbo",
            color = Color.Cyan.toArgb(),
            timestamp =2,
            id = 3
        ),
        onDeleteClick = {}
        )
}