package com.example.s3vior.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "persons_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    var id :Int=0 ,
    @ColumnInfo("image")
    var image: String?="",
    @ColumnInfo("name")
    var name: String="",
    @ColumnInfo("age")
    var age: Int=0,
    @ColumnInfo("gender")
    var gender: String="",
    @ColumnInfo("description")
    var description: String?="",
    @ColumnInfo("type")
    var type: String?=""
)
