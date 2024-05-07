package org.d3if3028.assessment1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hewan")
data class Hewan(
    @PrimaryKey(autoGenerate = true)
    val id: Long= 0L,
    val nama: String,
    val kingdom: String,
    val makan: String
)
