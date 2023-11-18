package kz.just_code.hiltdiapp.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Query("Select * from to_do where id = :id LIMIT 1")
    fun getById(id: Int): TodoEntity

    @Query("Select * from to_do where save_time = :date order by save_time DESC")
    fun getAllByDate(date: String): List<TodoEntity>  ////

    @Query("Select * from to_do order by save_time DESC")
    fun getAll(): List<TodoEntity> ////

    @Query("Select * from to_do order by save_time DESC")
    fun getAllFlow(): Flow<List<TodoEntity>>  ////

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(todoEntity: TodoEntity) ////

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(todoEntity: List<TodoEntity>) ////

    @Query("Delete from to_do") ////
    fun deleteAll()

    @Query("Delete from to_do where id = :id")
    fun deleteById(id: Int) ////
}