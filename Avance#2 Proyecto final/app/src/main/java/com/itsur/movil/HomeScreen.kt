package com.example.myapplication.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itsur.movil.R
import com.example.myapplication.viewmodel.NoteViewModel
import com.itsur.movil.DataBase.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(noteViewModel: NoteViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.home),
                        fontSize = 24.sp, // Ajusta el tamaño del texto aquí
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis // Maneja el desbordamiento de texto
                    )
                }
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(modifier = Modifier.padding(innerPadding)) {
            if (maxWidth < 600.dp) {
                HomeScreenCompact(noteViewModel)
            } else {
                HomeScreenExpanded(noteViewModel)
            }
        }
    }
}

@Composable
fun HomeScreenCompact(noteViewModel: NoteViewModel) {
    val notes by noteViewModel.notes.collectAsState(initial = emptyList())
    var selectedNote: Note? by remember { mutableStateOf<Note?>(null) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        NoteList(
            notes = notes,
            onItemClick = { note -> selectedNote = note },
            onDeleteNote = { note -> noteViewModel.deleteNote(note) }
        )

        selectedNote?.let {
            EditNoteDialog(
                note = it,
                onDismiss = { selectedNote = null },
                onSaveNote = { updatedNote ->
                    noteViewModel.updateNote(it, updatedNote)
                    selectedNote = null
                },
                onDeleteNote = {
                    noteViewModel.deleteNote(it)
                    selectedNote = null
                }
            )
        }
    }
}

@Composable
fun HomeScreenExpanded(noteViewModel: NoteViewModel) {
    val notes by noteViewModel.notes.collectAsState(initial = emptyList())
    var selectedNote: Note? by remember { mutableStateOf<Note?>(null) }

    Row(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            NoteList(
                notes = notes,
                onItemClick = { note -> selectedNote = note },
                onDeleteNote = { note -> noteViewModel.deleteNote(note) }
            )
        }

        selectedNote?.let {
            Column(modifier = Modifier.weight(1f)) {
                EditNoteDialog(
                    note = it,
                    onDismiss = { selectedNote = null },
                    onSaveNote = { updatedNote ->
                        noteViewModel.updateNote(it, updatedNote)
                        selectedNote = null
                    },
                    onDeleteNote = {
                        noteViewModel.deleteNote(it)
                        selectedNote = null
                    }
                )
            }
        }
    }
}
