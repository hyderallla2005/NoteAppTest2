package com.example.noteapptest2.feature_note.domain.model

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapptest2.feature_note.presentation.ui.theme.BabyBlue
import com.example.noteapptest2.feature_note.presentation.ui.theme.DarkGray
import com.example.noteapptest2.feature_note.presentation.ui.theme.LightBlue
import com.example.noteapptest2.feature_note.presentation.ui.theme.LightGreen
import com.example.noteapptest2.feature_note.presentation.ui.theme.RedOrange
import com.example.noteapptest2.feature_note.presentation.ui.theme.RedPink
import com.example.noteapptest2.feature_note.presentation.ui.theme.Violet
@Entity
data class Note(
    val title: String ,
    val content: String ,
    val color: Int ,
    val timestamp: Long,
    @PrimaryKey
    val id: Int? = null

){
    companion object{
        val noteColors = listOf(
            RedPink,BabyBlue,Violet,LightBlue,LightGreen,RedOrange,DarkGray
        )
    }
}

class InvalidNoteException(message: String): Exception(message)

