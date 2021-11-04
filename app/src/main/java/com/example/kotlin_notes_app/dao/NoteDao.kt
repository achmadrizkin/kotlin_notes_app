package com.example.kotlin_notes_app.dao

import androidx.room.*
import com.example.kotlin_notes_app.entities.Notes

@Dao
interface NoteDao {
    @get:Query("SELECT * FROM Notes ORDER BY id DESC")
    val allNotes: List<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(note: Notes)

    @Delete
    fun deleteNotes(note:Notes)
}