package com.example.myapplication.repository


import com.itsur.movil.DataBase.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun updateNote(oldNote: Note, newNote: Note)
    suspend fun deleteNote(note: Note)
}
