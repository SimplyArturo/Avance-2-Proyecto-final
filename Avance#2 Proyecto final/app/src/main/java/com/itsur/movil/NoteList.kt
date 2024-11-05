package com.example.myapplication.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

import com.itsur.movil.DataBase.Note
import com.itsur.movil.R

@Composable
fun NoteList(
    notes: List<Note>,
    onItemClick: (Note) -> Unit,
    onDeleteNote: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        for (note in notes) {
            Row(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                Text(note.title, modifier = Modifier.weight(1f).clickable { onItemClick(note) })
                IconButton(onClick = { onDeleteNote(note) }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = stringResource(id = R.string.delete_note)
                    )
                }
            }
        }
    }
}
