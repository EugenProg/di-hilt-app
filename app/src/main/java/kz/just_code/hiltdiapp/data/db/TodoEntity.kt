package kz.just_code.hiltdiapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val message: String,
    @ColumnInfo(name = "save_time") val saveTime: String
)
