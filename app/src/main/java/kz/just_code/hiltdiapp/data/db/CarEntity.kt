package kz.just_code.hiltdiapp.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson

@Entity(tableName = "car")
data class CarEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val manufacture: String,
    val model: String,
    @Embedded
    val engine: Engine,
    @TypeConverters(ElectronicsConverter::class)
    val electronicList: List<Electronic>
)

data class Engine(
    val power: Int,
    val volume: Double,
    val count: Int
)

data class Electronic(
    val uid: String,
    val name: String,
    val serialNumber: String
)

class StringConverter {

    @TypeConverter
    fun fromList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(line: String): List<String> {
        return Gson().fromJson(line, Array<String>::class.java).toList()
    }
}

class ElectronicsConverter {

    @TypeConverter
    fun fromList(list: List<Electronic>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toList(line: String): List<Electronic> {
        return Gson().fromJson(line, Array<Electronic>::class.java).toList()
    }
}