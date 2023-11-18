package kz.just_code.hiltdiapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TodoEntity::class, CarEntity::class], version = 5)
@TypeConverters(ElectronicsConverter::class)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
    abstract fun carDao(): CarDao
}