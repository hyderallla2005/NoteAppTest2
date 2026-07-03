package com.example.noteapptest2.feature_note.data.data_source

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.noteapptest2.feature_note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteDao {

    @Query("Select * From Note")
    fun getNotes(): Flow<List<Note>>

    @Query("select * from Note where id =:noteId")
    suspend fun getNoteById(noteId: Int): Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}