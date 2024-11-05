package com.example.myapplication.repository


import com.itsur.movil.DataBase.Note
import com.itsur.movil.DataBase.NoteDao

import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes()
    }

    override suspend fun addNote(note: Note) {
        noteDao.insert(note)
    }

    override suspend fun updateNote(oldNote: Note, newNote: Note) {
        noteDao.update(newNote)
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }
}
