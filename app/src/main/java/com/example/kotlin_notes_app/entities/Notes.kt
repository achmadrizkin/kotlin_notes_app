package com.example.kotlin_notes_app.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// setup Room Database
@Entity(tableName = "Notes")
data class Notes(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "sub_title")
    var subTitle: String,

    @ColumnInfo(name = "date_time")
    var dateTime: String,

    @ColumnInfo(name = "img_path")
    var imgPath: String,

    @ColumnInfo(name = "web_link")
    var webLink: String,

    @ColumnInfo(name = "color")
    var color: String

) {
    override fun toString(): String {
        return "$title: $dateTime"
    }
}