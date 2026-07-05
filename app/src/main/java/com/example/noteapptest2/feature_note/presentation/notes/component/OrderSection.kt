package com.example.noteapptest2.feature_note.presentation.notes.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.noteapptest2.feature_note.domain.model.Note
import com.example.noteapptest2.feature_note.domain.util.NoteOrder
import com.example.noteapptest2.feature_note.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    noteOrder: NoteOrder = NoteOrder.Date(OrderType.Descending),
    onNoteOrderChange:(NoteOrder) -> Unit
   ) {

    Column(modifier = modifier
        .fillMaxWidth()) {
        Row{
            DefaultRadioButton(
                "Title",
                selected = noteOrder is NoteOrder.Title,
                onSelected = {onNoteOrderChange(NoteOrder.Title(noteOrder.orderType))})
            DefaultRadioButton(
                "Date",
                selected = noteOrder is NoteOrder.Date,
                onSelected = {onNoteOrderChange(NoteOrder.Date(noteOrder.orderType))})
            DefaultRadioButton(
                "Color",
                selected = noteOrder is NoteOrder.Color,
                onSelected = {onNoteOrderChange(NoteOrder.Color(noteOrder.orderType))})

        }
        Row{
            DefaultRadioButton(
                "Ascending",
                selected = noteOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onNoteOrderChange(noteOrder.copy(
                        OrderType.Ascending
                    ))
                })
            DefaultRadioButton(
                "Descending",
                selected = noteOrder.orderType is OrderType.Descending,
                onSelected = {
                    noteOrder.copy(
                        OrderType.Descending
                    )
                })
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Show() {
    OrderSection() { }
}