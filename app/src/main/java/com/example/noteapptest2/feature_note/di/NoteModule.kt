package com.example.noteapptest2.feature_note.di

import android.app.Application
import androidx.room.Room
import com.example.noteapptest2.feature_note.data.data_source.NoteDao
import com.example.noteapptest2.feature_note.data.data_source.NoteDatabase
import com.example.noteapptest2.feature_note.data.repository.NoteRepositoryImpl
import com.example.noteapptest2.feature_note.domain.repository.NoteRepository
import com.example.noteapptest2.feature_note.domain.use_case.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NoteModule {
    @Singleton
    @Provides
   fun provideNoteDatabase(app: Application): NoteDatabase{
       return Room.databaseBuilder(
           context = app,
           klass = NoteDatabase::class.java,
           name = NoteDatabase.DATABASE_NAME
       ).build()
   }
    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDatabase): NoteRepository{
        return NoteRepositoryImpl(noteDao.noteDao)
    }

}